package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;

public interface Actionable {
    @NonNull
    Action getPrimaryAction();
}
