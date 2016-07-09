package org.moosetechnology.jdt2famix.injava;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.FileASTRequestor;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IPackageBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.moosetechnology.jdt2famix.Importer;
import org.moosetechnology.model.famix.*;
import org.moosetechnology.model.famix.Class;
import org.moosetechnology.model.java.JavaModel;

import ch.akuhn.fame.MetaRepository;
import ch.akuhn.fame.Repository;

public class InJavaImporter extends Importer {

	private Namespace unknownNamespace;
	private Map<String,Namespace> namespaces;
	public Map<String,Namespace> getNamespaces() { return namespaces; }
	
	private Map<String, Type> types;
	public Map<String, Type> getTypes() { return types; }

	private Map<String, Method> methods;
	public Map<String, Method> getMethods() { return methods; }

	private Map<String, Parameter> parameters;
	public Map<String, Parameter> getParameters() { return parameters; }
	
	Repository repository;
	public Repository getRepository() { return repository; }
	
	/*
	 * This is a structure that keeps track of the current stack of containers
	 * It is particularly useful when we deal with inner or anonymous classes
	 */ 
	private Deque<ContainerEntity> containerStack = new ArrayDeque<ContainerEntity>();
	public Deque<ContainerEntity> getContainerStack() { return containerStack; }
	public void pushOnContainerStack(ContainerEntity namespace) {this.containerStack.push(namespace);}
	public ContainerEntity popFromContainerStack() {return this.containerStack.pop();}
	public ContainerEntity topOfContainerStack() {return this.containerStack.peek();}

	
	public InJavaImporter() {
		 MetaRepository metaRepository = new MetaRepository();
		 FAMIXModel.importInto(metaRepository);
		 JavaModel.importInto(metaRepository);
		 repository = new Repository(metaRepository);
		 
		 types = new HashMap<String, Type>();
		 namespaces = new HashMap<String, Namespace>();
		 methods = new HashMap<String, Method>();
		 parameters = new HashMap<String, Parameter>();
	}
	
	
	@Override
	protected FileASTRequestor getRequestor() {
		return new AstRequestor(this);
	}


	//NAMESPACES
	public Namespace ensureNamespaceFromPackageBinding(IPackageBinding binding) {
		String packageName = binding.getName();
		if (namespaces.containsKey(packageName)) 
			return namespaces.get(packageName);
		else {
			Namespace namespace = createNamespaceNamed(packageName);
			namespaces.put(packageName, namespace);
			repository.add(namespace);
			return namespace;
		}
	}

	private Namespace createNamespaceNamed(String k) {
		Namespace namespace = new Namespace();
		namespace.setName(k);
		namespace.setIsStub(true);
		return namespace;
	}
	
	private ContainerEntity ensureContainerEntityForTypeBinding(ITypeBinding binding) {
		if (binding.getPackage() == null)
			return unknownNamespace();
		return ensureNamespaceFromPackageBinding(binding.getPackage());
	}
	
	private Namespace unknownNamespace() {
		if (unknownNamespace == null) {
			unknownNamespace = new Namespace();
			unknownNamespace.setName("__UNKNOWN__");
		}
		return unknownNamespace;
	}

	//TYPES
	public Type ensureTypeFromTypeBinding(ITypeBinding binding) {
		String qualifiedName = binding.getQualifiedName();
		if (types.containsKey(qualifiedName)) return types.get(qualifiedName);
		Type type = createTypeFromTypeBinding(binding);
		types.put(qualifiedName, type);
		type.setName(binding.getName());
		type.setContainer(ensureContainerEntityForTypeBinding(binding));
		if (binding.getSuperclass() != null) 
			createInheritanceFromSubtypeToSuperTypeBinding(type, binding);
		for (ITypeBinding interfaceBinding : binding.getInterfaces()) {
			createInheritanceFromSubtypeToSuperTypeBinding(type, interfaceBinding);
		}
		repository.add(type);
		return type;
	}

	private Type createTypeFromTypeBinding(ITypeBinding binding) {
		Type type;
		if (binding.isPrimitive())
			type = new PrimitiveType();
		else {
			Class clazz = new Class();
			clazz.setIsInterface(binding.isInterface());
			type = clazz;
		}
		type.setName(binding.getName());
		type.setIsStub(true);
		extractBasicModifiersFromBinding(binding.getModifiers(), type);
		return type;
	}
	
	//METHODS
	public Method ensureMethodFromMethodBinding(IMethodBinding binding) {
		//FIXME: the parametersString contains a , too many 
		String parametersString = Arrays
				.stream(binding.getParameterTypes())
				.map(p -> (String) p.getQualifiedName())
				.reduce("", (l, r) -> l + ", " + r);
		String methodName = binding.getName();
		String signature = methodName + "(" + parametersString + ")";
		String fullMethodName = binding.getDeclaringClass().getQualifiedName() + "." + signature;
		if (methods.containsKey(fullMethodName)) 
			return methods.get(fullMethodName);
		Method method = new Method();
		method.setName(methodName);
		method.setSignature(signature);
		method.setIsStub(true);
		method.setParentType(ensureTypeFromTypeBinding(binding.getDeclaringClass()));
		if (binding.isConstructor()) 
			method.setKind("constructor");
		ITypeBinding returnType = binding.getReturnType();
		if ((returnType != null) && !(returnType.isPrimitive() && returnType.getName().equals("void")))
			//we do not want to set void as a return type
			method.setDeclaredType(ensureTypeFromTypeBinding(returnType));
		extractBasicModifiersFromBinding(binding.getModifiers(), method);
		return method;
	}
	
	public Parameter ensureParameterFromSingleVariableDeclaration(SingleVariableDeclaration variableDeclaration,
			Method method, IMethodBinding methodBinding) {
		String name = variableDeclaration.getName().toString();
		String fullName = methodBinding.getDeclaringClass().getName() + "." + method.getSignature() + "." + name;
		if (parameters.containsKey(fullName)) 
			return parameters.get(fullName);
		Parameter parameter = new Parameter();
		parameter.setName(name);
		parameter.setParentBehaviouralEntity(method);
		parameter.setDeclaredType(ensureTypeFromTypeBinding(variableDeclaration.getType().resolveBinding()));
		return parameter;
	}

	//INHERITANCE
	private Inheritance createInheritanceFromSubtypeToSuperTypeBinding(Type subType,
			ITypeBinding superBinding) {
		Inheritance inheritance = new Inheritance();
		inheritance.setSuperclass(ensureTypeFromTypeBinding(superBinding)); 
		inheritance.setSubclass(subType);
		repository.add(inheritance);
		return inheritance;
	}
	
	//UTILS
	private void extractBasicModifiersFromBinding(int modifiers, NamedEntity entity) {
		Boolean publicModifier = Modifier.isPublic(modifiers);
		Boolean protectedModifier = Modifier.isProtected(modifiers);
		Boolean privateModifier = Modifier.isPrivate(modifiers);
		if (publicModifier )
			entity.addModifiers("public");
		if (protectedModifier)
			entity.addModifiers("protected");
		if (privateModifier)
			entity.addModifiers("private");
		if (!(publicModifier || protectedModifier || privateModifier))
			entity.addModifiers("package");
		if (Modifier.isFinal(modifiers))
			entity.addModifiers("final");
		if (Modifier.isAbstract(modifiers))
			entity.addModifiers("abstract");
		if (Modifier.isNative(modifiers))
			entity.addModifiers("native");
		if (Modifier.isSynchronized(modifiers))
			entity.addModifiers("synchronized");
		if (Modifier.isTransient(modifiers))
			entity.addModifiers("transient");
		if (Modifier.isVolatile(modifiers))
			entity.addModifiers("volatile");
		if (Modifier.isStatic(modifiers))
			entity.addModifiers("static");
	}
	
		
	//EXPORT
	public void exportMSE(String fileName) {
		try {
			repository.exportMSE(new FileWriter(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}