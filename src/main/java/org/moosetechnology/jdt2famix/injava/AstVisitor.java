package org.moosetechnology.jdt2famix.injava;


import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.moosetechnology.model.famix.Attribute;
import org.moosetechnology.model.famix.Namespace;
import org.moosetechnology.model.famix.Type;
import org.moosetechnology.model.famix.Class;
import org.moosetechnology.model.famix.Method;

/**
 * Responsible for visiting the AST of one Java file.
 * It works in close relationship with the {@link InJavaImporter} which
 * - provides ensure and create methods, and
 * - keeps track of the overall model.
 *  
 * @author girba
 *
 */
public class AstVisitor extends ASTVisitor {

	private InJavaImporter importer;
	public AstVisitor(InJavaImporter importer) {
		this.importer = importer;
	}
	
	////////PACKAGES
	
	public static String visitCompilationUnitCallback = AstVisitor.class.getName() + "visit(CompilationUnit)";
	@Override
	public boolean visit(CompilationUnit node) {
		Namespace namespace = importer.ensureNamespaceFromPackageBinding(node.getPackage().resolveBinding());
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
	
	
	////////TYPES
	
	@Override
	public boolean visit(TypeDeclaration node) {
		ITypeBinding binding = node.resolveBinding();
		Type type = importer.ensureTypeFromTypeBinding(binding);
		org.eclipse.jdt.core.dom.Type superclassType = node.getSuperclassType();
		/* This is an ugly patch. When the binding to the superclass or super interfaces cannot be resolved,
		 * we try to recover as much info as possible
		 * We do it here because it is hard to pass around the dom type */
		if (binding.getSuperclass() == null && superclassType != null)
			importer.createInheritanceFromSubtypeToSuperDomType(type, superclassType);
		if (binding.getInterfaces().length == 0 && !node.superInterfaceTypes().isEmpty())
			node.superInterfaceTypes().stream().forEach(t -> importer.createInheritanceFromSubtypeToSuperDomType(type, (org.eclipse.jdt.core.dom.Type) t));
		//TODO: rethink what happens with the container of nested classes
		type.setIsStub(false);
		importer.pushOnContainerStack(type);
		return true;
	}
	
	/**
	 * Needed for keeping track of the current container
	 */
	@Override
	public void endVisit(TypeDeclaration node) {
		importer.popFromContainerStack();
	}

	@Override
	public boolean visit(AnonymousClassDeclaration node) {
		Class clazz = new Class();
		clazz.setContainer(importer.topOfContainerStack());
		clazz.setName("$" + importer.topOfContainerStack().getTypes().size());
		importer.pushOnContainerStack(clazz);
		return true;
	}
	
	@Override
	public void endVisit(AnonymousClassDeclaration node) {
		importer.popFromContainerStack();
	}
//	
//	public static String visitEnumDeclarationCallback = AstVisitor.class.getName() + "visit(EnumDeclaration)";
//	@Override
//	public boolean visit(EnumDeclaration node) {
//		try {
//			new SmalltalkRequest(visitEnumDeclarationCallback, this, node).value();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return true;
//	}
//	
//	public static String endVisitEnumDeclarationCallback = AstVisitor.class.getName() + "endVisit(EnumDeclaration)";
//	@Override
//	public void endVisit(EnumDeclaration node) {
//		try {
//			new SmalltalkRequest(endVisitEnumDeclarationCallback, this, node).value();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	////////ANNOTATIONS
//	
//	@Override
//	public boolean visit(AnnotationTypeDeclaration node) {
//		// TODO Auto-generated method stub
//		return super.visit(node);
//	}
//	
//	@Override
//	public void endVisit(AnnotationTypeDeclaration node) {
//		// TODO Auto-generated method stub
//		super.endVisit(node);
//	}
//	
//	@Override
//	public boolean visit(AnnotationTypeMemberDeclaration node) {
//		// TODO Auto-generated method stub
//		return super.visit(node);
//	}
//	
//	@Override
//	public void endVisit(AnnotationTypeMemberDeclaration node) {
//		// TODO Auto-generated method stub
//		super.endVisit(node);
//	}
//	
//	
	////////METHODS
	
	@Override
	public boolean visit(MethodDeclaration node) {
		IMethodBinding binding = node.resolveBinding();
		Method method;
		if (binding != null)
			method = importer.ensureMethodFromMethodBinding(binding);
		else
			method = importer.ensureMethodFromMethodDeclaration(node);
		method.setIsStub(false);
		node.parameters().
			stream().
			forEach(p -> 
				importer.ensureParameterFromSingleVariableDeclaration((SingleVariableDeclaration) p, method));
		importer.pushOnContainerStack(method);
		return true;
	}

	/**
	 * Needed for keeping track of the current container
	 */
	@Override
	public void endVisit(MethodDeclaration node) {
		importer.popFromContainerStack();
	}
	
	
	////////ATTRIBUTES
	
	@Override
	public boolean visit(FieldDeclaration node) {
		node.fragments().stream().forEach(f -> visitFragment((VariableDeclarationFragment) f, node));
		return true;
	}
	
	private void visitFragment(VariableDeclarationFragment fragment, FieldDeclaration field) {
		Attribute attribute = importer.ensureAttributeForFragment(fragment, field);
		attribute.setIsStub(false);
	}
	
	
	////////LOCAL VARIABLES
	
//  I do not know when this one is triggered
//	public boolean visit(VariableDeclarationExpression node) {
//		node.fragments().stream().forEach(
//				fragment -> 
//				importer.ensureLocalVariableFromFragment((VariableDeclarationFragment) fragment, node.getType()));
//		return true;
//	}
	public boolean visit(VariableDeclarationStatement node) {
		node.fragments().stream().forEach(
				fragment -> 
				importer.ensureLocalVariableFromFragment((VariableDeclarationFragment) fragment, node.getType()));
		return true;
	}
	
//	
//	////////INVOCATIONS
//	
//	public static String visitMethodInvocationCallback = AstVisitor.class.getName() + "visit(MethodInvocation)";
//	@Override
//	public boolean visit(MethodInvocation node) {
//		try {
//			new SmalltalkRequest(visitMethodInvocationCallback, this, node).value();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return super.visit(node);
//	}
//
//	@Override
//	public void endVisit(MethodInvocation node) {
//		//Not needed
//	}
//	
//	public static String visitSuperMethodInvocationCallback = AstVisitor.class.getName() + "visit(SuperMethodInvocation)";
//	@Override
//	public boolean visit(SuperMethodInvocation node) {
//		try {
//			new SmalltalkRequest(visitSuperMethodInvocationCallback, this, node).value();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return true;
//	}
//	
//	@Override
//	public void endVisit(SuperMethodInvocation node) {
//		//Not needed
//	}
//	
//	public static String visitConstructorInvocationCallback = AstVisitor.class.getName() + "visit(ConstructorInvocation)";
//	@Override
//	public boolean visit(ConstructorInvocation node) {
//		try {
//			new SmalltalkRequest(visitConstructorInvocationCallback, this, node).value();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return true;
//	}
//	
//	@Override
//	public void endVisit(ConstructorInvocation node) {
//		//Not needed
//	}
//
//	public static String visitSuperConstructorInvocationCallback = AstVisitor.class.getName() + "visit(SuperConstructorInvocation)";
//	@Override
//	public boolean visit(SuperConstructorInvocation node) {
//		try {
//			new SmalltalkRequest(visitSuperConstructorInvocationCallback, this, node).value();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return true;
//	}
//	
//	public static String visitClassInstanceCreationCallback = AstVisitor.class.getName() + "visit(ClassInstanceCreation)";
//	@Override
//	public boolean visit(ClassInstanceCreation node) {
//		try {
//			new SmalltalkRequest(visitClassInstanceCreationCallback, this, node).value();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return true;
//	}
//	
//	////////ACCESSES
//	public static String visitFieldAccessCallback = AstVisitor.class.getName() + "visit(FieldAccess)";
//	@Override
//	public boolean visit(FieldAccess node) {
//		try {
//			new SmalltalkRequest(visitFieldAccessCallback, this, node).value();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return true;
//	}
//
//	@Override
//	public void endVisit(FieldAccess node) {
//		//Not needed
//	}
//
//	public static String visitAssignmentCallback = AstVisitor.class.getName() + "visit(Assignment)";
//	@Override
//	public boolean visit(Assignment node) {
//		try {
//			new SmalltalkRequest(visitAssignmentCallback, this, node).value();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return true;
//	}
//	
//	@Override
//	public void endVisit(Assignment node) {
//		//Not needed
//	}

}
