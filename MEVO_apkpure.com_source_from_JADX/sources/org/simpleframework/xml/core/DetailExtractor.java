package org.simpleframework.xml.core;

import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class DetailExtractor {
    private final Cache<Detail> details;
    private final Cache<ContactList> fields;
    private final Cache<ContactList> methods;
    private final DefaultType override;
    private final Support support;

    public DetailExtractor(Support support) {
        this(support, null);
    }

    public DetailExtractor(Support support, DefaultType defaultType) {
        this.methods = new ConcurrentCache();
        this.fields = new ConcurrentCache();
        this.details = new ConcurrentCache();
        this.override = defaultType;
        this.support = support;
    }

    public Detail getDetail(Class cls) {
        Detail detail = (Detail) this.details.fetch(cls);
        if (detail != null) {
            return detail;
        }
        detail = new DetailScanner(cls, this.override);
        this.details.cache(cls, detail);
        return detail;
    }

    public ContactList getFields(Class cls) throws Exception {
        ContactList contactList = (ContactList) this.fields.fetch(cls);
        if (contactList != null) {
            return contactList;
        }
        Detail detail = getDetail(cls);
        return detail != null ? getFields(cls, detail) : contactList;
    }

    private ContactList getFields(Class cls, Detail detail) throws Exception {
        ContactList fieldScanner = new FieldScanner(detail, this.support);
        if (detail != null) {
            this.fields.cache(cls, fieldScanner);
        }
        return fieldScanner;
    }

    public ContactList getMethods(Class cls) throws Exception {
        ContactList contactList = (ContactList) this.methods.fetch(cls);
        if (contactList != null) {
            return contactList;
        }
        Detail detail = getDetail(cls);
        return detail != null ? getMethods(cls, detail) : contactList;
    }

    private ContactList getMethods(Class cls, Detail detail) throws Exception {
        ContactList methodScanner = new MethodScanner(detail, this.support);
        if (detail != null) {
            this.methods.cache(cls, methodScanner);
        }
        return methodScanner;
    }
}
