package com.feenk.jdt2famix.injava;

import java.util.Arrays;
import java.util.function.Consumer;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.InfixExpression.Operator;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

import com.feenk.jdt2famix.model.famix.Access;
import com.feenk.jdt2famix.model.famix.AnnotationType;
import com.feenk.jdt2famix.model.famix.AnnotationTypeAttribute;
import com.feenk.jdt2famix.model.famix.Attribute;
import com.feenk.jdt2famix.model.famix.CaughtException;
import com.feenk.jdt2famix.model.famix.Class;
import com.feenk.jdt2famix.model.famix.Enum;
import com.feenk.jdt2famix.model.famix.EnumValue;
import com.feenk.jdt2famix.model.famix.Inheritance;
import com.feenk.jdt2famix.model.famix.Invocation;
import com.feenk.jdt2famix.model.famix.Method;
import com.feenk.jdt2famix.model.famix.Namespace;
import com.feenk.jdt2famix.model.famix.ParameterizedType;
import com.feenk.jdt2famix.model.famix.ThrownException;
import com.feenk.jdt2famix.model.famix.Type;

/**
 * Responsible for visiting the AST of one Java file. It works in close
 * relationship with the {@link InJavaImporter} which - provides ensure and
 * create methods, and - keeps track of the overall model.
 * 
 * Each method that visits a container entity (e.g., Type, Method ...), - we
 * push the resulting Famix entity in the importer stack, and - we pop it in a
 * corresponding endVisit method
 * 
 * @author girba
 *
 */
public class AstVisitor extends ASTVisitor {

	private InJavaImporter importer;

	public AstVisitor(InJavaImporter importer) {
		this.importer = importer;
	}

	public void logNullBinding(String string, Object extraData, int lineNumber) {
		importer.logNullBinding(string, extraData, lineNumber);
	}

	//////// PACKAGES

	@Override
	public boolean visit(CompilationUnit node) {
		Namespace namespace;
		if (node.getPackage() == null)
			/* This is the default package */
			namespace = importer.ensureNamespaceNamed("");
		else
			namespace = importer.ensureNamespaceFromPackageBinding(node.getPackage().resolveBinding());
		namespace.setIsStub(false);
		importer.pushOnContainerStack(namespace);
		return true;
	}

	/**
	 * Needed for keeping track of the current container
	 */
	@Override
	public void endVisit(CompilationUnit node) {
		importer.popFromContainerStack();
	}

	//////// TYPES

	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(TypeDeclaration node) {
		ITypeBinding binding = node.resolveBinding();
		if (binding == null) {
			logNullBinding("type declaration", node.getName(),
					((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition()));
			return false;
		}
		Type type = importer.ensureTypeFromTypeBinding(binding);
		org.eclipse.jdt.core.dom.Type superclassType = node.getSuperclassType();
		/*
		 * This is an ugly patch. When the binding to the superclass or super interfaces
		 * cannot be resolved, we try to recover as much info as possible We do it here
		 * because it is hard to pass around the dom type
		 */
		if (binding.getSuperclass() == null && superclassType != null)
			importer.createInheritanceFromSubtypeToSuperDomType(type, superclassType);

		if (superclassType != null)
			type.getSuperInheritances().stream().filter(inheritance -> (inheritance.getSuperclass() instanceof Class && !((Class)inheritance.getSuperclass()).getIsInterface())
					|| (inheritance.getSuperclass() instanceof ParameterizedType && !((ParameterizedType)inheritance.getSuperclass()).getParameterizableClass().getIsInterface()))
					.findFirst().ifPresent(in -> importer.createSourceAnchor(in, superclassType));

		if (binding.getInterfaces().length == 0 && !node.superInterfaceTypes().isEmpty())
			node.superInterfaceTypes().stream().forEach(t -> {
				importer.createInheritanceFromSubtypeToSuperDomType(type, (org.eclipse.jdt.core.dom.Type) t);
			});

		// create source anchors for implemented interfaces references
		createSourceAnchorsForInterfaceInheritance(node, type);

		type.setIsStub(false);
		importer.createSourceAnchor(type, node);
		importer.ensureCommentFromBodyDeclaration(type, node);
		importer.pushOnContainerStack(type);
		return true;
	}

	private void createSourceAnchorsForInterfaceInheritance(EnumDeclaration node, Type type) {
		if (!node.superInterfaceTypes().isEmpty()) {
			node.superInterfaceTypes().stream().forEach(createSourceAnchorForInheritance(type));
		}
	}
	
	private void createSourceAnchorsForInterfaceInheritance(TypeDeclaration node, Type type) {
		if (!node.superInterfaceTypes().isEmpty()) {
			node.superInterfaceTypes().stream().forEach(createSourceAnchorForInheritance(type));
		}
	}

	private Consumer createSourceAnchorForInheritance(Type type) {
		return t -> {
			org.eclipse.jdt.core.dom.Type currentInterface = (org.eclipse.jdt.core.dom.Type) t;
			type.getSuperInheritances().stream()
					.filter(superInheritance -> currentInterface.resolveBinding() != null && superInheritance
							.getSuperclass().getName().equals(currentInterface.resolveBinding().getName()))
					.findFirst()
					.ifPresent(superInheritance -> importer.createSourceAnchor(superInheritance, currentInterface));
		};
	}

	@Override
	public void endVisit(TypeDeclaration node) {
		if (importer.topOfContainerStack() instanceof Type)
			importer.popFromContainerStack();
	}

	@Override
	public boolean visit(AnonymousClassDeclaration node) {
		ITypeBinding binding = node.resolveBinding();
		Type type;
		if (binding != null)
			type = importer.createTypeFromTypeBinding(binding);
		else {
			type = importer.createTypeNamedInUnknownNamespace("");
			logNullBinding("anonymous type declaration", node.getParent().toString().replaceAll("\n", " "),
					((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition()));
		}
		importer.ensureTypeFromAnonymousDeclaration(type, node);
		type.setIsStub(false);
		importer.createSourceAnchor(type, node);
		importer.pushOnContainerStack(type);
		return true;
	}

	@Override
	public void endVisit(AnonymousClassDeclaration node) {
		importer.popFromContainerStack();
	}

	@Override
	public boolean visit(EnumDeclaration node) {
		ITypeBinding binding = node.resolveBinding();
		if (binding == null) {
			logNullBinding("enum declaration", node.getName(),
					((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition()));
			return false;
		}
		Enum famixEnum = (Enum) importer.ensureTypeFromTypeBinding(binding);
		createSourceAnchorsForInterfaceInheritance(node, famixEnum);
		famixEnum.setIsStub(false);
		importer.createSourceAnchor(famixEnum, node);
		importer.ensureCommentFromBodyDeclaration(famixEnum, node);
		importer.pushOnContainerStack(famixEnum);
		return true;
	}

	@Override
	public void endVisit(EnumDeclaration node) {
		if (importer.topOfContainerStack() instanceof Enum)
			importer.popFromContainerStack();
	}

	@Override
	public boolean visit(EnumConstantDeclaration node) {
		if (!node.arguments().isEmpty())
			importer.pushOnContainerStack(importer.ensureInitializerMethod());
		EnumValue enumValue = importer.ensureEnumValueFromDeclaration(node);
		importer.createSourceAnchor(enumValue, node);
		importer.ensureCommentFromBodyDeclaration(enumValue, node);
		return true;
	}

	@Override
	public void endVisit(EnumConstantDeclaration node) {
		if (importer.topOfContainerStack().getName().equals(InJavaImporter.INITIALIZER_NAME))
			importer.popFromContainerStack();
	}

	//////// ANNOTATIONS

	@Override
	public boolean visit(AnnotationTypeDeclaration node) {
		ITypeBinding binding = node.resolveBinding();
		if (binding == null) {
			logNullBinding("annotation type declaration", node.getName(),
					((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition()));
			return false;
		}
		Type type = importer.ensureTypeFromTypeBinding(binding);
		type.setIsStub(false);
		importer.createSourceAnchor(type, node);
		importer.pushOnContainerStack(type);
		importer.ensureCommentFromBodyDeclaration(type, node);
		return true;
	}

	@Override
	public void endVisit(AnnotationTypeDeclaration node) {
		if (importer.topOfContainerStack() instanceof AnnotationType)
			importer.popFromContainerStack();
	}

	@Override
	public boolean visit(AnnotationTypeMemberDeclaration node) {
		AnnotationTypeAttribute attribute = importer.ensureAnnotationTypeAttributeFromDeclaration(node);
		attribute.setIsStub(false);
		importer.ensureCommentFromBodyDeclaration(attribute, node);
		return super.visit(node);
	}

	@Override
	public void endVisit(AnnotationTypeMemberDeclaration node) {
		super.endVisit(node);
	}

	/**
	 * handles: @ TypeName We do not use this one because we want to tie the
	 * creation of annotation instances with the ensuring of bindings (e.g.,
	 * {@link InJavaImporter#ensureTypeFromTypeBinding(ITypeBinding)}). Thus, we
	 * prefer to call the annotation creation explicitly from the other visit
	 * methods (e.g., {link {@link #visit(TypeDeclaration)}
	 */
	@Override
	public boolean visit(MarkerAnnotation node) {
		return true;
	}

	/**
	 * handles: @ TypeName ( [ MemberValuePair { , MemberValuePair } ] ) see comment
	 * from {@link #visit(MarkerAnnotation)}
	 */
	@Override
	public boolean visit(NormalAnnotation node) {
		return true;
	}

	/**
	 * handles: @ TypeName ( Expression ) see comment from
	 * {@link #visit(MarkerAnnotation)}
	 */
	@Override
	public boolean visit(SingleMemberAnnotation node) {
		return true;
	}

	//////// METHODS

	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(MethodDeclaration node) {
		IMethodBinding binding = node.resolveBinding();
		Method method;
		if (binding != null) {
			method = importer.ensureMethodFromMethodBindingToCurrentContainer(binding);
			Arrays.stream(binding.getExceptionTypes())
					.forEach(e -> importer.createDeclaredExceptionFromTypeBinding(e, method));
		} else {
			logNullBinding("method declaration", node.getName(),
					((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition()));
			method = importer.ensureMethodFromMethodDeclaration(node);
		}
		method.setIsStub(false);
		method.setCyclomaticComplexity(1);
		importer.pushOnContainerStack(method);
		node.parameters().stream().forEach(
				p -> importer.ensureParameterFromSingleVariableDeclaration((SingleVariableDeclaration) p, method));
		importer.createSourceAnchor(method, node);
		importer.ensureCommentFromBodyDeclaration(method, node);
		return true;
	}

	@Override
	public void endVisit(MethodDeclaration node) {
		importer.popFromContainerStack();
	}

	@Override
	public boolean visit(Initializer node) {
		Method method = importer.ensureInitializerMethod();
		importer.pushOnContainerStack(method);
		importer.createSourceAnchor(method, node);
		importer.ensureCommentFromBodyDeclaration(method, node);
		return true;
	}

	@Override
	public void endVisit(Initializer node) {
		importer.popFromContainerStack();
	}

	//////// ATTRIBUTES

	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(FieldDeclaration node) {
		if (node.fragments().stream().anyMatch(f -> ((VariableDeclarationFragment) f).getInitializer() != null))
			importer.pushOnContainerStack(importer.ensureInitializerMethod());
		node.fragments().stream().forEach(f -> visitFragment((VariableDeclarationFragment) f, node));
		return true;
	}

	private void visitFragment(VariableDeclarationFragment fragment, FieldDeclaration field) {
		Attribute attribute = importer.ensureAttributeForFragment(fragment, field);
		importer.createSourceAnchor(attribute, fragment);
		importer.ensureCommentFromBodyDeclaration(attribute, field);

		/*
		 * only the last fragment of a field contains the initializer code. thus, to
		 * create the access to each variable in the fragment we need to ask that last
		 * fragment we do not have to check the existence of that last fragment, because
		 * we already know that the field has at least one fragment
		 */
		VariableDeclarationFragment lastFragment = (VariableDeclarationFragment) field.fragments()
				.get(field.fragments().size() - 1);
		if (lastFragment.getInitializer() != null) {
			Access access = importer.createAccessFromExpression(fragment.getName());
			access.setIsWrite(true);
			importer.createAccessFromExpression((Expression) lastFragment.getInitializer());
		}
		attribute.setIsStub(false);
	}

	@Override
	public void endVisit(FieldDeclaration node) {
		if (importer.topOfContainerStack().getName().equals(InJavaImporter.INITIALIZER_NAME))
			importer.popFromContainerStack();
	}

	//////// LOCAL VARIABLES

	// I do not know when this one is triggered
	// public boolean visit(VariableDeclarationExpression node) {
	// node.fragments().stream().forEach(
	// fragment ->
	// importer.ensureLocalVariableFromFragment((VariableDeclarationFragment)
	// fragment, node.getType()));
	// return true;
	// }
	@SuppressWarnings("unchecked")
	public boolean visit(VariableDeclarationStatement node) {
		node.fragments().stream().forEach(fragment -> importer
				.ensureLocalVariableFromFragment((VariableDeclarationFragment) fragment, node.getType()));
		return true;
	}

	//////// INVOCATIONS

	/**
	 * handles object.method(parameter)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(MethodInvocation node) {
		Invocation invocation = importer.createInvocationFromMethodBinding(node.resolveMethodBinding(),
				node.toString().trim());
		importer.createSourceAnchor(invocation, node.getName());
		importer.createAccessFromExpression(node.getExpression());
		invocation.setReceiver(importer.ensureStructuralEntityFromExpression(node.getExpression()));
		node.arguments().stream().forEach(arg -> importer.createAccessFromExpression((Expression) arg));
		return true;
	}

	/**
	 * handles super.method(parameter)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(SuperMethodInvocation node) {
		Invocation invocation = importer.createInvocationFromMethodBinding(node.resolveMethodBinding(),
				node.toString().trim());
		importer.createSourceAnchor(invocation, node.getName());
		node.arguments().stream().forEach(arg -> importer.createAccessFromExpression((Expression) arg));
		return true;
	}

	/**
	 * handles this(parameter)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(ConstructorInvocation node) {
		Invocation invocation = importer.createInvocationFromMethodBinding(node.resolveConstructorBinding(),
				node.toString().trim());
		importer.createSourceAnchor(invocation, node.getStartPosition() + 1, node.getStartPosition() + "this".length());
		node.arguments().stream().forEach(arg -> importer.createAccessFromExpression((Expression) arg));
		return true;
	}

	/**
	 * handles super(parameter)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(SuperConstructorInvocation node) {
		Invocation invocation = importer.createInvocationFromMethodBinding(node.resolveConstructorBinding(),
				node.toString().trim());
		importer.createSourceAnchor(invocation, node.getStartPosition() + 1,
				node.getStartPosition() + "super".length());
		node.arguments().stream().forEach(arg -> importer.createAccessFromExpression((Expression) arg));
		return true;
	}

	/**
	 * handles new Class()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(ClassInstanceCreation node) {
		IMethodBinding binding = node.resolveConstructorBinding();
		if (binding != null) {
			Invocation invocation = importer.createInvocationFromMethodBinding(binding, node.toString().trim());
			importer.createSourceAnchor(invocation, node.getType());
		} else {
			String name = node.getType().toString();
			importer.ensureBasicMethod(name, name, importer.ensureTypeNamedInUnknownNamespace(name),
					m -> importer.createInvocationToMethod(m, node.toString().trim()));
		}

		node.arguments().stream().forEach(arg -> importer.createAccessFromExpression((Expression) arg));
		return true;
	}

	//////// ACCESSES

	/**
	 * This one looks highly interesting, but I do not know in which situation this
	 * is invoked. Funny, no?
	 */
	@Override
	public boolean visit(FieldAccess node) {
		return true;
	}

	@Override
	public boolean visit(Assignment node) {
		Access writeAccess = importer.createAccessFromExpression((Expression) node.getLeftHandSide());
		writeAccess.setIsWrite(true);
		importer.createAccessFromExpression((Expression) node.getRightHandSide());
		return true;
	}

	@Override
	public boolean visit(ReturnStatement node) {
		importer.createAccessFromExpression((Expression) node.getExpression());
		return true;
	}

	/**
	 * We create this access explicitly to catch a boolean variable used in
	 * condition. Complicated expressions are handled in
	 * {@link #visit(InfixExpression)}
	 */
	@Override
	public boolean visit(WhileStatement node) {
		importer.topFromContainerStack(Method.class).incCyclomaticComplexity();
		;
		importer.createAccessFromExpression((Expression) node.getExpression());
		return true;
	}

	/**
	 * We create this access explicitly to catch a boolean variable used in
	 * condition. Complicated expressions are handled in
	 * {@link #visit(InfixExpression)}
	 */
	@Override
	public boolean visit(DoStatement node) {
		importer.topFromContainerStack(Method.class).incCyclomaticComplexity();
		;
		importer.createAccessFromExpression((Expression) node.getExpression());
		return true;
	}

	/**
	 * We create this access explicitly to catch a boolean variable used in
	 * condition. Complicated expressions are handled in
	 * {@link #visit(InfixExpression)}
	 */
	@Override
	public boolean visit(IfStatement node) {
		importer.topFromContainerStack(Method.class).incCyclomaticComplexity();
		;
		importer.createAccessFromExpression((Expression) node.getExpression());
		return true;
	}

	/**
	 * We create this access explicitly to catch a boolean variable used in
	 * condition. Complicated expressions are handled in
	 * {@link #visit(InfixExpression)}
	 */
	@Override
	public boolean visit(SwitchStatement node) {
		importer.createAccessFromExpression((Expression) node.getExpression());
		return true;
	}

	/**
	 * Handles for (int i = init; i < n; i++)
	 * 
	 * We create this access explicitly to catch a boolean variable used in
	 * condition. Complicated expressions are handled in
	 * {@link #visit(InfixExpression)}
	 */
	@Override
	public boolean visit(ForStatement node) {
		importer.topFromContainerStack(Method.class).incCyclomaticComplexity();
		;
		importer.createAccessFromExpression((Expression) node.getExpression());
		// for (Iterator<?> iterator = node.initializers().iterator();
		// iterator.hasNext();) {
		// VariableDeclarationExpression initializerExpression =
		// (VariableDeclarationExpression) iterator.next();
		// for (Iterator<?> iterator2 = initializerExpression.fragments().iterator();
		// iterator2.hasNext();) {
		// VariableDeclarationFragment fragment = (VariableDeclarationFragment)
		// iterator2.next();
		// importer.createAccessFromExpression((Expression) fragment.getInitializer());
		// }
		// }
		return true;
	}

	/**
	 * Handles for ( Object x : list ) We create this access explicitly to catch a
	 * boolean variable used in condition. Complicated expressions are handled in
	 * {@link #visit(InfixExpression)}
	 */
	@Override
	public boolean visit(EnhancedForStatement node) {
		importer.topFromContainerStack(Method.class).incCyclomaticComplexity();
		;
		importer.createAccessFromExpression((Expression) node.getExpression());
		return true;
	}

	/**
	 * We create this access explicitly to catch a boolean variable used in
	 * condition. Complicated expressions are handled in
	 * {@link #visit(InfixExpression)}
	 */
	@Override
	public boolean visit(ConditionalExpression node) {
		importer.topFromContainerStack(Method.class).incCyclomaticComplexity();
		;
		importer.createAccessFromExpression((Expression) node.getExpression());
		return true;
	}

	/**
	 * This one also takes care of expanded expressions like true || (41 == 42) &&
	 * booleanAttribute
	 */
	@Override
	public boolean visit(InfixExpression node) {
		if (node.getOperator().equals(Operator.AND) || node.getOperator().equals(Operator.OR)) {
			importer.topFromContainerStack(Method.class).incCyclomaticComplexity();
			;
		}
		importer.createAccessFromExpression((Expression) node.getLeftOperand());
		importer.createAccessFromExpression((Expression) node.getRightOperand());
		return true;
	}

	/**
	 * Handles expressions like (a && b || c)
	 */
	@Override
	public boolean visit(ParenthesizedExpression node) {
		importer.createAccessFromExpression((Expression) node.getExpression());
		return true;
	}

	/**
	 * Handles synchronized(a) {}
	 */
	@Override
	public boolean visit(SynchronizedStatement node) {
		importer.createAccessFromExpression((Expression) node.getExpression());
		return true;
	}

	/**
	 * It would be ideal to find a way to use this method for creating accesses.
	 * However without having the context, we do not know whether this is actually
	 * an access. That is why we have to resort to spreading these
	 * createAccessFromExpression calls everywhere.
	 */
	@Override
	public boolean visit(SimpleName node) {
		return false;
	}

	@Override
	public boolean visit(CatchClause node) {
		importer.topFromContainerStack(Method.class).incCyclomaticComplexity();
		;
		CaughtException caughtException = new CaughtException();
		ITypeBinding binding = node.getException().getType().resolveBinding();
		if (binding != null) {
			Type caughtType = importer.ensureTypeFromTypeBinding(binding);
			caughtException.setExceptionClass((com.feenk.jdt2famix.model.famix.Class) caughtType);
			caughtException.setDefiningMethod((Method) importer.topOfContainerStack());
			importer.repository().add(caughtException);
		}
		return true;
	}

	@Override
	public boolean visit(ThrowStatement node) {
		ITypeBinding binding = node.getExpression().resolveTypeBinding();
		if (binding != null) {
			ThrownException thrownException = new ThrownException();
			Type thrownType = importer.ensureTypeFromTypeBinding(binding);
			thrownException.setExceptionClass((com.feenk.jdt2famix.model.famix.Class) thrownType);
			thrownException.setDefiningMethod((Method) importer.topOfContainerStack());
			importer.repository().add(thrownException);
		}
		return true;
	}

	@Override
	public boolean visit(SwitchCase node) {
		importer.topFromContainerStack(Method.class).incCyclomaticComplexity();
		return true;
	}
}
