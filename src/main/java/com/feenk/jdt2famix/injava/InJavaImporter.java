package com.feenk.jdt2famix.injava;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.FileASTRequestor;
import org.eclipse.jdt.core.dom.IAnnotationBinding;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMemberValuePairBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IPackageBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import ch.akuhn.fame.MetaRepository;
import ch.akuhn.fame.Repository;

import com.feenk.jdt2famix.Famix;
import com.feenk.jdt2famix.Importer;
import com.feenk.jdt2famix.JavaFiles;
import com.feenk.jdt2famix.model.famix.Access;
import com.feenk.jdt2famix.model.famix.AnnotationInstance;
import com.feenk.jdt2famix.model.famix.AnnotationInstanceAttribute;
import com.feenk.jdt2famix.model.famix.AnnotationType;
import com.feenk.jdt2famix.model.famix.AnnotationTypeAttribute;
import com.feenk.jdt2famix.model.famix.Attribute;
import com.feenk.jdt2famix.model.famix.Class;
import com.feenk.jdt2famix.model.famix.Comment;
import com.feenk.jdt2famix.model.famix.ContainerEntity;
import com.feenk.jdt2famix.model.famix.DeclaredException;
import com.feenk.jdt2famix.model.famix.Enum;
import com.feenk.jdt2famix.model.famix.EnumValue;
import com.feenk.jdt2famix.model.famix.FAMIXModel;
import com.feenk.jdt2famix.model.famix.FileAnchor;
import com.feenk.jdt2famix.model.famix.Inheritance;
import com.feenk.jdt2famix.model.famix.Invocation;
import com.feenk.jdt2famix.model.famix.JavaSourceLanguage;
import com.feenk.jdt2famix.model.famix.LocalVariable;
import com.feenk.jdt2famix.model.famix.Method;
import com.feenk.jdt2famix.model.famix.NamedEntity;
import com.feenk.jdt2famix.model.famix.Namespace;
import com.feenk.jdt2famix.model.famix.Parameter;
import com.feenk.jdt2famix.model.famix.ParameterType;
import com.feenk.jdt2famix.model.famix.ParameterizableClass;
import com.feenk.jdt2famix.model.famix.ParameterizedType;
import com.feenk.jdt2famix.model.famix.PrimitiveType;
import com.feenk.jdt2famix.model.famix.SourcedEntity;
import com.feenk.jdt2famix.model.famix.StructuralEntity;
import com.feenk.jdt2famix.model.famix.Type;
import com.feenk.jdt2famix.model.famix.UnknownVariable;
import com.feenk.jdt2famix.model.java.JavaModel;

/**
 * The core class that holds the logic of creating the model
 * It looks like a god class, but it is convenient to have most of the logic here
 * 
 * There are two main kinds of methods
 * 1. ensure methods are those that always return the same instance of a named entity for the same qualified name.
 *    These are important for creating the graph.
 * 2. create methods always create new instances entities (both named and other types, such as associations)
 * 
 * @author girba
 */
public class InJavaImporter extends Importer {
	
	private static final char NAME_SEPARATOR = '.';
	public static final String INITIALIZER_NAME = "<init>";
	public static final String UNKNOWN_NAME = "__UNKNOWN__";
	public static final String CONSTRUCTOR_KIND = "constructor";
	private static final String INITIALIZER_KIND = "initializer";
	
	private Namespace unknownNamespace;
	private Type unknownType;
	private UnknownVariable unknownVariable;
	
	private Repository repository;
	public Repository repository() { return repository; }
	
	private NamedEntityAccumulator<Namespace> namespaces;
	public NamedEntityAccumulator<Namespace> namespaces() {return namespaces;}

	private NamedEntityAccumulator<Type> types; 
	public NamedEntityAccumulator<Type> types() {return types;}

	private NamedEntityAccumulator<Method> methods;
	public NamedEntityAccumulator<Method> methods() {return methods;}

	private NamedEntityAccumulator<Attribute> attributes;
	public NamedEntityAccumulator<Attribute> attributes() {return attributes;}

	private NamedEntityAccumulator<Parameter> parameters;
	
	/**
	 * This is a structure that keeps track of the current stack of containers
	 * It is particularly useful when we deal with inner or anonymous classes
	 */ 
	private Deque<ContainerEntity> containerStack = new ArrayDeque<ContainerEntity>();
	public void pushOnContainerStack(ContainerEntity namespace) {this.containerStack.push(namespace);}
	public ContainerEntity popFromContainerStack() {return this.containerStack.pop();}
	public ContainerEntity topOfContainerStack() {return this.containerStack.peek();}
	@SuppressWarnings("unchecked")
	public <T> T topFromContainerStack(java.lang.Class<T> clazz) { 
		for (Iterator<ContainerEntity> iterator = containerStack.iterator(); iterator.hasNext();) {
			ContainerEntity next = iterator.next();
			if (clazz.isInstance(next)) return (T) next;
		}
		return null;
	}
	
	public InJavaImporter() {
		MetaRepository metaRepository = new MetaRepository();
		FAMIXModel.importInto(metaRepository);
		JavaModel.importInto(metaRepository);
		repository = new Repository(metaRepository);
		repository.add(new JavaSourceLanguage());
		
		namespaces = new NamedEntityAccumulator<Namespace>(repository);
		types = new NamedEntityAccumulator<Type>(repository);
		methods = new NamedEntityAccumulator<Method>(repository);
		attributes = new NamedEntityAccumulator<Attribute>(repository);
		parameters = new NamedEntityAccumulator<Parameter>(repository);
	}
	
	@Override
	protected FileASTRequestor getRequestor(JavaFiles allJavaFiles) {
		return new AstRequestor(this, allJavaFiles);
	}

	
	//NAMESPACE
	
	public Namespace ensureNamespaceFromPackageBinding(IPackageBinding binding) {
		return ensureNamespaceNamed(binding.getName());
	}
	
	Namespace ensureNamespaceNamed(String packageName) {
		if (namespaces.has(packageName)) 
			return namespaces.named(packageName);
		else
			return namespaces.add(packageName, createNamespaceNamed(packageName));
	}

	private Namespace createNamespaceNamed(String qualifiedName) {
		int lastIndexOfDot = qualifiedName.lastIndexOf(".");
		Namespace namespace = new Namespace();
		namespace.setIsStub(true);
		if (lastIndexOfDot <= 0)
			namespace.setName(qualifiedName);
		else {
			/* Java packages are not nested, even though they look like they are.
			 * But, in Famix, namespaces are nested. So we create nesting based on the . separator
			 */
			namespace.setName(qualifiedName.substring(lastIndexOfDot+1));
			Namespace parentNamespace = ensureNamespaceNamed(qualifiedName.substring(0, lastIndexOfDot));
			namespace.setParentScope(parentNamespace);
		}
		return namespace;
	}
	
	private ContainerEntity ensureContainerEntityForTypeBinding(ITypeBinding binding) {
		if (binding.getDeclaringClass() != null)
			return ensureTypeFromTypeBinding(binding.getDeclaringClass());
		if (binding.getPackage() != null)
			return ensureNamespaceFromPackageBinding(binding.getPackage());
		return unknownNamespace();
	}

	public Namespace unknownNamespace() {
		if (unknownNamespace == null) {
			unknownNamespace = new Namespace();
			unknownNamespace.setName(UNKNOWN_NAME);
			unknownNamespace.setIsStub(true);
			namespaces.add(Famix.qualifiedNameOf(unknownNamespace), unknownNamespace);
		}
		return unknownNamespace;
	}
	
	
	//TYPE
	
	public Type ensureTypeFromTypeBinding(ITypeBinding binding) {
		String qualifiedName = binding.getQualifiedName();
		if (types.has(qualifiedName)) return types.named(qualifiedName);
		Type type = createTypeFromTypeBinding(binding);
		type.setName(binding.getName());
		types.add(qualifiedName, type);
		type.setIsStub(true);
		extractBasicModifiersFromBinding(binding.getModifiers(), type);
		type.setContainer(ensureContainerEntityForTypeBinding(binding));
		if (binding.getSuperclass() != null) 
			createInheritanceFromSubtypeToSuperTypeBinding(type, binding.getSuperclass());
		for (ITypeBinding interfaceBinding : binding.getInterfaces()) {
			createInheritanceFromSubtypeToSuperTypeBinding(type, interfaceBinding);
		}
		if (binding.isParameterizedType()) {
			/* This if duplicates the condition from the create method because we want to break
			 * possible infinite loops induced by the below ensure calls.
			 * This is achieved by having this condition after the addition of the type in the types map.
			 */
			ParameterizedType parameterizedType = ((ParameterizedType) type);
			if (ensureTypeFromTypeBinding(binding.getErasure()) instanceof ParameterizableClass) 
				parameterizedType.setParameterizableClass((ParameterizableClass) ensureTypeFromTypeBinding(binding.getErasure()));
			List<Type> arguments = Stream.of(binding.getTypeArguments()).map(arg -> ensureTypeFromTypeBinding(arg)).collect(Collectors.toList());
			parameterizedType.setArguments(arguments);
		}
		if (binding.isGenericType()) {
			ParameterizableClass parameterizableClass = (ParameterizableClass) type;
			Stream.of(binding.getTypeParameters()).forEach(p -> createParameterType(p.getName().toString(), parameterizableClass));
		}
		createAnnotationInstancesToEntityFromAnnotationBinding(type, binding.getAnnotations());
		return type;
	}

	/**
	 * Main method for creating annotation instances
	 */
	private void createAnnotationInstancesToEntityFromAnnotationBinding(NamedEntity entity, IAnnotationBinding[] annotations) {
		for (IAnnotationBinding annotationInstanceBinding : annotations) {
			ITypeBinding annotationTypeBinding = annotationInstanceBinding.getAnnotationType();
			AnnotationInstance annotationInstance = new AnnotationInstance();
			annotationInstance.setAnnotatedEntity(entity);
			if (annotationInstanceBinding != null) {
				createAnnotationInstanceFromAnnotationInstanceBinding(annotationInstanceBinding, annotationTypeBinding,
						annotationInstance);
			}
			repository.add(annotationInstance);
		}
	}
	private void createAnnotationInstanceFromAnnotationInstanceBinding(IAnnotationBinding annotationInstanceBinding,
			ITypeBinding annotationTypeBinding,
			AnnotationInstance annotationInstance) {
		AnnotationType annotationType = (AnnotationType) ensureTypeFromTypeBinding(annotationTypeBinding);
		annotationInstance.setAnnotationType(annotationType);
		IMemberValuePairBinding[] allMemberValuePairs = annotationInstanceBinding.getAllMemberValuePairs();
		for (IMemberValuePairBinding memberValueBinding : allMemberValuePairs) {
			AnnotationInstanceAttribute annotationInstanceAttribute = new AnnotationInstanceAttribute();
			annotationInstanceAttribute.setValue(annotationInstanceAttributeValueString(memberValueBinding.getValue()));
			annotationInstance.addAttributes(annotationInstanceAttribute);
			repository.add(annotationInstanceAttribute);

			annotationInstanceAttribute.setAnnotationTypeAttribute(ensureAnnotationTypeAttribute(annotationType, memberValueBinding.getName()));
		}
	}
	private String annotationInstanceAttributeValueString(Object value) {
		if (value == null)
			/*
			 * Theoretically, this should not happen because the Java compiler prevents you from setting null to an enum constant.
			 * However, we can still get a null during import.
			 * For example, when referencing something like attribute=MyClass.class, without MyClass being available,
			 * we get a null as value.   
			 */
			return "null";
		if (value instanceof ITypeBinding)
			return ((ITypeBinding)value).getName() + ".class";
		if (value instanceof Object[]) {
			Object[] array = (Object[]) value;
			StringJoiner signatureJoiner = new StringJoiner(", ", "{", "}");
			Arrays
			.stream(array)
			.forEach( object -> signatureJoiner.add(annotationInstanceAttributeValueString(object)) );
			return signatureJoiner.toString();
		}
		return value.toString();
	}
	private AnnotationTypeAttribute ensureAnnotationTypeAttribute(Type parentType, String name) {
		String qualifiedName = Famix.qualifiedNameOf(parentType) + NAME_SEPARATOR + name;
		if (attributes().has(qualifiedName))
			return (AnnotationTypeAttribute) attributes().named(qualifiedName);
		AnnotationTypeAttribute attribute = new AnnotationTypeAttribute();
		attribute.setName(name);
		attribute.setParentType(parentType);
		attributes.add(qualifiedName, attribute);
		return attribute;
	}
	
	Type createTypeFromTypeBinding(ITypeBinding binding) {
		if (binding.isPrimitive())
			return new PrimitiveType();
		if (binding.isParameterizedType())
			return new ParameterizedType();
		if (binding.isGenericType()) {
			ParameterizableClass parameterizableClass = new ParameterizableClass();
			parameterizableClass.setIsInterface(binding.isInterface());
			return parameterizableClass;
		}
		if (binding.isEnum())
			return new Enum();
		if (binding.isArray())
			return createTypeFromTypeBinding(binding.getElementType());
		if (binding.isAnnotation())
			return new AnnotationType();
		Class clazz = new Class();
		clazz.setIsInterface(binding.isInterface());
		return clazz;
	}
	
	private ParameterType createParameterType(String name, Type container) {
		ParameterType parameterType = new ParameterType();
		parameterType.setName(name);
		parameterType.setContainer(container);
		return parameterType;
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
			return ensureTypeNamedInUnknownNamespace(((SimpleType) domType).getName().toString());
		if (domType.isParameterizedType())
			return ensureTypeNamedInUnknownNamespace(((org.eclipse.jdt.core.dom.ParameterizedType) domType).getType().toString());
		return unknownType();
	}
	
	/**
	 * This is the type we used as a null object whenever we need to reference a type  
	 */
	public Type unknownType() {
		if (unknownType == null) {
			unknownType = ensureTypeNamedInUnknownNamespace(UNKNOWN_NAME);
		}
		return unknownType;
	}
	
	public Type ensureTypeNamedInUnknownNamespace(String name) {
		Type type = new Type();
		type.setName(name);
		type.setContainer(unknownNamespace());
		type.setIsStub(true);
		String qualifiedName = Famix.qualifiedNameOf(type);
		if (types.has(qualifiedName))
			return types.named(qualifiedName);
		else {
			types.add(Famix.qualifiedNameOf(type), type);
			return type;
		}
	}

	public Type ensureTypeFromAnonymousDeclaration(
			AnonymousClassDeclaration node) {
		ITypeBinding binding = node.resolveBinding();
		Type type = createTypeFromTypeBinding(binding);
		type.setContainer(topOfContainerStack());
		type.setName("$" + topOfContainerStack().getTypes().size());
		if (node.getParent() instanceof ClassInstanceCreation)
			createInheritanceFromSubtypeToSuperDomType(type, ((ClassInstanceCreation) node.getParent()).getType());
		if (node.getParent() instanceof EnumConstantDeclaration)
			createInheritanceFromSubtypeToSuperType(type, topFromContainerStack(Enum.class));
		types.add(Famix.qualifiedNameOf(type), type);
		return type;
	}

	
	//INHERITANCE
	
	/**
	 * We use this one when we have the super type binding
	 */
	private Inheritance createInheritanceFromSubtypeToSuperTypeBinding(Type subType,
			ITypeBinding superBinding) {
		return createInheritanceFromSubtypeToSuperType(subType, ensureTypeFromTypeBinding(superBinding)); 
	}

	/**
	 * When we cannot resolve the binding of the superclass of a class declaration,
	 * we still want to create a {@link Type} with the best available information
	 * from {@link org.eclipse.jdt.core.dom.Type}  
	 */
	public Inheritance createInheritanceFromSubtypeToSuperDomType(Type subType,
			org.eclipse.jdt.core.dom.Type type) {
		return createInheritanceFromSubtypeToSuperType(subType, ensureTypeFromDomType(type));
	}

	/**
	 * We use this one when we have the super type 
	 */
	private Inheritance createInheritanceFromSubtypeToSuperType(Type subType,
			Type superType) {
		Inheritance inheritance = new Inheritance();
		inheritance.setSuperclass(superType); 
		inheritance.setSubclass(subType);
		repository.add(inheritance);
		return inheritance;
	}

		
	//METHOD
	
	/**
	 * We use this one when we know that we are aiming for the top of the container stack
	 * This is important in the case of anonymous classes which have empty names in JDT
	 */
	public Method ensureMethodFromMethodBindingToCurrentContainer(IMethodBinding binding) {
		return ensureMethodFromMethodBinding(binding, (Type) topOfContainerStack());
	}
	
	public Method ensureMethodFromMethodBinding(IMethodBinding binding) {
		/*	binding.getDeclaringClass() might be null when you invoke a method from a class that is not in the path
			It looks like calling getMethodDeclaration is more robust. */
		return ensureMethodFromMethodBinding(binding.getMethodDeclaration(), ensureTypeFromTypeBinding(binding.getMethodDeclaration().getDeclaringClass()));
	}

	public Method ensureMethodFromMethodBinding(IMethodBinding binding, Type parentType) {
		StringJoiner signatureJoiner = new StringJoiner(", ", "(", ")");
		Arrays
			.stream(binding.getParameterTypes())
			.forEach( p -> signatureJoiner.add((String) p.getQualifiedName()) );
		String methodName = binding.getName();
		String signature = methodName + signatureJoiner.toString();
		return ensureBasicMethod(
				methodName, 
				signature, 
				parentType,
				m -> setUpMethodFromMethodBinding(m, binding));
	}
	private void setUpMethodFromMethodBinding(Method method, IMethodBinding binding) {
		if (binding.isConstructor()) 
			method.setKind(CONSTRUCTOR_KIND);
		ITypeBinding returnType = binding.getReturnType();
		if ((returnType != null) && !(returnType.isPrimitive() && returnType.getName().equals("void")))
			//we do not want to set void as a return type
			method.setDeclaredType(ensureTypeFromTypeBinding(returnType));
		extractBasicModifiersFromBinding(binding.getModifiers(), method);
		if (Modifier.isStatic(binding.getModifiers()))
			method.setHasClassScope(true);
		createAnnotationInstancesToEntityFromAnnotationBinding(method, binding.getAnnotations());
	}
	
	public Method ensureMethodFromMethodDeclaration(MethodDeclaration node) {
		StringJoiner signatureJoiner = new StringJoiner(", ", "(", ")");
		Arrays
			.stream(node.parameters().toArray())
			.forEach( p -> signatureJoiner.add((String) ((SingleVariableDeclaration) p).getType().toString()) );
		String methodName = node.getName().toString();
		String signature = methodName + signatureJoiner.toString();		
		return ensureBasicMethod(
				methodName, 
				signature, 
				(Type) topOfContainerStack(),
				m -> setUpMethodFromMethodDeclaration(m, node));
	}
	private void setUpMethodFromMethodDeclaration(Method method, MethodDeclaration node) {
		if (node.getReturnType2() != null)
			method.setDeclaredType(ensureTypeFromDomType(node.getReturnType2()));
	}
	
	public Method ensureInitializerMethod() {
		return ensureBasicMethod(
				INITIALIZER_NAME, 
				INITIALIZER_NAME, 
				(Type) topOfContainerStack(),
				m -> setUpInitializerMethod(m));
	}

	private void setUpInitializerMethod(Method method) {
		method.setKind(INITIALIZER_KIND);
		method.setIsStub(false);
	}
	
	public Method ensureBasicMethod(String methodName, String signature, Type parentType, Consumer<Method> ifAbsent) {
		String qualifiedName = Famix.qualifiedNameOf(parentType) + NAME_SEPARATOR + signature;
		if(methods.has(qualifiedName))
			return methods.named(qualifiedName);
		Method method = new Method();
		method.setName(methodName);
		methods.add(qualifiedName, method);
		method.setSignature(signature);
		method.setIsStub(true);
		method.setParentType(parentType);
		ifAbsent.accept(method);
		return method;
	}
	
	//PARAMETER
	
	public Parameter ensureParameterFromSingleVariableDeclaration(SingleVariableDeclaration variableDeclaration,
			Method method) {
		String name = variableDeclaration.getName().toString();
		String qualifiedName = Famix.qualifiedNameOf(method) + NAME_SEPARATOR + name;
		if (parameters.has(qualifiedName)) 
			return parameters.named(qualifiedName);
		Parameter parameter = new Parameter();
		parameters.add(qualifiedName, parameter);
		parameter.setName(name);
		parameter.setParentBehaviouralEntity(method);
		parameter.setDeclaredType(ensureTypeFromDomType(variableDeclaration.getType()));
		IVariableBinding binding = variableDeclaration.resolveBinding();
		if (binding != null)
			createAnnotationInstancesToEntityFromAnnotationBinding(parameter, binding.getAnnotations());
		return parameter;
	}

	public Parameter ensureParameterWithinCurrentMethodFromVariableBinding(IVariableBinding binding) {
		Method method = (Method) topOfContainerStack();
		Optional<Parameter> possibleParameter = method.getParameters()
			.stream()
			.filter(p -> p.getName().equals(binding.getName()))
			.findAny();
		if (possibleParameter.isPresent())
			return possibleParameter.get();
		return null;
	}

	
	//ATTRIBUTE
	
	/**
	 * We pass both the fragment and the field because we need the field type when the binding cannot be resolved
	 */
	public Attribute ensureAttributeForFragment(VariableDeclarationFragment fragment, FieldDeclaration field) {
		IVariableBinding binding = fragment.resolveBinding();
		Attribute attribute;
		if (binding == null)
			attribute = ensureAttributeFromFragmentIntoParentType(fragment, field, this.topFromContainerStack(Type.class));
		else {
			attribute = ensureAttributeForVariableBinding(binding);
			extractBasicModifiersFromBinding(binding.getModifiers(), attribute);
			if (Modifier.isStatic(binding.getModifiers()))
				attribute.setHasClassScope(true);
		}
		attribute.setIsStub(true);
		return attribute;
	}
	
	Attribute ensureAttributeForVariableBinding(IVariableBinding binding) {
		String name = binding.getName();
		ITypeBinding parentTypeBinding = binding.getDeclaringClass();
		Type parentType;
		if (parentTypeBinding == null)
			/* for example
			 * 		String[] args;
			 * 		args.length
			 * appears like an attribute, but the declaring class is not present
			 */
			parentType = unknownType();
		else 
			parentType = ensureTypeFromTypeBinding(parentTypeBinding);
		String qualifiedName = Famix.qualifiedNameOf(parentType) + NAME_SEPARATOR + name;
		if (attributes.has(qualifiedName)) 
			return attributes.named(qualifiedName);
		Attribute attribute = ensureBasicAttribute(parentType, name, qualifiedName,
				ensureTypeFromTypeBinding(binding.getType()));
		createAnnotationInstancesToEntityFromAnnotationBinding(attribute, binding.getAnnotations());
		return attribute;
	}

	private Attribute ensureAttributeFromFragmentIntoParentType(
			VariableDeclarationFragment fragment,
			FieldDeclaration field,
			Type parentType) {
		String name = fragment.getName().toString();
		String qualifiedName = Famix.qualifiedNameOf(parentType);
		if (attributes.has(qualifiedName)) 
			return attributes.named(qualifiedName);
		Attribute attribute = ensureBasicAttribute(parentType, name, qualifiedName,
				ensureTypeFromDomType(field.getType()));
		return attribute;
	}
	private Attribute ensureBasicAttribute(Type parentType, String name,
			String qualifiedName, Type declaredType) {
		Attribute attribute = new Attribute();
		attribute.setName(name);
		attribute.setParentType(parentType);
		attribute.setDeclaredType(declaredType);
		attributes.add(qualifiedName, attribute);
		return attribute;
	}
	

	//LOCAL VARIABLE
	
	/**
	 * We pass the dom type here because of the funny types of JDT 
	 */
	public LocalVariable ensureLocalVariableFromFragment(
			VariableDeclarationFragment fragment,
			org.eclipse.jdt.core.dom.Type type) {
		LocalVariable localVariable = new LocalVariable();
		localVariable.setName(fragment.getName().toString());
		localVariable.setDeclaredType(ensureTypeFromDomType(type));
		//CHECK: We might want to recover the modifiers (e.g., final) 
		localVariable.setIsStub(true);
		((Method) topOfContainerStack()).addLocalVariables(localVariable);
		repository.add(localVariable);
		return localVariable;
	}
	
	//ENUM VALUE
	
	public EnumValue ensureEnumValueFromDeclaration(EnumConstantDeclaration node) {
		Enum parentEnum = topFromContainerStack(Enum.class);
		String enumValueName = node.getName().toString();
		return ensureBasicEnumValue(parentEnum, enumValueName);
	}

	public EnumValue ensureEnumValueFromVariableBinding(IVariableBinding binding) {
		Enum parentEnum = (Enum) ensureTypeFromTypeBinding(binding.getType());
		String enumValueName = binding.getName().toString();
		return ensureBasicEnumValue(parentEnum, enumValueName);
	}
	
	private EnumValue ensureBasicEnumValue(Enum parentEnum, String enumValueName) {
		if (parentEnum.getValues().stream().anyMatch(v -> v.getName().equals(enumValueName)))
			return parentEnum.getValues().stream().filter(v -> v.getName().equals(enumValueName)).findAny().get();
		EnumValue enumValue = new EnumValue();
		enumValue.setName(enumValueName);
		enumValue.setParentEnum(parentEnum);
		enumValue.setIsStub(true);
		repository.add(enumValue);
		return enumValue;
	}

	
	//ANNOTATION TYPE ATTRIBUTE
	
	public AnnotationTypeAttribute ensureAnnotationTypeAttributeFromDeclaration(
			AnnotationTypeMemberDeclaration node) {
		IMethodBinding binding = node.resolveBinding();
		if (binding != null)
			return ensureAnnotationTypeAttributeFromBinding(binding);
		return new AnnotationTypeAttribute();
	}
	private AnnotationTypeAttribute ensureAnnotationTypeAttributeFromBinding(
			IMethodBinding binding) {
		ITypeBinding parentTypeBinding = binding.getDeclaringClass();
		if (parentTypeBinding == null) { return null; }
		AnnotationType annotationType = (AnnotationType) ensureTypeFromTypeBinding(parentTypeBinding);		
		AnnotationTypeAttribute attribute = ensureAnnotationTypeAttribute(annotationType, binding.getName());
		ITypeBinding returnType = binding.getReturnType();
		if ((returnType != null) && !(returnType.isPrimitive() && returnType.getName().equals("void"))) {
			//we do not want to set void as a return type
			attribute.setDeclaredType(ensureTypeFromTypeBinding(returnType));
		}
		return attribute;
	}
	
	
	//INVOCATION
	
	/**
	 * We pass the signature because we want to get it from the node,
	 * but there can be different types of nodes (funny JDT).
	 */
	public Invocation createInvocationFromMethodBinding(IMethodBinding binding,
			String signature) {		
		Invocation invocation = new Invocation();
		invocation.setSender((Method) topOfContainerStack()); 
		if (binding != null)
			invocation.addCandidates(ensureMethodFromMethodBinding(binding));  
		invocation.setSignature(signature);
		repository.add(invocation);
		return invocation;
	}

	public Invocation createInvocationToMethod(Method method,
			String signature) {
		Invocation invocation = new Invocation();
		invocation.setSender((Method) topOfContainerStack()); 
		invocation.addCandidates(method);  
		invocation.setSignature(signature);
		repository.add(invocation);
		return invocation;
	}
	
	public StructuralEntity ensureStructuralEntityFromExpression(
			Expression expression) {
		if (expression instanceof SimpleName) {
			IBinding simpleNameBinding = ((SimpleName) expression).resolveBinding();
			if (simpleNameBinding instanceof IVariableBinding) {
				IVariableBinding binding = ((IVariableBinding) simpleNameBinding).getVariableDeclaration();
				if (binding.isField())
					return ensureAttributeForVariableBinding(binding);
				if (binding.isParameter())
					return ensureParameterWithinCurrentMethodFromVariableBinding(binding);
				if (binding.isEnumConstant())
					return ensureEnumValueFromVariableBinding(binding);
			}
		}
		return null;
	}
	
	
	//ACCESS
	
	public Access createAccessFromExpression(Expression expression) {
		//is this not horrible?
		if (expression instanceof Name) {
			SimpleName simpleName;
			if (expression instanceof SimpleName)
				simpleName = (SimpleName) expression;
			else 
				simpleName = ((QualifiedName) expression).getName();
			IBinding simpleNameBinding = simpleName.resolveBinding();
			if (simpleNameBinding instanceof IVariableBinding) {
				IVariableBinding variableBinding = ((IVariableBinding) simpleNameBinding).getVariableDeclaration();
				return createAccessFromVariableBinding(variableBinding);
			}
		}
		return new Access();
	}
	
	private Access createAccessFromVariableBinding(IVariableBinding binding) {
		Access access = new Access();
		StructuralEntity variable = null;
		boolean isField = binding.isField();
		boolean isParameter = binding.isParameter();
		boolean isEnumConstant = binding.isEnumConstant();
		if (!isField && !isParameter && !isEnumConstant)
			//we only consider fields, parameters and enum constants
			return access;
		if (isField) 
			variable = ensureAttributeForVariableBinding(binding);
		if (isParameter)
			variable = ensureParameterWithinCurrentMethodFromVariableBinding(binding);
		if (isEnumConstant)
			variable = ensureEnumValueFromVariableBinding(binding);
		if (variable == null)
			access.setVariable(unknownVariable());
		access.setVariable(variable);
		if (topOfContainerStack() instanceof Method)
			access.setAccessor((Method) topOfContainerStack());
		if (topOfContainerStack() instanceof Type)
			access.setAccessor(ensureInitializerMethod());
		repository.add(access);
		return access;
	}
	
	public UnknownVariable unknownVariable() {
		if (unknownVariable == null) {
			unknownVariable = new UnknownVariable();
			repository.add(unknownVariable);
		}
		return unknownVariable;
	}

	
	// EXCEPTION
	
	public DeclaredException createDeclaredExceptionFromTypeBinding(ITypeBinding binding, Method method) {
		DeclaredException declaredException = new DeclaredException();
		method.addDeclaredExceptions(declaredException);
		declaredException.setExceptionClass((Class) ensureTypeFromTypeBinding(binding));
		repository.add(declaredException);
		return declaredException;
	}
	
	
	//SOURCE ANCHOR
	
	/**
	 * We pass the compilationUnit because this is where the logic of getting the line number is.
	 * We cannot complain about the sense of humor in this design :)
	 */
	public void createSourceAnchor(SourcedEntity sourcedEntity, String sourceFilePath, ASTNode node, CompilationUnit compilationUnit) {
		FileAnchor fileAnchor = new FileAnchor();
		fileAnchor.setStartLine(compilationUnit.getLineNumber(node.getStartPosition()));
		fileAnchor.setEndLine(compilationUnit.getLineNumber(node.getStartPosition() + node.getLength() - 1));
		fileAnchor.setFileName(pathWithoutIgnoredRootPath(sourceFilePath));
		sourcedEntity.setSourceAnchor(fileAnchor);
		repository.add(fileAnchor);
	}

	//COMMENT
	
	public void ensureCommentFromBodyDeclaration(SourcedEntity entity, BodyDeclaration node) {
		if (node.getJavadoc() != null)
			createBasicComment(entity, node.getJavadoc().toString());
		else {
			//if there is no javadoc, we look for single line or multi line comments before the node
			CompilationUnit root = (CompilationUnit) node.getRoot();
			int firstLeadingCommentIndex = root.firstLeadingCommentIndex(node);
			if (firstLeadingCommentIndex >= 0)  
				createBasicComment(entity, root.getCommentList().get(firstLeadingCommentIndex).toString());
		}
	}
	private void createBasicComment(SourcedEntity entity, String content) {
		Comment comment = new Comment();
		comment.setContent(content);
		entity.addComments(comment);
		repository.add(comment);
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
		/*	We do not extract the static modifier here because we want to set the hasClassScope property
			and we do that specifically only for attributes and methods */  
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