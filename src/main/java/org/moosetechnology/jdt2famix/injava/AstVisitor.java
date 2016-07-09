package org.moosetechnology.jdt2famix.injava;


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
import org.moosetechnology.model.famix.Namespace;
import org.moosetechnology.model.famix.Type;
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
		Type type = importer.ensureTypeFromTypeBinding(node.resolveBinding());
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

//	public static String visitAnonymousClassDeclarationCallback = AstVisitor.class.getName() + "visit(AnonymousClassDeclaration)";
//	@Override
//	public boolean visit(AnonymousClassDeclaration node) {
//		try {
//			new SmalltalkRequest(visitAnonymousClassDeclarationCallback, this, node).value();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return true;
//	}
//	
//	public static String endVisitAnonymousClassDeclarationCallback = AstVisitor.class.getName() + "endVisit(AnonymousClassDeclaration)";
//)	@Override
//	public void endVisit(AnonymousClassDeclaration node) {
//		try {
//			new SmalltalkRequest(endVisitAnonymousClassDeclarationCallback, this, node).value();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//	}
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
			method = new Method();

		node.parameters().
			stream().
			forEach(p -> 
				importer.ensureParameterFromSingleVariableDeclaration((SingleVariableDeclaration) p, method, binding));
		
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
