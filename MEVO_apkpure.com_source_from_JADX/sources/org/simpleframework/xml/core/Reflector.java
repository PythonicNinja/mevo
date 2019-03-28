package org.simpleframework.xml.core;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

final class Reflector {
    Reflector() {
    }

    public static Class getDependent(Field field) {
        ParameterizedType type = getType(field);
        if (type != null) {
            return getClass(type);
        }
        return Object.class;
    }

    public static Class[] getDependents(Field field) {
        field = getType(field);
        if (field != null) {
            return getClasses(field);
        }
        return new Class[null];
    }

    private static ParameterizedType getType(Field field) {
        field = field.getGenericType();
        return field instanceof ParameterizedType ? (ParameterizedType) field : null;
    }

    public static Class getReturnDependent(Method method) {
        ParameterizedType returnType = getReturnType(method);
        if (returnType != null) {
            return getClass(returnType);
        }
        return Object.class;
    }

    public static Class[] getReturnDependents(Method method) {
        method = getReturnType(method);
        if (method != null) {
            return getClasses(method);
        }
        return new Class[null];
    }

    private static ParameterizedType getReturnType(Method method) {
        method = method.getGenericReturnType();
        return method instanceof ParameterizedType ? (ParameterizedType) method : null;
    }

    public static Class getParameterDependent(Method method, int i) {
        ParameterizedType parameterType = getParameterType(method, i);
        if (parameterType != null) {
            return getClass(parameterType);
        }
        return Object.class;
    }

    public static Class[] getParameterDependents(Method method, int i) {
        method = getParameterType(method, i);
        if (method != null) {
            return getClasses(method);
        }
        return new Class[null];
    }

    public static Class getParameterDependent(Constructor constructor, int i) {
        ParameterizedType parameterType = getParameterType(constructor, i);
        if (parameterType != null) {
            return getClass(parameterType);
        }
        return Object.class;
    }

    public static Class[] getParameterDependents(Constructor constructor, int i) {
        constructor = getParameterType(constructor, i);
        if (constructor != null) {
            return getClasses(constructor);
        }
        return new Class[null];
    }

    private static ParameterizedType getParameterType(Method method, int i) {
        method = method.getGenericParameterTypes();
        if (method.length > i) {
            method = method[i];
            if ((method instanceof ParameterizedType) != 0) {
                return (ParameterizedType) method;
            }
        }
        return null;
    }

    private static ParameterizedType getParameterType(Constructor constructor, int i) {
        constructor = constructor.getGenericParameterTypes();
        if (constructor.length > i) {
            constructor = constructor[i];
            if ((constructor instanceof ParameterizedType) != 0) {
                return (ParameterizedType) constructor;
            }
        }
        return null;
    }

    private static Class getClass(ParameterizedType parameterizedType) {
        parameterizedType = parameterizedType.getActualTypeArguments();
        return parameterizedType.length > 0 ? getClass(parameterizedType[0]) : null;
    }

    private static Class[] getClasses(ParameterizedType parameterizedType) {
        parameterizedType = parameterizedType.getActualTypeArguments();
        Class[] clsArr = new Class[parameterizedType.length];
        for (int i = 0; i < parameterizedType.length; i++) {
            clsArr[i] = getClass(parameterizedType[i]);
        }
        return clsArr;
    }

    private static Class getClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        return getGenericClass(type);
    }

    private static Class getGenericClass(Type type) {
        if (type instanceof GenericArrayType) {
            return getArrayClass(type);
        }
        return Object.class;
    }

    private static Class getArrayClass(Type type) {
        type = getClass(((GenericArrayType) type).getGenericComponentType());
        return type != null ? Array.newInstance(type, 0).getClass() : null;
    }

    public static String getName(String str) {
        if (str.length() <= 0) {
            return str;
        }
        str = str.toCharArray();
        char c = str[0];
        if (!isAcronym(str)) {
            str[0] = toLowerCase(c);
        }
        return new String(str);
    }

    private static boolean isAcronym(char[] cArr) {
        if (cArr.length >= 2 && isUpperCase(cArr[0])) {
            return isUpperCase(cArr[1]);
        }
        return false;
    }

    private static char toLowerCase(char c) {
        return Character.toLowerCase(c);
    }

    private static boolean isUpperCase(char c) {
        return Character.isUpperCase(c);
    }
}
