package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.ElementMapUnion;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.Transient;
import org.simpleframework.xml.Version;

class MethodScanner extends ContactList {
    private final Detail detail;
    private final MethodPartFactory factory;
    private final PartMap read = new PartMap();
    private final Support support;
    private final PartMap write = new PartMap();

    private static class PartMap extends LinkedHashMap<String, MethodPart> implements Iterable<String> {
        private PartMap() {
        }

        public Iterator<String> iterator() {
            return keySet().iterator();
        }

        public MethodPart take(String str) {
            return (MethodPart) remove(str);
        }
    }

    public MethodScanner(Detail detail, Support support) throws Exception {
        this.factory = new MethodPartFactory(detail, support);
        this.support = support;
        this.detail = detail;
        scan(detail);
    }

    private void scan(Detail detail) throws Exception {
        DefaultType override = detail.getOverride();
        DefaultType access = detail.getAccess();
        Class cls = detail.getSuper();
        if (cls != null) {
            extend(cls, override);
        }
        extract(detail, access);
        extract(detail);
        build();
        validate();
    }

    private void extend(Class cls, DefaultType defaultType) throws Exception {
        cls = this.support.getMethods(cls, defaultType).iterator();
        while (cls.hasNext() != null) {
            process((MethodContact) ((Contact) cls.next()));
        }
    }

    private void extract(Detail detail) throws Exception {
        for (MethodDetail methodDetail : detail.getMethods()) {
            Annotation[] annotations = methodDetail.getAnnotations();
            Method method = methodDetail.getMethod();
            for (Annotation scan : annotations) {
                scan(method, scan, annotations);
            }
        }
    }

    private void extract(Detail detail, DefaultType defaultType) throws Exception {
        Detail<MethodDetail> methods = detail.getMethods();
        if (defaultType == DefaultType.PROPERTY) {
            for (MethodDetail methodDetail : methods) {
                Annotation[] annotations = methodDetail.getAnnotations();
                Method method = methodDetail.getMethod();
                if (this.factory.getType(method) != null) {
                    process(method, annotations);
                }
            }
        }
    }

    private void scan(Method method, Annotation annotation, Annotation[] annotationArr) throws Exception {
        if (annotation instanceof Attribute) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementUnion) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementListUnion) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementMapUnion) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementList) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementArray) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementMap) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof Element) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof Version) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof Text) {
            process(method, annotation, annotationArr);
        }
        if (annotation instanceof Transient) {
            remove(method, annotation, annotationArr);
        }
    }

    private void process(Method method, Annotation annotation, Annotation[] annotationArr) throws Exception {
        MethodPart instance = this.factory.getInstance(method, annotation, annotationArr);
        annotation = instance.getMethodType();
        if (annotation == MethodType.GET) {
            process(instance, this.read);
        }
        if (annotation == MethodType.IS) {
            process(instance, this.read);
        }
        if (annotation == MethodType.SET) {
            process(instance, this.write);
        }
    }

    private void process(Method method, Annotation[] annotationArr) throws Exception {
        MethodPart instance = this.factory.getInstance(method, annotationArr);
        annotationArr = instance.getMethodType();
        if (annotationArr == MethodType.GET) {
            process(instance, this.read);
        }
        if (annotationArr == MethodType.IS) {
            process(instance, this.read);
        }
        if (annotationArr == MethodType.SET) {
            process(instance, this.write);
        }
    }

    private void process(MethodPart methodPart, PartMap partMap) {
        String name = methodPart.getName();
        if (name != null) {
            partMap.put(name, methodPart);
        }
    }

    private void process(MethodContact methodContact) {
        MethodPart read = methodContact.getRead();
        methodContact = methodContact.getWrite();
        if (methodContact != null) {
            insert(methodContact, this.write);
        }
        insert(read, this.read);
    }

    private void insert(MethodPart methodPart, PartMap partMap) {
        String name = methodPart.getName();
        MethodPart methodPart2 = (MethodPart) partMap.remove(name);
        if (methodPart2 != null && isText(methodPart)) {
            methodPart = methodPart2;
        }
        partMap.put(name, methodPart);
    }

    private boolean isText(MethodPart methodPart) {
        return (methodPart.getAnnotation() instanceof Text) != null ? true : null;
    }

    private void remove(Method method, Annotation annotation, Annotation[] annotationArr) throws Exception {
        method = this.factory.getInstance(method, annotation, annotationArr);
        annotation = method.getMethodType();
        if (annotation == MethodType.GET) {
            remove(method, this.read);
        }
        if (annotation == MethodType.IS) {
            remove(method, this.read);
        }
        if (annotation == MethodType.SET) {
            remove(method, this.write);
        }
    }

    private void remove(MethodPart methodPart, PartMap partMap) throws Exception {
        methodPart = methodPart.getName();
        if (methodPart != null) {
            partMap.remove(methodPart);
        }
    }

    private void build() throws Exception {
        Iterator it = this.read.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            MethodPart methodPart = (MethodPart) this.read.get(str);
            if (methodPart != null) {
                build(methodPart, str);
            }
        }
    }

    private void build(MethodPart methodPart, String str) throws Exception {
        MethodPart take = this.write.take(str);
        if (take != null) {
            build(methodPart, take);
        } else {
            build(methodPart);
        }
    }

    private void build(MethodPart methodPart) throws Exception {
        add(new MethodContact(methodPart));
    }

    private void build(MethodPart methodPart, MethodPart methodPart2) throws Exception {
        Annotation annotation = methodPart.getAnnotation();
        String name = methodPart.getName();
        if (methodPart2.getAnnotation().equals(annotation)) {
            if (methodPart.getType() != methodPart2.getType()) {
                throw new MethodException("Method types do not match for %s in %s", name, methodPart.getType());
            } else {
                add(new MethodContact(methodPart, methodPart2));
                return;
            }
        }
        throw new MethodException("Annotations do not match for '%s' in %s", name, this.detail);
    }

    private void validate() throws Exception {
        Iterator it = this.write.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            MethodPart methodPart = (MethodPart) this.write.get(str);
            if (methodPart != null) {
                validate(methodPart, str);
            }
        }
    }

    private void validate(MethodPart methodPart, String str) throws Exception {
        str = this.read.take(str);
        methodPart = methodPart.getMethod();
        if (str == null) {
            throw new MethodException("No matching get method for %s in %s", methodPart, this.detail);
        }
    }
}
