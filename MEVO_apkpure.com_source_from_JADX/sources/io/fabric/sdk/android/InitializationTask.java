package io.fabric.sdk.android;

import io.fabric.sdk.android.services.common.TimingMetric;
import io.fabric.sdk.android.services.concurrency.Priority;
import io.fabric.sdk.android.services.concurrency.PriorityAsyncTask;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;

class InitializationTask<Result> extends PriorityAsyncTask<Void, Void, Result> {
    private static final String TIMING_METRIC_TAG = "KitInitialization";
    final Kit<Result> kit;

    public InitializationTask(Kit<Result> kit) {
        this.kit = kit;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        TimingMetric createAndStartTimingMetric = createAndStartTimingMetric("onPreExecute");
        try {
            boolean onPreExecute = this.kit.onPreExecute();
            createAndStartTimingMetric.stopMeasuring();
            if (onPreExecute) {
                return;
            }
        } catch (UnmetDependencyException e) {
            throw e;
        } catch (Throwable e2) {
            Fabric.getLogger().mo2497e(Fabric.TAG, "Failure onPreExecute()", e2);
            createAndStartTimingMetric.stopMeasuring();
        } catch (Throwable th) {
            createAndStartTimingMetric.stopMeasuring();
            cancel(true);
        }
        cancel(true);
    }

    protected Result doInBackground(Void... voidArr) {
        voidArr = createAndStartTimingMetric("doInBackground");
        Result doInBackground = !isCancelled() ? this.kit.doInBackground() : null;
        voidArr.stopMeasuring();
        return doInBackground;
    }

    protected void onPostExecute(Result result) {
        this.kit.onPostExecute(result);
        this.kit.initializationCallback.success(result);
    }

    protected void onCancelled(Result result) {
        this.kit.onCancelled(result);
        result = new StringBuilder();
        result.append(this.kit.getIdentifier());
        result.append(" Initialization was cancelled");
        this.kit.initializationCallback.failure(new InitializationException(result.toString()));
    }

    public Priority getPriority() {
        return Priority.HIGH;
    }

    private TimingMetric createAndStartTimingMetric(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.kit.getIdentifier());
        stringBuilder.append(".");
        stringBuilder.append(str);
        TimingMetric timingMetric = new TimingMetric(stringBuilder.toString(), TIMING_METRIC_TAG);
        timingMetric.startMeasuring();
        return timingMetric;
    }
}
