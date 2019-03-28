package com.raizlabs.android.dbflow.structure.database.transaction;

import android.support.annotation.NonNull;

public interface ITransactionQueue {
    void add(@NonNull Transaction transaction);

    void cancel(@NonNull Transaction transaction);

    void cancel(@NonNull String str);

    void quit();

    void startIfNotAlive();
}
