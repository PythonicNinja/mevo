package org.simpleframework.xml.stream;

import java.util.Iterator;
import java.util.LinkedHashMap;

class PrefixResolver extends LinkedHashMap<String, String> implements NamespaceMap {
    private final OutputNode source;

    public PrefixResolver(OutputNode outputNode) {
        this.source = outputNode;
    }

    public String getPrefix() {
        return this.source.getPrefix();
    }

    public String setReference(String str) {
        return setReference(str, "");
    }

    public String setReference(String str, String str2) {
        if (resolvePrefix(str) != null) {
            return null;
        }
        return (String) put(str, str2);
    }

    public String getPrefix(String str) {
        if (size() > 0) {
            String str2 = (String) get(str);
            if (str2 != null) {
                return str2;
            }
        }
        return resolvePrefix(str);
    }

    public String getReference(String str) {
        if (containsValue(str)) {
            Iterator it = iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                String str3 = (String) get(str2);
                if (str3 != null && str3.equals(str)) {
                    return str2;
                }
            }
        }
        return resolveReference(str);
    }

    private String resolveReference(String str) {
        NamespaceMap namespaces = this.source.getNamespaces();
        return namespaces != null ? namespaces.getReference(str) : null;
    }

    private String resolvePrefix(String str) {
        NamespaceMap namespaces = this.source.getNamespaces();
        if (namespaces != null) {
            str = namespaces.getPrefix(str);
            if (!containsValue(str)) {
                return str;
            }
        }
        return null;
    }

    public Iterator<String> iterator() {
        return keySet().iterator();
    }
}
