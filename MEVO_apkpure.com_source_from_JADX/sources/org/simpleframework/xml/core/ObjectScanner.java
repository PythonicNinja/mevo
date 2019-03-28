package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.util.List;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

class ObjectScanner implements Scanner {
    private StructureBuilder builder;
    private Detail detail;
    private ClassScanner scanner;
    private Structure structure;
    private Support support;

    public ObjectScanner(Detail detail, Support support) throws Exception {
        this.scanner = new ClassScanner(detail, support);
        this.builder = new StructureBuilder(this, detail, support);
        this.support = support;
        this.detail = detail;
        scan(detail);
    }

    public Signature getSignature() {
        return this.scanner.getSignature();
    }

    public List<Signature> getSignatures() {
        return this.scanner.getSignatures();
    }

    public ParameterMap getParameters() {
        return this.scanner.getParameters();
    }

    public Instantiator getInstantiator() {
        return this.structure.getInstantiator();
    }

    public Class getType() {
        return this.detail.getType();
    }

    public Decorator getDecorator() {
        return this.scanner.getDecorator();
    }

    public Caller getCaller(Context context) {
        return new Caller(this, context);
    }

    public Section getSection() {
        return this.structure.getSection();
    }

    public Version getRevision() {
        return this.structure.getRevision();
    }

    public Order getOrder() {
        return this.scanner.getOrder();
    }

    public Label getVersion() {
        return this.structure.getVersion();
    }

    public Label getText() {
        return this.structure.getText();
    }

    public String getName() {
        return this.detail.getName();
    }

    public Function getCommit() {
        return this.scanner.getCommit();
    }

    public Function getValidate() {
        return this.scanner.getValidate();
    }

    public Function getPersist() {
        return this.scanner.getPersist();
    }

    public Function getComplete() {
        return this.scanner.getComplete();
    }

    public Function getReplace() {
        return this.scanner.getReplace();
    }

    public Function getResolve() {
        return this.scanner.getResolve();
    }

    public boolean isPrimitive() {
        return this.structure.isPrimitive();
    }

    public boolean isEmpty() {
        return this.scanner.getRoot() == null;
    }

    public boolean isStrict() {
        return this.detail.isStrict();
    }

    private void scan(Detail detail) throws Exception {
        order(detail);
        field(detail);
        method(detail);
        validate(detail);
        commit(detail);
    }

    private void order(Detail detail) throws Exception {
        this.builder.assemble(detail.getType());
    }

    private void commit(Detail detail) throws Exception {
        detail = detail.getType();
        if (this.structure == null) {
            this.structure = this.builder.build(detail);
        }
        this.builder = null;
    }

    private void validate(Detail detail) throws Exception {
        detail = detail.getType();
        this.builder.commit(detail);
        this.builder.validate(detail);
    }

    private void field(Detail detail) throws Exception {
        detail = this.support.getFields(detail.getType(), detail.getOverride()).iterator();
        while (detail.hasNext()) {
            Contact contact = (Contact) detail.next();
            Annotation annotation = contact.getAnnotation();
            if (annotation != null) {
                this.builder.process(contact, annotation);
            }
        }
    }

    private void method(Detail detail) throws Exception {
        detail = this.support.getMethods(detail.getType(), detail.getOverride()).iterator();
        while (detail.hasNext()) {
            Contact contact = (Contact) detail.next();
            Annotation annotation = contact.getAnnotation();
            if (annotation != null) {
                this.builder.process(contact, annotation);
            }
        }
    }
}
