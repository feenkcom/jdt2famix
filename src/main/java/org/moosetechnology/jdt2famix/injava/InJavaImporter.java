package org.moosetechnology.jdt2famix.injava;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.FileASTRequestor;
import org.eclipse.jdt.core.dom.IPackageBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.moosetechnology.jdt2famix.Importer;
import org.moosetechnology.model.famix.*;
import org.moosetechnology.model.famix.Class;

public class InJavaImporter extends Importer {

	private Map<String,Namespace> namespaces = new HashMap<String, Namespace>();
	public Map<String,Namespace> getNamespaces() { return namespaces; }

	private Map<String, Type> types = new HashMap<String, Type>();
	public Map<String, Type> getTypes() { return types ; }
	
	@Override
	protected FileASTRequestor getRequestor() {
		return new AstRequestor(this);
	}

	public Namespace ensureNamespaceFromPackageBinding(IPackageBinding binding) {
		return namespaces.computeIfAbsent(binding.getName(), k -> namespaces.put(k, createNamespaceNamed(k)));
	}

	private Namespace createNamespaceNamed(String k) {
		Namespace namespace = new Namespace();
		namespace.setName(k);
		namespace.setIsStub(true);
		return namespace;
	}
	
	public Type ensureTypeFromTypeBinding(ITypeBinding binding) {
		String qualifiedName = binding.getQualifiedName();
		if (types.containsKey(qualifiedName)) return types.get(qualifiedName);
		Type type = createTypeFromTypeBinding(binding);
		types.put(qualifiedName, type);
		return type;
	}

	private Type createTypeFromTypeBinding(ITypeBinding binding) {
		Class clazz = new Class();
		clazz.setIsInterface(binding.isInterface());
		return clazz;
	}
}
