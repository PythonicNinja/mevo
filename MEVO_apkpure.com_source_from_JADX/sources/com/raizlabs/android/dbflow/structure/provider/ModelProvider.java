package com.raizlabs.android.dbflow.structure.provider;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;

public interface ModelProvider {
    Uri getDeleteUri();

    Uri getInsertUri();

    Uri getQueryUri();

    Uri getUpdateUri();

    void load();

    void load(@NonNull OperatorGroup operatorGroup, @Nullable String str, String... strArr);
}
