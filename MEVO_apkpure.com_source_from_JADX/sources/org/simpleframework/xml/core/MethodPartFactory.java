package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class MethodPartFactory {
    private final AnnotationFactory factory;

    public MethodPartFactory(Detail detail, Support support) {
        this.factory = new AnnotationFactory(detail, support);
    }

    public MethodPart getInstance(Method method, Annotation[] annotationArr) throws Exception {
        Annotation annotation = getAnnotation(method);
        return annotation != null ? getInstance(method, annotation, annotationArr) : null;
    }

    public MethodPart getInstance(Method method, Annotation annotation, Annotation[] annotationArr) throws Exception {
        method = getName(method, annotation);
        if (method.getType() == MethodType.SET) {
            return new SetPart(method, annotation, annotationArr);
        }
        return new GetPart(method, annotation, annotationArr);
    }

    private MethodName getName(Method method, Annotation annotation) throws Exception {
        MethodType methodType = getMethodType(method);
        if (methodType == MethodType.GET) {
            return getRead(method, methodType);
        }
        if (methodType == MethodType.IS) {
            return getRead(method, methodType);
        }
        if (methodType == MethodType.SET) {
            return getWrite(method, methodType);
        }
        throw new MethodException("Annotation %s must mark a set or get method", annotation);
    }

    private MethodType getMethodType(Method method) {
        method = method.getName();
        if (method.startsWith("get")) {
            return MethodType.GET;
        }
        if (method.startsWith("is")) {
            return MethodType.IS;
        }
        if (method.startsWith("set") != null) {
            return MethodType.SET;
        }
        return MethodType.NONE;
    }

    private Annotation getAnnotation(Method method) throws Exception {
        Class[] dependents = getDependents(method);
        Class type = getType(method);
        return type != null ? this.factory.getInstance(type, dependents) : null;
    }

    private Class[] getDependents(Method method) throws Exception {
        MethodType methodType = getMethodType(method);
        if (methodType == MethodType.SET) {
            return Reflector.getParameterDependents(method, 0);
        }
        if (methodType == MethodType.GET) {
            return Reflector.getReturnDependents(method);
        }
        return methodType == MethodType.IS ? Reflector.getReturnDependents(method) : null;
    }

    public Class getType(Method method) throws Exception {
        MethodType methodType = getMethodType(method);
        if (methodType == MethodType.SET) {
            return getParameterType(method);
        }
        if (methodType == MethodType.GET) {
            return getReturnType(method);
        }
        return methodType == MethodType.IS ? getReturnType(method) : null;
    }

    private Class getParameterType(Method method) throws Exception {
        return method.getParameterTypes().length == 1 ? method.getParameterTypes()[0] : null;
    }

    private Class getReturnType(Method method) throws Exception {
        return method.getParameterTypes().length == 0 ? method.getReturnType() : null;
    }

    private MethodName getRead(Method method, MethodType methodType) throws Exception {
        Class[] parameterTypes = method.getParameterTypes();
        String name = method.getName();
        if (parameterTypes.length != 0) {
            throw new MethodException("Get method %s is not a valid property", method);
        }
        String typeName = getTypeName(name, methodType);
        if (typeName != null) {
            return new MethodName(method, methodType, typeName);
        }
        throw new MethodException("Could not get name for %s", method);
    }

    private MethodName getWrite(Method method, MethodType methodType) throws Exception {
        Class[] parameterTypes = method.getParameterTypes();
        String name = method.getName();
        if (parameterTypes.length != 1) {
            throw new MethodException("Set method %s is not a valid property", method);
        }
        String typeName = getTypeName(name, methodType);
        if (typeName != null) {
            return new MethodName(method, methodType, typeName);
        }
        throw new MethodException("Could not get name for %s", method);
    }

    private String getTypeName(String str, MethodType methodType) {
        methodType = methodType.getPrefix();
        int length = str.length();
        if (length > methodType) {
            str = str.substring(methodType, length);
        }
        return Reflector.getName(str);
    }
}
