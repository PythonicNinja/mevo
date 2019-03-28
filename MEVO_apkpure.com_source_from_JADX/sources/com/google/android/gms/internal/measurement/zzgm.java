package com.google.android.gms.internal.measurement;

import android.os.Process;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;

final class zzgm extends Thread {
    private final /* synthetic */ zzgi zzanx;
    private final Object zzaoa = new Object();
    private final BlockingQueue<zzgl<?>> zzaob;

    public zzgm(zzgi zzgi, String str, BlockingQueue<zzgl<?>> blockingQueue) {
        this.zzanx = zzgi;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zzaob = blockingQueue;
        setName(str);
    }

    private final void zza(InterruptedException interruptedException) {
        this.zzanx.zzgi().zziy().zzg(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }

    public final void run() {
        Object obj = null;
        while (obj == null) {
            try {
                this.zzanx.zzant.acquire();
                obj = 1;
            } catch (InterruptedException e) {
                zza(e);
            }
        }
        try {
            int threadPriority = Process.getThreadPriority(Process.myTid());
            while (true) {
                zzgl zzgl = (zzgl) this.zzaob.poll();
                if (zzgl != null) {
                    Process.setThreadPriority(zzgl.zzanz ? threadPriority : 10);
                    zzgl.run();
                } else {
                    synchronized (this.zzaoa) {
                        if (this.zzaob.peek() == null && !this.zzanx.zzanu) {
                            try {
                                this.zzaoa.wait(30000);
                            } catch (InterruptedException e2) {
                                zza(e2);
                            }
                        }
                    }
                    synchronized (this.zzanx.zzans) {
                        if (this.zzaob.peek() == null) {
                            break;
                        }
                    }
                }
            }
            synchronized (this.zzanx.zzans) {
                this.zzanx.zzant.release();
                this.zzanx.zzans.notifyAll();
                if (this == this.zzanx.zzanm) {
                    this.zzanx.zzanm = null;
                } else if (this == this.zzanx.zzann) {
                    this.zzanx.zzann = null;
                } else {
                    this.zzanx.zzgi().zziv().log("Current scheduler thread is neither worker nor network");
                }
            }
        } catch (Throwable th) {
            synchronized (this.zzanx.zzans) {
                this.zzanx.zzant.release();
                this.zzanx.zzans.notifyAll();
                if (this == this.zzanx.zzanm) {
                    this.zzanx.zzanm = null;
                } else if (this == this.zzanx.zzann) {
                    this.zzanx.zzann = null;
                } else {
                    this.zzanx.zzgi().zziv().log("Current scheduler thread is neither worker nor network");
                }
            }
        }
    }

    public final void zzjx() {
        synchronized (this.zzaoa) {
            this.zzaoa.notifyAll();
        }
    }
}
