package com.inverce.mod.core.configuration;

import android.support.annotation.NonNull;
import com.inverce.mod.core.functional.ISupplier;
import com.inverce.mod.core.utilities.SubBuilder;
import java.util.ArrayList;
import java.util.Iterator;

public class Preset {
    protected ArrayList<Record<?>> records = new ArrayList();

    protected class Record<T> {
        Value<T> preference;
        ISupplier<T> value;

        public Record(Value<T> value, ISupplier<T> iSupplier) {
            this.preference = value;
            this.value = iSupplier;
        }

        void apply() {
            this.preference.set(this.value.get());
        }
    }

    public class Builder extends SubBuilder<Preset> {
        static final /* synthetic */ Object lambda$add$0$Preset$Builder(Object obj) {
            return obj;
        }

        Builder() {
            super(Preset.this);
        }

        @NonNull
        public <T, Y extends Value<T>> Builder add(Y y, T t) {
            return addSupplier(y, new Preset$Builder$$Lambda$0(t));
        }

        @NonNull
        public <T, Y extends Value<T>> Builder addSupplier(Y y, ISupplier<T> iSupplier) {
            Preset.this.records.add(new Record(y, iSupplier));
            return this;
        }
    }

    public static Builder create() {
        Preset preset = new Preset();
        preset.getClass();
        return new Builder();
    }

    protected Preset() {
    }

    @NonNull
    public Preset apply() {
        Iterator it = this.records.iterator();
        while (it.hasNext()) {
            ((Record) it.next()).apply();
        }
        return this;
    }
}
