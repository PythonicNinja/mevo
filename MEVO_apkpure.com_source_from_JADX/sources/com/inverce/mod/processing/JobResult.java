package com.inverce.mod.processing;

import android.support.annotation.Nullable;

public class JobResult<ITEM, RESULT> {
    @Nullable
    public final Exception exception;
    public final Job<ITEM, RESULT> job;
    @Nullable
    public final RESULT result;

    JobResult(Job<ITEM, RESULT> job, RESULT result) {
        this.job = job;
        this.result = result;
        this.exception = null;
    }

    JobResult(Job<ITEM, RESULT> job, Exception exception) {
        this.job = job;
        this.exception = exception;
        this.result = null;
    }
}
