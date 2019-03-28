package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NamespaceMap;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

class Composite implements Converter {
    private final Context context;
    private final Criteria criteria;
    private final ObjectFactory factory;
    private final Primitive primitive;
    private final Revision revision;
    private final Type type;

    private static class Builder {
        protected final Composite composite;
        protected final Criteria criteria;
        protected final Schema schema;
        protected final Instance value;

        public Builder(Composite composite, Criteria criteria, Schema schema, Instance instance) {
            this.composite = composite;
            this.criteria = criteria;
            this.schema = schema;
            this.value = instance;
        }

        public Object read(InputNode inputNode) throws Exception {
            Object instance = this.value.getInstance();
            Section section = this.schema.getSection();
            this.value.setInstance(instance);
            this.composite.readVersion(inputNode, instance, this.schema);
            this.composite.readText(inputNode, instance, section);
            this.composite.readAttributes(inputNode, instance, section);
            this.composite.readElements(inputNode, instance, section);
            this.criteria.commit(instance);
            return instance;
        }
    }

    private class Injector extends Builder {
        private Injector(Composite composite, Criteria criteria, Schema schema, Instance instance) {
            super(composite, criteria, schema, instance);
        }

        public Object read(InputNode inputNode) throws Exception {
            Section section = this.schema.getSection();
            this.composite.readVersion(inputNode, null, this.schema);
            this.composite.readText(inputNode, null, section);
            this.composite.readAttributes(inputNode, null, section);
            this.composite.readElements(inputNode, null, section);
            return readInject(inputNode);
        }

        private Object readInject(InputNode inputNode) throws Exception {
            inputNode = this.schema.getInstantiator().getInstance(this.criteria);
            this.value.setInstance(inputNode);
            this.criteria.commit(inputNode);
            return inputNode;
        }
    }

    public Composite(Context context, Type type) {
        this(context, type, null);
    }

    public Composite(Context context, Type type, Class cls) {
        this.factory = new ObjectFactory(context, type, cls);
        this.primitive = new Primitive(context, type);
        this.criteria = new Collector();
        this.revision = new Revision();
        this.context = context;
        this.type = type;
    }

    public Object read(InputNode inputNode) throws Exception {
        Instance instance = this.factory.getInstance(inputNode);
        Class type = instance.getType();
        if (instance.isReference()) {
            return instance.getInstance();
        }
        if (this.context.isPrimitive(type)) {
            return readPrimitive(inputNode, instance);
        }
        return read(inputNode, instance, type);
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Schema schema = this.context.getSchema(obj.getClass());
        Caller caller = schema.getCaller();
        read(inputNode, obj, schema);
        this.criteria.commit(obj);
        caller.validate(obj);
        caller.commit(obj);
        return readResolve(inputNode, obj, caller);
    }

    private Object read(InputNode inputNode, Instance instance, Class cls) throws Exception {
        Schema schema = this.context.getSchema(cls);
        Caller caller = schema.getCaller();
        cls = read(schema, instance).read(inputNode);
        caller.validate(cls);
        caller.commit(cls);
        instance.setInstance(cls);
        return readResolve(inputNode, cls, caller);
    }

    private Builder read(Schema schema, Instance instance) throws Exception {
        if (schema.getInstantiator().isDefault()) {
            return new Builder(this, this.criteria, schema, instance);
        }
        return new Injector(this, this.criteria, schema, instance);
    }

    private Object readPrimitive(InputNode inputNode, Instance instance) throws Exception {
        Class type = instance.getType();
        inputNode = this.primitive.read(inputNode, type);
        if (type != null) {
            instance.setInstance(inputNode);
        }
        return inputNode;
    }

    private Object readResolve(InputNode inputNode, Object obj, Caller caller) throws Exception {
        if (obj == null) {
            return obj;
        }
        inputNode = inputNode.getPosition();
        obj = caller.resolve(obj);
        if (this.type.getType().isAssignableFrom(obj.getClass())) {
            return obj;
        }
        throw new ElementException("Type %s does not match %s at %s", obj.getClass(), this.type.getType(), inputNode);
    }

    private void read(InputNode inputNode, Object obj, Schema schema) throws Exception {
        Section section = schema.getSection();
        readVersion(inputNode, obj, schema);
        readSection(inputNode, obj, section);
    }

    private void readSection(InputNode inputNode, Object obj, Section section) throws Exception {
        readText(inputNode, obj, section);
        readAttributes(inputNode, obj, section);
        readElements(inputNode, obj, section);
    }

    private void readVersion(InputNode inputNode, Object obj, Schema schema) throws Exception {
        Label version = schema.getVersion();
        Class type = this.type.getType();
        if (version != null) {
            inputNode = (InputNode) inputNode.getAttributes().remove(version.getName());
            if (inputNode != null) {
                readVersion(inputNode, obj, version);
                return;
            }
            inputNode = this.context.getVersion(type);
            obj = Double.valueOf(this.revision.getDefault());
            inputNode = Double.valueOf(inputNode.revision());
            this.criteria.set(version, obj);
            this.revision.compare(inputNode, obj);
        }
    }

    private void readVersion(InputNode inputNode, Object obj, Label label) throws Exception {
        inputNode = readInstance(inputNode, obj, label);
        obj = this.type.getType();
        if (inputNode != null) {
            obj = Double.valueOf(this.context.getVersion(obj).revision());
            if (inputNode.equals(this.revision) == null) {
                this.revision.compare(obj, inputNode);
            }
        }
    }

    private void readAttributes(InputNode inputNode, Object obj, Section section) throws Exception {
        NodeMap<String> attributes = inputNode.getAttributes();
        LabelMap attributes2 = section.getAttributes();
        for (String attribute : attributes) {
            InputNode attribute2 = inputNode.getAttribute(attribute);
            if (attribute2 != null) {
                readAttribute(attribute2, obj, section, attributes2);
            }
        }
        validate(inputNode, attributes2, obj);
    }

    private void readElements(InputNode inputNode, Object obj, Section section) throws Exception {
        LabelMap elements = section.getElements();
        InputNode next = inputNode.getNext();
        while (next != null) {
            Section section2 = section.getSection(next.getName());
            if (section2 != null) {
                readSection(next, obj, section2);
            } else {
                readElement(next, obj, section, elements);
            }
            next = inputNode.getNext();
        }
        validate(inputNode, elements, obj);
    }

    private void readText(InputNode inputNode, Object obj, Section section) throws Exception {
        section = section.getText();
        if (section != null) {
            readInstance(inputNode, obj, section);
        }
    }

    private void readAttribute(InputNode inputNode, Object obj, Section section, LabelMap labelMap) throws Exception {
        Label label = labelMap.getLabel(section.getAttribute(inputNode.getName()));
        if (label == null) {
            inputNode = inputNode.getPosition();
            obj = this.context.getType(this.type, obj);
            if (labelMap.isStrict(this.context) != null && this.revision.isEqual() != null) {
                throw new AttributeException("Attribute '%s' does not have a match in %s at %s", section, obj, inputNode);
            }
            return;
        }
        readInstance(inputNode, obj, label);
    }

    private void readElement(InputNode inputNode, Object obj, Section section, LabelMap labelMap) throws Exception {
        section = section.getPath(inputNode.getName());
        Label label = labelMap.getLabel(section);
        if (label == null) {
            label = this.criteria.resolve(section);
        }
        if (label == null) {
            Position position = inputNode.getPosition();
            obj = this.context.getType(this.type, obj);
            if (labelMap.isStrict(this.context) == null || this.revision.isEqual() == null) {
                inputNode.skip();
                return;
            } else {
                throw new ElementException("Element '%s' does not have a match in %s at %s", section, obj, position);
            }
        }
        readUnion(inputNode, obj, labelMap, label);
    }

    private void readUnion(InputNode inputNode, Object obj, LabelMap labelMap, Label label) throws Exception {
        inputNode = readInstance(inputNode, obj, label);
        for (String label2 : label.getPaths()) {
            labelMap.getLabel(label2);
        }
        if (label.isInline() != null) {
            this.criteria.set(label, inputNode);
        }
    }

    private Object readInstance(InputNode inputNode, Object obj, Label label) throws Exception {
        InputNode readVariable = readVariable(inputNode, obj, label);
        if (readVariable == null) {
            inputNode = inputNode.getPosition();
            obj = this.context.getType(this.type, obj);
            if (label.isRequired() && this.revision.isEqual()) {
                throw new ValueRequiredException("Empty value for %s in %s at %s", label, obj, inputNode);
            }
        } else if (readVariable != label.getEmpty(this.context)) {
            this.criteria.set(label, readVariable);
        }
        return readVariable;
    }

    private Object readVariable(InputNode inputNode, Object obj, Label label) throws Exception {
        Converter converter = label.getConverter(this.context);
        if (label.isCollection()) {
            Variable variable = this.criteria.get(label);
            label = label.getContact();
            if (variable != null) {
                return converter.read(inputNode, variable.getValue());
            }
            if (obj != null) {
                obj = label.get(obj);
                if (obj != null) {
                    return converter.read(inputNode, obj);
                }
            }
        }
        return converter.read(inputNode);
    }

    private void validate(InputNode inputNode, LabelMap labelMap, Object obj) throws Exception {
        obj = this.context.getType(this.type, obj);
        inputNode = inputNode.getPosition();
        labelMap = labelMap.iterator();
        while (labelMap.hasNext()) {
            Label label = (Label) labelMap.next();
            if (label.isRequired() && this.revision.isEqual()) {
                throw new ValueRequiredException("Unable to satisfy %s for %s at %s", label, obj, inputNode);
            }
            Object empty = label.getEmpty(this.context);
            if (empty != null) {
                this.criteria.set(label, empty);
            }
        }
    }

    public boolean validate(InputNode inputNode) throws Exception {
        Instance instance = this.factory.getInstance(inputNode);
        if (instance.isReference()) {
            return true;
        }
        instance.setInstance(null);
        return validate(inputNode, instance.getType());
    }

    private boolean validate(InputNode inputNode, Class cls) throws Exception {
        cls = this.context.getSchema(cls);
        Section section = cls.getSection();
        validateText(inputNode, cls);
        validateSection(inputNode, section);
        return inputNode.isElement();
    }

    private void validateSection(InputNode inputNode, Section section) throws Exception {
        validateAttributes(inputNode, section);
        validateElements(inputNode, section);
    }

    private void validateAttributes(InputNode inputNode, Section section) throws Exception {
        NodeMap<String> attributes = inputNode.getAttributes();
        LabelMap attributes2 = section.getAttributes();
        for (String attribute : attributes) {
            InputNode attribute2 = inputNode.getAttribute(attribute);
            if (attribute2 != null) {
                validateAttribute(attribute2, section, attributes2);
            }
        }
        validate(inputNode, attributes2);
    }

    private void validateElements(InputNode inputNode, Section section) throws Exception {
        LabelMap elements = section.getElements();
        InputNode next = inputNode.getNext();
        while (next != null) {
            Section section2 = section.getSection(next.getName());
            if (section2 != null) {
                validateSection(next, section2);
            } else {
                validateElement(next, section, elements);
            }
            next = inputNode.getNext();
        }
        validate(inputNode, elements);
    }

    private void validateText(InputNode inputNode, Schema schema) throws Exception {
        Label text = schema.getText();
        if (text != null) {
            validate(inputNode, text);
        }
    }

    private void validateAttribute(InputNode inputNode, Section section, LabelMap labelMap) throws Exception {
        Position position = inputNode.getPosition();
        Label label = labelMap.getLabel(section.getAttribute(inputNode.getName()));
        if (label == null) {
            inputNode = this.type.getType();
            if (labelMap.isStrict(this.context) != null && this.revision.isEqual() != null) {
                throw new AttributeException("Attribute '%s' does not exist for %s at %s", section, inputNode, position);
            }
            return;
        }
        validate(inputNode, label);
    }

    private void validateElement(InputNode inputNode, Section section, LabelMap labelMap) throws Exception {
        section = section.getPath(inputNode.getName());
        Label label = labelMap.getLabel(section);
        if (label == null) {
            label = this.criteria.resolve(section);
        }
        if (label == null) {
            Position position = inputNode.getPosition();
            Class type = this.type.getType();
            if (labelMap.isStrict(this.context) == null || this.revision.isEqual() == null) {
                inputNode.skip();
                return;
            } else {
                throw new ElementException("Element '%s' does not exist for %s at %s", section, type, position);
            }
        }
        validateUnion(inputNode, labelMap, label);
    }

    private void validateUnion(InputNode inputNode, LabelMap labelMap, Label label) throws Exception {
        for (String label2 : label.getPaths()) {
            labelMap.getLabel(label2);
        }
        if (label.isInline() != null) {
            this.criteria.set(label, null);
        }
        validate(inputNode, label);
    }

    private void validate(InputNode inputNode, Label label) throws Exception {
        Converter converter = label.getConverter(this.context);
        Position position = inputNode.getPosition();
        Class type = this.type.getType();
        if (converter.validate(inputNode) == null) {
            throw new PersistenceException("Invalid value for %s in %s at %s", label, type, position);
        } else {
            this.criteria.set(label, null);
        }
    }

    private void validate(InputNode inputNode, LabelMap labelMap) throws Exception {
        inputNode = inputNode.getPosition();
        labelMap = labelMap.iterator();
        while (labelMap.hasNext()) {
            Label label = (Label) labelMap.next();
            Class type = this.type.getType();
            if (label.isRequired() && this.revision.isEqual()) {
                throw new ValueRequiredException("Unable to satisfy %s for %s at %s", label, type, inputNode);
            }
        }
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        Schema schema = this.context.getSchema(obj.getClass());
        Caller caller = schema.getCaller();
        try {
            if (schema.isPrimitive()) {
                this.primitive.write(outputNode, obj);
            } else {
                caller.persist(obj);
                write(outputNode, obj, schema);
            }
            caller.complete(obj);
        } catch (Throwable th) {
            caller.complete(obj);
        }
    }

    private void write(OutputNode outputNode, Object obj, Schema schema) throws Exception {
        Section section = schema.getSection();
        writeVersion(outputNode, obj, schema);
        writeSection(outputNode, obj, section);
    }

    private void writeSection(OutputNode outputNode, Object obj, Section section) throws Exception {
        NamespaceMap namespaces = outputNode.getNamespaces();
        String prefix = section.getPrefix();
        if (prefix != null) {
            String reference = namespaces.getReference(prefix);
            if (reference == null) {
                throw new ElementException("Namespace prefix '%s' in %s is not in scope", new Object[]{prefix, this.type});
            }
            outputNode.setReference(reference);
        }
        writeAttributes(outputNode, obj, section);
        writeElements(outputNode, obj, section);
        writeText(outputNode, obj, section);
    }

    private void writeVersion(OutputNode outputNode, Object obj, Schema schema) throws Exception {
        obj = schema.getRevision();
        schema = schema.getVersion();
        if (obj != null) {
            Double valueOf = Double.valueOf(this.revision.getDefault());
            obj = Double.valueOf(obj.revision());
            if (!this.revision.compare(obj, valueOf)) {
                writeAttribute(outputNode, obj, schema);
            } else if (schema.isRequired()) {
                writeAttribute(outputNode, obj, schema);
            }
        }
    }

    private void writeAttributes(OutputNode outputNode, Object obj, Section section) throws Exception {
        section = section.getAttributes().iterator();
        while (section.hasNext()) {
            Label label = (Label) section.next();
            Object obj2 = label.getContact().get(obj);
            Class type = this.context.getType(this.type, obj);
            if (obj2 == null) {
                obj2 = label.getEmpty(this.context);
            }
            if (obj2 == null && label.isRequired()) {
                throw new AttributeException("Value for %s is null in %s", new Object[]{label, type});
            }
            writeAttribute(outputNode, obj2, label);
        }
    }

    private void writeElements(OutputNode outputNode, Object obj, Section section) throws Exception {
        for (String str : section) {
            Section section2 = section.getSection(str);
            if (section2 != null) {
                writeSection(outputNode.getChild(str), obj, section2);
            } else {
                Label element = section.getElement(section.getPath(str));
                Class type = this.context.getType(this.type, obj);
                if (this.criteria.get(element) != null) {
                    continue;
                } else if (element == null) {
                    throw new ElementException("Element '%s' not defined in %s", new Object[]{str, type});
                } else {
                    writeUnion(outputNode, obj, section, element);
                }
            }
        }
    }

    private void writeUnion(OutputNode outputNode, Object obj, Section section, Label label) throws Exception {
        section = label.getContact().get(obj);
        obj = this.context.getType(this.type, obj);
        if (section == null && label.isRequired()) {
            throw new ElementException("Value for %s is null in %s", label, obj);
        }
        obj = writeReplace(section);
        if (obj != null) {
            writeElement(outputNode, obj, label);
        }
        this.criteria.set(label, obj);
    }

    private Object writeReplace(Object obj) throws Exception {
        if (obj == null) {
            return obj;
        }
        return this.context.getCaller(obj.getClass()).replace(obj);
    }

    private void writeText(OutputNode outputNode, Object obj, Section section) throws Exception {
        Label text = section.getText();
        if (text != null) {
            Object obj2 = text.getContact().get(obj);
            obj = this.context.getType(this.type, obj);
            if (obj2 == null) {
                obj2 = text.getEmpty(this.context);
            }
            if (obj2 == null && text.isRequired()) {
                throw new TextException("Value for %s is null in %s", text, obj);
            } else {
                writeText(outputNode, obj2, text);
            }
        }
    }

    private void writeAttribute(OutputNode outputNode, Object obj, Label label) throws Exception {
        if (obj != null) {
            label.getDecorator().decorate(outputNode.setAttribute(label.getName(), this.factory.getText(obj)));
        }
    }

    private void writeElement(OutputNode outputNode, Object obj, Label label) throws Exception {
        if (obj != null) {
            Class cls = obj.getClass();
            Label label2 = label.getLabel(cls);
            String name = label2.getName();
            label = label.getType(cls);
            outputNode = outputNode.getChild(name);
            if (!label2.isInline()) {
                writeNamespaces(outputNode, label, label2);
            }
            if (label2.isInline() || isOverridden(outputNode, obj, label) == null) {
                Converter converter = label2.getConverter(this.context);
                outputNode.setData(label2.isData());
                writeElement(outputNode, obj, converter);
            }
        }
    }

    private void writeElement(OutputNode outputNode, Object obj, Converter converter) throws Exception {
        converter.write(outputNode, obj);
    }

    private void writeNamespaces(OutputNode outputNode, Type type, Label label) throws Exception {
        label.getDecorator().decorate(outputNode, this.context.getDecorator(type.getType()));
    }

    private void writeText(OutputNode outputNode, Object obj, Label label) throws Exception {
        if (obj != null && !label.isTextList()) {
            obj = this.factory.getText(obj);
            outputNode.setData(label.isData());
            outputNode.setValue(obj);
        }
    }

    private boolean isOverridden(OutputNode outputNode, Object obj, Type type) throws Exception {
        return this.factory.setOverride(type, obj, outputNode);
    }
}
