package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

class InstantiatorBuilder {
    private LabelMap attributes = new LabelMap();
    private Comparer comparer = new Comparer();
    private Detail detail;
    private LabelMap elements = new LabelMap();
    private Instantiator factory;
    private List<Creator> options = new ArrayList();
    private Scanner scanner;
    private LabelMap texts = new LabelMap();

    public InstantiatorBuilder(Scanner scanner, Detail detail) {
        this.scanner = scanner;
        this.detail = detail;
    }

    public Instantiator build() throws Exception {
        if (this.factory == null) {
            populate(this.detail);
            build(this.detail);
            validate(this.detail);
        }
        return this.factory;
    }

    private Instantiator build(Detail detail) throws Exception {
        if (this.factory == null) {
            this.factory = create(detail);
        }
        return this.factory;
    }

    private Instantiator create(Detail detail) throws Exception {
        Signature signature = this.scanner.getSignature();
        return new ClassInstantiator(this.options, signature != null ? new SignatureCreator(signature) : null, this.scanner.getParameters(), detail);
    }

    private Creator create(Signature signature) {
        Creator signatureCreator = new SignatureCreator(signature);
        if (signature != null) {
            this.options.add(signatureCreator);
        }
        return signatureCreator;
    }

    private Parameter create(Parameter parameter) throws Exception {
        Label resolve = resolve(parameter);
        return resolve != null ? new CacheParameter(parameter, resolve) : null;
    }

    private void populate(Detail detail) throws Exception {
        for (Signature populate : this.scanner.getSignatures()) {
            populate(populate);
        }
    }

    private void populate(Signature signature) throws Exception {
        Signature signature2 = new Signature(signature);
        signature = signature.iterator();
        while (signature.hasNext()) {
            Parameter create = create((Parameter) signature.next());
            if (create != null) {
                signature2.add(create);
            }
        }
        create(signature2);
    }

    private void validate(Detail detail) throws Exception {
        for (Parameter parameter : this.scanner.getParameters().getAll()) {
            Label resolve = resolve(parameter);
            String path = parameter.getPath();
            if (resolve == null) {
                throw new ConstructorException("Parameter '%s' does not have a match in %s", path, detail);
            }
            validateParameter(resolve, parameter);
        }
        validateConstructors();
    }

    private void validateParameter(Label label, Parameter parameter) throws Exception {
        Contact contact = label.getContact();
        String name = parameter.getName();
        if (Support.isAssignable(parameter.getType(), contact.getType())) {
            validateNames(label, parameter);
            validateAnnotations(label, parameter);
            return;
        }
        throw new ConstructorException("Type is not compatible with %s for '%s' in %s", label, name, parameter);
    }

    private void validateNames(Label label, Parameter parameter) throws Exception {
        String[] names = label.getNames();
        String name = parameter.getName();
        if (!contains(names, name)) {
            String name2 = label.getName();
            if (name != name2) {
                if (name != null) {
                    if (name2 != null) {
                        if (!name.equals(name2)) {
                            throw new ConstructorException("Annotation does not match %s for '%s' in %s", label, name, parameter);
                        }
                        return;
                    }
                }
                throw new ConstructorException("Annotation does not match %s for '%s' in %s", label, name, parameter);
            }
        }
    }

    private void validateAnnotations(Label label, Parameter parameter) throws Exception {
        label = label.getAnnotation();
        Annotation annotation = parameter.getAnnotation();
        String name = parameter.getName();
        if (!this.comparer.equals(label, annotation)) {
            if (!label.annotationType().equals(annotation.annotationType())) {
                throw new ConstructorException("Annotation %s does not match %s for '%s' in %s", annotation.annotationType(), label.annotationType(), name, parameter);
            }
        }
    }

    private void validateConstructors() throws Exception {
        List creators = this.factory.getCreators();
        if (this.factory.isDefault()) {
            validateConstructors(this.elements);
            validateConstructors(this.attributes);
        }
        if (!creators.isEmpty()) {
            validateConstructors(this.elements, creators);
            validateConstructors(this.attributes, creators);
        }
    }

    private void validateConstructors(LabelMap labelMap) throws Exception {
        labelMap = labelMap.iterator();
        while (labelMap.hasNext()) {
            Label label = (Label) labelMap.next();
            if (label != null && label.getContact().isReadOnly()) {
                throw new ConstructorException("Default constructor can not accept read only %s in %s", label, this.detail);
            }
        }
    }

    private void validateConstructors(LabelMap labelMap, List<Creator> list) throws Exception {
        labelMap = labelMap.iterator();
        while (labelMap.hasNext()) {
            Label label = (Label) labelMap.next();
            if (label != null) {
                validateConstructor(label, list);
            }
        }
        if (list.isEmpty() != null) {
            throw new ConstructorException("No constructor accepts all read only values in %s", this.detail);
        }
    }

    private void validateConstructor(Label label, List<Creator> list) throws Exception {
        list = list.iterator();
        while (list.hasNext()) {
            Signature signature = ((Creator) list.next()).getSignature();
            Contact contact = label.getContact();
            Object key = label.getKey();
            if (contact.isReadOnly() && signature.get(key) == null) {
                list.remove();
            }
        }
    }

    public void register(Label label) throws Exception {
        if (label.isAttribute()) {
            register(label, this.attributes);
        } else if (label.isText()) {
            register(label, this.texts);
        } else {
            register(label, this.elements);
        }
    }

    private void register(Label label, LabelMap labelMap) throws Exception {
        String name = label.getName();
        String path = label.getPath();
        if (!labelMap.containsKey(name)) {
            labelMap.put(name, label);
        } else if (!((Label) labelMap.get(name)).getPath().equals(name)) {
            labelMap.remove(name);
        }
        labelMap.put(path, label);
    }

    private Label resolve(Parameter parameter) throws Exception {
        if (parameter.isAttribute()) {
            return resolve(parameter, this.attributes);
        }
        if (parameter.isText()) {
            return resolve(parameter, this.texts);
        }
        return resolve(parameter, this.elements);
    }

    private Label resolve(Parameter parameter, LabelMap labelMap) throws Exception {
        String name = parameter.getName();
        Label label = (Label) labelMap.get(parameter.getPath());
        return label == null ? (Label) labelMap.get(name) : label;
    }

    private boolean contains(String[] strArr, String str) throws Exception {
        for (String str2 : strArr) {
            if (str2 == str || str2.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
