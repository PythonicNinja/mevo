package com.mevo.app.tools;

import android.os.CountDownTimer;

public class RepeatTask {
    private boolean doFirstRunImmidietlyOnStart;
    private long repeatIntervalMs;
    private Runnable task;
    private CountDownTimer timer;

    public RepeatTask(long j, boolean z, Runnable runnable) {
        this.repeatIntervalMs = j;
        this.doFirstRunImmidietlyOnStart = z;
        this.task = runnable;
    }

    public void start() {
        stop();
        if (this.doFirstRunImmidietlyOnStart) {
            this.task.run();
        }
        this.timer = new CountDownTimer(this.repeatIntervalMs, this.repeatIntervalMs) {
            public void onTick(long j) {
            }

            public void onFinish() {
                RepeatTask.this.task.run();
                RepeatTask.this.timer.start();
            }
        };
        this.timer.start();
    }

    public void stop() {
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
    }

    public void destroy() {
        stop();
        this.timer = null;
        this.task = null;
    }
}
