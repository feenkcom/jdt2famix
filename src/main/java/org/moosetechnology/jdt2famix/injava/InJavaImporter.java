package org.moosetechnology.jdt2famix.injava;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.FileASTRequestor;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IPackageBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.moosetechnology.jdt2famix.Importer;
import org.moosetechnology.model.famix.*;
import org.moosetechnology.model.famix.Class;
import org.moosetechnology.model.java.JavaModel;

import ch.akuhn.fame.MetaRepository;
import ch.akuhn.fame.Repository;

/**
 * The core class that holds the logic of creating the model
 * It looks like a god class, but it is convenient to have most of the logic here
 * 
 * There are two main types of methods
 * 1. ensure methods are those that always return the same instance of a named entity for the same qualified name.
 *    These are important for creating the graph 
 * 2. create methods create the instances both for named entities and for other types (such as associations)
 * 
 * @author girba
 *
 */
public class InJavaImporter extends Importer {

	private Namespace unknownNamespace;
	private Map<String,Namespace> namespaces;
	public Map<String,Namespace> getNamespaces() { return namespaces; }
	
	private Type unknownType;
	private Map<String, Type> types;
	public Map<String, Type> getTypes() { return types; }

	private Map<String, Method> methods;
	public Map<String, Method> getMethods() { return methods; }

	private Map<String, Attribute> attributes;
	public Map<String, Attribute> getAttributes() { return attributes; }

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
		 attributes = new HashMap<String, Attribute>();
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
	
	Namespace unknownNamespace() {
		if (unknownNamespace == null) {
			unknownNamespace = new Namespace();
			unknownNamespace.setName("__UNKNOWN__");
			unknownNamespace.setIsStub(true);
			namespaces.put(getQualifiedName(unknownNamespace), unknownNamespace);
		}
		return unknownNamespace;
	}

	//TYPES
	public Type ensureTypeFromTypeBinding(ITypeBinding binding) {
		if (binding == null) return unknownType();
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

	/**
	 * All types should be ensured first via this method.
	 * We first check to see if the binding is resolvable (not null)
	 * If it is not null we ensure the type from the binding (the happy case)
	 * If the type is null we recover what we know (for example, the name of a simple type)
	 * In the worst case we return the {@link #unknownType()} 
	 */
	private Type ensureTypeFromDomType(org.eclipse.jdt.core.dom.Type domType) {
		ITypeBinding binding = domType.resolveBinding();
		if (binding != null)
			return ensureTypeFromTypeBinding(binding);
		if (domType.isSimpleType())
			return typeNamedInUnknownNamespace(((SimpleType) domType).getName().toString());
		return unknownType();
	}
	
	/**
	 * This is the type we used as a null object whenever we need to reference a type  
	 */
	Type unknownType() {
		if (unknownType == null) {
			unknownType = typeNamedInUnknownNamespace("__UNKNOWN__");
		}
		return unknownType;
	}
	
	private Type typeNamedInUnknownNamespace(String name) {
		Type type = new Type();
		type.setName(name);
		type.setContainer(unknownNamespace());
		type.setIsStub(true);
		String qualifiedName = getQualifiedName(type);
		if (types.containsKey(qualifiedName))
			return types.get(qualifiedName);
		else {
			types.put(getQualifiedName(type), type);
			return type;
		}
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
		String qualifiedName = binding.getDeclaringClass().getQualifiedName() + "." + signature;
		if (methods.containsKey(qualifiedName)) 
			return methods.get(qualifiedName);
		Method method = new Method();
		methods.put(qualifiedName, method);
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
	
	public Method ensureMethodFromMethodDeclaration(MethodDeclaration node) {
		String parametersString = Arrays
				.stream(node.parameters().toArray())
				.map(p -> (String) ((SingleVariableDeclaration) p).getType().toString())
				.reduce("", (l, r) -> l + ", " + r);
		String methodName = node.getName().toString();
		String signature = methodName + "(" + parametersString + ")";
		String qualifiedName = getQualifiedName(topOfContainerStack()) + "." + signature;
		if(methods.containsKey(qualifiedName))
			return methods.get(qualifiedName);
		Method method = new Method();
		method.setName(methodName);
		method.setSignature(signature);
		method.setParentType((Type) topOfContainerStack());
		method.setDeclaredType(ensureTypeFromDomType(node.getReturnType2()));
		method.setIsStub(true);
		return method;
	}

	public Parameter ensureParameterFromSingleVariableDeclaration(SingleVariableDeclaration variableDeclaration,
			Method method) {
		String name = variableDeclaration.getName().toString();
		String qualifiedName = getQualifiedName(method) + "." + name;
		if (parameters.containsKey(qualifiedName)) 
			return parameters.get(qualifiedName);
		Parameter parameter = new Parameter();
		parameters.put(qualifiedName, parameter);
		parameter.setName(name);
		parameter.setParentBehaviouralEntity(method);
		parameter.setDeclaredType(ensureTypeFromDomType(variableDeclaration.getType()));
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
		
	//ATTRIBUTES
	
	/**
	 * We pass both the fragment and the field because we might need the field type when the binding cannot be resolved
	 * @return
	 */
	public Attribute ensureAttributeForFragment(VariableDeclarationFragment fragment, FieldDeclaration field) {
		IVariableBinding binding = fragment.resolveBinding();
		Attribute attribute;
		if (binding == null)
			attribute = ensureAttributeFromFragmentIntoParentType(fragment, field, (Type) this.topOfContainerStack());
		else {
			attribute = ensureAttributeForVariableBinding(binding);
			extractBasicModifiersFromBinding(binding.getModifiers(), attribute);
		}
		attribute.setIsStub(true);
		return attribute;
	}
	private Attribute ensureAttributeForVariableBinding(IVariableBinding binding) {
		String name = binding.getName();
		String qualifiedName = binding.getDeclaringClass().getQualifiedName() + '.' + name;
		if (attributes.containsKey(qualifiedName)) 
			return attributes.get(qualifiedName);
		Attribute attribute = new Attribute();
		attributes.put(qualifiedName, attribute);
		attribute.setName(name);
		attribute.setParentType(ensureTypeFromTypeBinding(binding.getDeclaringClass()));
		attribute.setDeclaredType(ensureTypeFromTypeBinding(binding.getType()));		
		return attribute;
	}
	private Attribute ensureAttributeFromFragmentIntoParentType(
			VariableDeclarationFragment fragment,
			FieldDeclaration field,
			Type parentType) {
		String name = fragment.getName().toString();
		String qualifiedName = getQualifiedName(parentType);
		if (attributes.containsKey(qualifiedName)) 
			return attributes.get(qualifiedName);
		Attribute attribute = new Attribute();
		attribute.setName(name);
		attribute.setParentType(parentType);
		attribute.setDeclaredType(ensureTypeFromDomType(field.getType()));
		return attribute;
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

	private String getQualifiedName(Method method) {
		return getQualifiedName(method.getParentType()) + "." + method.getSignature();
	}
	
	private String getQualifiedName(Type type) {
		return getQualifiedName(type.getContainer()) + "." + type.getName();
	}

	private String getQualifiedName(ContainerEntity container) {
		return container.getName();
	}

	//EXPORT
	public void exportMSE(String fileName) {
		try {
			repository.exportMSE(new FileWriter(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * We pass the dom type here because of the funny types of JDT 
	 */
	public LocalVariable ensureLocalVariableFromFragment(
			VariableDeclarationFragment fragment,
			org.eclipse.jdt.core.dom.Type type) {
		LocalVariable localVariable = new LocalVariable();
		localVariable.setName(fragment.getName().toString());
		localVariable.setDeclaredType(ensureTypeFromDomType(type));
		//CHECK: We might want to recover the modifiers (final) 
		localVariable.setIsStub(true);
		((Method) topOfContainerStack()).addLocalVariables(localVariable);
		return localVariable;
	}

}