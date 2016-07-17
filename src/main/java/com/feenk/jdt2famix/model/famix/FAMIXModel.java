// Automagically generated code, please do not change
package com.feenk.jdt2famix.model.famix;

import ch.akuhn.fame.MetaRepository;

public class FAMIXModel {

    public static MetaRepository metamodel() {
        MetaRepository metamodel = new MetaRepository();
        importInto(metamodel);
        return metamodel;
    }
    
    public static void importInto(MetaRepository metamodel) {
		metamodel.with(com.feenk.jdt2famix.model.famix.Association.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.FileAnchor.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.PharoAnchor.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.LocalVariable.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.UnknownSourceLanguage.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Package.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.BehaviouralEntity.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.ParameterType.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.CompilationUnit.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.CaughtException.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Include.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Exception.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.CustomSourceLanguage.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Namespace.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.SourceAnchor.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.DeclaredException.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.AnnotationInstanceAttribute.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.GlobalVariable.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Function.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Class.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.MultipleFileAnchor.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.JavaSourceLanguage.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.SourcedEntity.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.AnnotationType.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Access.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Comment.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.ScopingEntity.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Module.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.UnknownVariable.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.DereferencedInvocation.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Reference.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.SourceTextAnchor.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.ParameterizableClass.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Method.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.LeafEntity.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.CSourceLanguage.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Attribute.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.SmalltalkMonticelloSourceLanguage.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Type.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.EnumValue.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.TypeAlias.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.AnnotationInstance.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.ThrownException.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Parameter.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.NamedEntity.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Invocation.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Entity.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.PrimitiveType.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.ContainerEntity.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.SmalltalkSourceLanguage.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.AbstractFileAnchor.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.StructuralEntity.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Header.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Inheritance.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.AnnotationTypeAttribute.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.IndexedFileAnchor.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.ImplicitVariable.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.Enum.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.SourceLanguage.class);
		metamodel.with(com.feenk.jdt2famix.model.famix.ParameterizedType.class);

    }

}

