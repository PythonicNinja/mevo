package org.simpleframework.xml.core;

class Caller {
    private final Function commit;
    private final Function complete;
    private final Context context;
    private final Function persist;
    private final Function replace;
    private final Function resolve;
    private final Function validate;

    public Caller(Scanner scanner, Context context) {
        this.validate = scanner.getValidate();
        this.complete = scanner.getComplete();
        this.replace = scanner.getReplace();
        this.resolve = scanner.getResolve();
        this.persist = scanner.getPersist();
        this.commit = scanner.getCommit();
        this.context = context;
    }

    public Object replace(Object obj) throws Exception {
        return this.replace != null ? this.replace.call(this.context, obj) : obj;
    }

    public Object resolve(Object obj) throws Exception {
        return this.resolve != null ? this.resolve.call(this.context, obj) : obj;
    }

    public void commit(Object obj) throws Exception {
        if (this.commit != null) {
            this.commit.call(this.context, obj);
        }
    }

    public void validate(Object obj) throws Exception {
        if (this.validate != null) {
            this.validate.call(this.context, obj);
        }
    }

    public void persist(Object obj) throws Exception {
        if (this.persist != null) {
            this.persist.call(this.context, obj);
        }
    }

    public void complete(Object obj) throws Exception {
        if (this.complete != null) {
            this.complete.call(this.context, obj);
        }
    }
}
