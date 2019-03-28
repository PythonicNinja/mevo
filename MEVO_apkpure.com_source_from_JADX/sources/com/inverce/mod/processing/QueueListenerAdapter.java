package com.inverce.mod.processing;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class QueueListenerAdapter extends QueueListener {
    public void onJobFailure(@NonNull ProcessingQueue processingQueue, @NonNull Job<?, ?> job, @NonNull Exception exception) {
    }

    public void onJobResult(@NonNull ProcessingQueue processingQueue, @NonNull Job<?, ?> job, @Nullable Object obj) {
    }

    public void onJobStarted(@NonNull ProcessingQueue processingQueue, Object obj, @NonNull Processor<?, ?> processor) {
    }

    public void onQueueCancelled(@NonNull ProcessingQueue processingQueue) {
    }

    public void onQueueFinished(@NonNull ProcessingQueue processingQueue) {
    }

    public void onQueueStarted(@NonNull ProcessingQueue processingQueue) {
    }
}
