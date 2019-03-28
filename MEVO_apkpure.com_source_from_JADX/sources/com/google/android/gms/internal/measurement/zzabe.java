package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzabe extends zzabd<FieldDescriptorType, Object> {
    zzabe(int i) {
        super(i);
    }

    public final void zzru() {
        if (!isImmutable()) {
            Entry zzah;
            for (int i = 0; i < zzuy(); i++) {
                zzah = zzah(i);
                if (((zzzt) zzah.getKey()).zztz()) {
                    zzah.setValue(Collections.unmodifiableList((List) zzah.getValue()));
                }
            }
            for (Entry zzah2 : zzuz()) {
                if (((zzzt) zzah2.getKey()).zztz()) {
                    zzah2.setValue(Collections.unmodifiableList((List) zzah2.getValue()));
                }
            }
        }
        super.zzru();
    }
}
