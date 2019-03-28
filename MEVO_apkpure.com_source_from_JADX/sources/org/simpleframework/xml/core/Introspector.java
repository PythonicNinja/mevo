package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;

class Introspector {
    private final Contact contact;
    private final Format format;
    private final Label label;
    private final Annotation marker;

    public Introspector(Contact contact, Label label, Format format) {
        this.marker = contact.getAnnotation();
        this.contact = contact;
        this.format = format;
        this.label = label;
    }

    public Contact getContact() {
        return this.contact;
    }

    public Type getDependent() throws Exception {
        return this.label.getDependent();
    }

    public String getEntry() throws Exception {
        Class type = getDependent().getType();
        if (type.isArray()) {
            type = type.getComponentType();
        }
        return getName(type);
    }

    private String getName(Class cls) throws Exception {
        String root = getRoot(cls);
        if (root != null) {
            return root;
        }
        return Reflector.getName(cls.getSimpleName());
    }

    private String getRoot(Class cls) {
        for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            String root = getRoot(cls, cls2);
            if (root != null) {
                return root;
            }
        }
        return null;
    }

    private String getRoot(Class<?> cls, Class<?> cls2) {
        cls = cls2.getSimpleName();
        Root root = (Root) cls2.getAnnotation(Root.class);
        if (root == null) {
            return null;
        }
        cls2 = root.name();
        if (isEmpty(cls2)) {
            return Reflector.getName(cls);
        }
        return cls2;
    }

    public String getName() throws Exception {
        return !this.label.isInline() ? getDefault() : this.label.getEntry();
    }

    private String getDefault() throws Exception {
        String override = this.label.getOverride();
        if (isEmpty(override)) {
            return this.contact.getName();
        }
        return override;
    }

    public Expression getExpression() throws Exception {
        String path = getPath();
        if (path != null) {
            return new PathParser(path, this.contact, this.format);
        }
        return new EmptyExpression(this.format);
    }

    public String getPath() throws Exception {
        Path path = (Path) this.contact.getAnnotation(Path.class);
        if (path == null) {
            return null;
        }
        return path.value();
    }

    public boolean isEmpty(String str) {
        boolean z = true;
        if (str == null) {
            return true;
        }
        if (str.length() != null) {
            z = false;
        }
        return z;
    }

    public String toString() {
        return String.format("%s on %s", new Object[]{this.marker, this.contact});
    }
}
