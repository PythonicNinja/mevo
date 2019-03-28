package com.inverce.mod.processing;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.concurrent.Callable;

public interface Processor<ITEM, RESULT> {
    public static final Processor<Callable<?>, ?> CALLABLE = Processor$$Lambda$0.$instance;
    @Nullable
    public static final Processor<Runnable, Void> RUNNABLES = Processor$$Lambda$1.$instance;

    public static class EX {
        static <T, R, M> Processor<T, R> map(@NonNull Processor<M, R> processor, @NonNull Processor<T, M> processor2) {
            return new Processor$EX$$Lambda$0(processor, processor2);
        }
    }

    @NonNull
    RESULT processJob(ITEM item) throws Exception;
}
