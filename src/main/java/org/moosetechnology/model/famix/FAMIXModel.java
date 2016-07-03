// Automagically generated code, please do not change
package org.moosetechnology.model.famix;

import ch.akuhn.fame.MetaRepository;

public class FAMIXModel {

    public static MetaRepository metamodel() {
        MetaRepository metamodel = new MetaRepository();
        importInto(metamodel);
        return metamodel;
    }
    
    public static void importInto(MetaRepository metamodel) {
		metamodel.with(org.moosetechnology.model.famix.Association.class);
		metamodel.with(org.moosetechnology.model.famix.FileAnchor.class);
		metamodel.with(org.moosetechnology.model.famix.PharoAnchor.class);
		metamodel.with(org.moosetechnology.model.famix.LocalVariable.class);
		metamodel.with(org.moosetechnology.model.famix.UnknownSourceLanguage.class);
		metamodel.with(org.moosetechnology.model.famix.Package.class);
		metamodel.with(org.moosetechnology.model.famix.BehaviouralEntity.class);
		metamodel.with(org.moosetechnology.model.famix.ParameterType.class);
		metamodel.with(org.moosetechnology.model.famix.CompilationUnit.class);
		metamodel.with(org.moosetechnology.model.famix.CaughtException.class);
		metamodel.with(org.moosetechnology.model.famix.Include.class);
		metamodel.with(org.moosetechnology.model.famix.Exception.class);
		metamodel.with(org.moosetechnology.model.famix.CustomSourceLanguage.class);
		metamodel.with(org.moosetechnology.model.famix.Namespace.class);
		metamodel.with(org.moosetechnology.model.famix.SourceAnchor.class);
		metamodel.with(org.moosetechnology.model.famix.DeclaredException.class);
		metamodel.with(org.moosetechnology.model.famix.AnnotationInstanceAttribute.class);
		metamodel.with(org.moosetechnology.model.famix.GlobalVariable.class);
		metamodel.with(org.moosetechnology.model.famix.Function.class);
		metamodel.with(org.moosetechnology.model.famix.Class.class);
		metamodel.with(org.moosetechnology.model.famix.MultipleFileAnchor.class);
		metamodel.with(org.moosetechnology.model.famix.JavaSourceLanguage.class);
		metamodel.with(org.moosetechnology.model.famix.SourcedEntity.class);
		metamodel.with(org.moosetechnology.model.famix.AnnotationType.class);
		metamodel.with(org.moosetechnology.model.famix.Access.class);
		metamodel.with(org.moosetechnology.model.famix.Comment.class);
		metamodel.with(org.moosetechnology.model.famix.ScopingEntity.class);
		metamodel.with(org.moosetechnology.model.famix.Module.class);
		metamodel.with(org.moosetechnology.model.famix.UnknownVariable.class);
		metamodel.with(org.moosetechnology.model.famix.DereferencedInvocation.class);
		metamodel.with(org.moosetechnology.model.famix.Reference.class);
		metamodel.with(org.moosetechnology.model.famix.SourceTextAnchor.class);
		metamodel.with(org.moosetechnology.model.famix.ParameterizableClass.class);
		metamodel.with(org.moosetechnology.model.famix.Method.class);
		metamodel.with(org.moosetechnology.model.famix.LeafEntity.class);
		metamodel.with(org.moosetechnology.model.famix.CSourceLanguage.class);
		metamodel.with(org.moosetechnology.model.famix.Attribute.class);
		metamodel.with(org.moosetechnology.model.famix.SmalltalkMonticelloSourceLanguage.class);
		metamodel.with(org.moosetechnology.model.famix.Type.class);
		metamodel.with(org.moosetechnology.model.famix.EnumValue.class);
		metamodel.with(org.moosetechnology.model.famix.TypeAlias.class);
		metamodel.with(org.moosetechnology.model.famix.AnnotationInstance.class);
		metamodel.with(org.moosetechnology.model.famix.ThrownException.class);
		metamodel.with(org.moosetechnology.model.famix.Parameter.class);
		metamodel.with(org.moosetechnology.model.famix.NamedEntity.class);
		metamodel.with(org.moosetechnology.model.famix.Invocation.class);
		metamodel.with(org.moosetechnology.model.famix.Entity.class);
		metamodel.with(org.moosetechnology.model.famix.PrimitiveType.class);
		metamodel.with(org.moosetechnology.model.famix.ContainerEntity.class);
		metamodel.with(org.moosetechnology.model.famix.SmalltalkSourceLanguage.class);
		metamodel.with(org.moosetechnology.model.famix.AbstractFileAnchor.class);
		metamodel.with(org.moosetechnology.model.famix.StructuralEntity.class);
		metamodel.with(org.moosetechnology.model.famix.Header.class);
		metamodel.with(org.moosetechnology.model.famix.Inheritance.class);
		metamodel.with(org.moosetechnology.model.famix.AnnotationTypeAttribute.class);
		metamodel.with(org.moosetechnology.model.famix.IndexedFileAnchor.class);
		metamodel.with(org.moosetechnology.model.famix.ImplicitVariable.class);
		metamodel.with(org.moosetechnology.model.famix.Enum.class);
		metamodel.with(org.moosetechnology.model.famix.SourceLanguage.class);
		metamodel.with(org.moosetechnology.model.famix.ParameterizedType.class);

    }

}

