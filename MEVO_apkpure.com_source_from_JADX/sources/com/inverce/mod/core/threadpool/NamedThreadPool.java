package com.inverce.mod.core.threadpool;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadPool implements ThreadFactory {
    protected static final AtomicInteger poolNumber = new AtomicInteger(1);
    protected final ThreadGroup group;
    @NonNull
    protected final String namePrefix;
    protected final AtomicInteger threadNumber = new AtomicInteger(1);

    public NamedThreadPool(String str) {
        ThreadGroup threadGroup;
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            threadGroup = securityManager.getThreadGroup();
        } else {
            threadGroup = Thread.currentThread().getThreadGroup();
        }
        this.group = threadGroup;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(Operation.MINUS);
        stringBuilder.append(poolNumber.getAndIncrement());
        stringBuilder.append("-thread-");
        this.namePrefix = stringBuilder.toString();
    }

    @NonNull
    public Thread newThread(Runnable runnable) {
        ThreadGroup threadGroup = this.group;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.namePrefix);
        stringBuilder.append(this.threadNumber.getAndIncrement());
        Thread thread = new Thread(threadGroup, runnable, stringBuilder.toString(), 0);
        if (thread.isDaemon() != null) {
            thread.setDaemon(null);
        }
        if (thread.getPriority() != 5) {
            thread.setPriority(5);
        }
        return thread;
    }
}
