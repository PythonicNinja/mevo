package com.inverce.mod.processing;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class QueueListener {
    public abstract void onJobFailure(@NonNull ProcessingQueue processingQueue, @NonNull Job<?, ?> job, @NonNull Exception exception);

    public abstract void onJobResult(@NonNull ProcessingQueue processingQueue, @NonNull Job<?, ?> job, @Nullable Object obj);

    public abstract void onJobStarted(@NonNull ProcessingQueue processingQueue, Object obj, @NonNull Processor<?, ?> processor);

    public abstract void onQueueCancelled(@NonNull ProcessingQueue processingQueue);

    public abstract void onQueueFinished(@NonNull ProcessingQueue processingQueue);

    public abstract void onQueueStarted(@NonNull ProcessingQueue processingQueue);
}
