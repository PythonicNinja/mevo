package com.inverce.mod.core.utilities;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.inverce.mod.core.functional.IConsumer;
import java.io.Serializable;

public class SubBundleBuilder<TYPE> extends SubBuilder<TYPE> {
    protected Bundle extras;
    private IConsumer<Bundle> setter;

    public SubBundleBuilder(TYPE type, @Nullable Bundle bundle) {
        super(type);
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.extras = bundle;
    }

    public TYPE create() {
        if (this.setter != null) {
            this.setter.accept(this.extras);
        }
        return super.create();
    }

    public SubBundleBuilder(TYPE type, Bundle bundle, IConsumer<Bundle> iConsumer) {
        this(type, bundle);
        this.setter = iConsumer;
    }

    @CheckResult
    @NonNull
    public SubBundleBuilder<TYPE> with(@NonNull String str, Serializable serializable) {
        this.extras.putSerializable(str, serializable);
        return this;
    }

    @CheckResult
    @NonNull
    public SubBundleBuilder<TYPE> with(@NonNull String str, Parcelable parcelable) {
        this.extras.putParcelable(str, parcelable);
        return this;
    }

    @CheckResult
    @NonNull
    public SubBundleBuilder<TYPE> with(@NonNull String str, String str2) {
        this.extras.putString(str, str2);
        return this;
    }

    @CheckResult
    @NonNull
    public SubBundleBuilder<TYPE> with(@NonNull String str, long j) {
        this.extras.putLong(str, j);
        return this;
    }

    @CheckResult
    @NonNull
    public SubBundleBuilder<TYPE> with(@NonNull String str, double d) {
        this.extras.putDouble(str, d);
        return this;
    }

    @CheckResult
    @NonNull
    public SubBundleBuilder<TYPE> with(@NonNull Serializable serializable) {
        return with(DataBufferSafeParcelable.DATA_FIELD, serializable);
    }

    @CheckResult
    @NonNull
    public SubBundleBuilder<TYPE> with(@NonNull Parcelable parcelable) {
        return with(DataBufferSafeParcelable.DATA_FIELD, parcelable);
    }

    @CheckResult
    @NonNull
    public SubBundleBuilder<TYPE> with(@NonNull Bundle bundle) {
        this.extras.putAll(bundle);
        return this;
    }

    @CheckResult
    @NonNull
    public SubBundleBuilder<TYPE> setup(@NonNull IConsumer<Bundle> iConsumer) {
        iConsumer.accept(this.extras);
        return this;
    }
}
