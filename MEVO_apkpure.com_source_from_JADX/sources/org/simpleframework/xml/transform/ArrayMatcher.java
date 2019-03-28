package org.simpleframework.xml.transform;

class ArrayMatcher implements Matcher {
    private final Matcher primary;

    public ArrayMatcher(Matcher matcher) {
        this.primary = matcher;
    }

    public Transform match(Class cls) throws Exception {
        cls = cls.getComponentType();
        if (cls == Character.TYPE) {
            return new CharacterArrayTransform(cls);
        }
        if (cls == Character.class) {
            return new CharacterArrayTransform(cls);
        }
        if (cls == String.class) {
            return new StringArrayTransform();
        }
        return matchArray(cls);
    }

    private Transform matchArray(Class cls) throws Exception {
        Transform match = this.primary.match(cls);
        if (match == null) {
            return null;
        }
        return new ArrayTransform(match, cls);
    }
}
