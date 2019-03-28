package org.greenrobot.eventbus;

import android.util.Log;
import java.io.PrintStream;
import java.util.logging.Level;

public interface Logger {

    public static class AndroidLogger implements Logger {
        static final boolean ANDROID_LOG_AVAILABLE;
        private final String tag;

        static {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
            /*
            r0 = 0;
            r1 = "android.util.Log";	 Catch:{ ClassNotFoundException -> 0x000a }
            r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x000a }
            if (r1 == 0) goto L_0x000a;
        L_0x0009:
            r0 = 1;
        L_0x000a:
            ANDROID_LOG_AVAILABLE = r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.greenrobot.eventbus.Logger.AndroidLogger.<clinit>():void");
        }

        public static boolean isAndroidLogAvailable() {
            return ANDROID_LOG_AVAILABLE;
        }

        public AndroidLogger(String str) {
            this.tag = str;
        }

        public void log(Level level, String str) {
            if (level != Level.OFF) {
                Log.println(mapLevel(level), this.tag, str);
            }
        }

        public void log(Level level, String str, Throwable th) {
            if (level != Level.OFF) {
                level = mapLevel(level);
                String str2 = this.tag;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("\n");
                stringBuilder.append(Log.getStackTraceString(th));
                Log.println(level, str2, stringBuilder.toString());
            }
        }

        protected int mapLevel(Level level) {
            level = level.intValue();
            if (level < 800) {
                return level < 500 ? 2 : 3;
            } else {
                if (level < 900) {
                    return 4;
                }
                return level < 1000 ? 5 : 6;
            }
        }
    }

    public static class JavaLogger implements Logger {
        protected final java.util.logging.Logger logger;

        public JavaLogger(String str) {
            this.logger = java.util.logging.Logger.getLogger(str);
        }

        public void log(Level level, String str) {
            this.logger.log(level, str);
        }

        public void log(Level level, String str, Throwable th) {
            this.logger.log(level, str, th);
        }
    }

    public static class SystemOutLogger implements Logger {
        public void log(Level level, String str) {
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(level);
            stringBuilder.append("] ");
            stringBuilder.append(str);
            printStream.println(stringBuilder.toString());
        }

        public void log(Level level, String str, Throwable th) {
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(level);
            stringBuilder.append("] ");
            stringBuilder.append(str);
            printStream.println(stringBuilder.toString());
            th.printStackTrace(System.out);
        }
    }

    void log(Level level, String str);

    void log(Level level, String str, Throwable th);
}
