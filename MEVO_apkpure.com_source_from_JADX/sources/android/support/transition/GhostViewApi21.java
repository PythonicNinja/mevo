package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Method;

@RequiresApi(21)
class GhostViewApi21 implements GhostViewImpl {
    private static final String TAG = "GhostViewApi21";
    private static Method sAddGhostMethod;
    private static boolean sAddGhostMethodFetched;
    private static Class<?> sGhostViewClass;
    private static boolean sGhostViewClassFetched;
    private static Method sRemoveGhostMethod;
    private static boolean sRemoveGhostMethodFetched;
    private final View mGhostView;

    static class Creator implements android.support.transition.GhostViewImpl.Creator {
        Creator() {
        }

        public android.support.transition.GhostViewImpl addGhost(android.view.View r6, android.view.ViewGroup r7, android.graphics.Matrix r8) {
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
            r5 = this;
            android.support.transition.GhostViewApi21.fetchAddGhostMethod();
            r0 = android.support.transition.GhostViewApi21.sAddGhostMethod;
            r1 = 0;
            if (r0 == 0) goto L_0x0031;
        L_0x000a:
            r0 = new android.support.transition.GhostViewApi21;	 Catch:{ IllegalAccessException -> 0x0031, InvocationTargetException -> 0x0026 }
            r2 = android.support.transition.GhostViewApi21.sAddGhostMethod;	 Catch:{ IllegalAccessException -> 0x0031, InvocationTargetException -> 0x0026 }
            r3 = 3;	 Catch:{ IllegalAccessException -> 0x0031, InvocationTargetException -> 0x0026 }
            r3 = new java.lang.Object[r3];	 Catch:{ IllegalAccessException -> 0x0031, InvocationTargetException -> 0x0026 }
            r4 = 0;	 Catch:{ IllegalAccessException -> 0x0031, InvocationTargetException -> 0x0026 }
            r3[r4] = r6;	 Catch:{ IllegalAccessException -> 0x0031, InvocationTargetException -> 0x0026 }
            r6 = 1;	 Catch:{ IllegalAccessException -> 0x0031, InvocationTargetException -> 0x0026 }
            r3[r6] = r7;	 Catch:{ IllegalAccessException -> 0x0031, InvocationTargetException -> 0x0026 }
            r6 = 2;	 Catch:{ IllegalAccessException -> 0x0031, InvocationTargetException -> 0x0026 }
            r3[r6] = r8;	 Catch:{ IllegalAccessException -> 0x0031, InvocationTargetException -> 0x0026 }
            r6 = r2.invoke(r1, r3);	 Catch:{ IllegalAccessException -> 0x0031, InvocationTargetException -> 0x0026 }
            r6 = (android.view.View) r6;	 Catch:{ IllegalAccessException -> 0x0031, InvocationTargetException -> 0x0026 }
            r0.<init>(r6);	 Catch:{ IllegalAccessException -> 0x0031, InvocationTargetException -> 0x0026 }
            return r0;
        L_0x0026:
            r6 = move-exception;
            r7 = new java.lang.RuntimeException;
            r6 = r6.getCause();
            r7.<init>(r6);
            throw r7;
        L_0x0031:
            return r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.transition.GhostViewApi21.Creator.addGhost(android.view.View, android.view.ViewGroup, android.graphics.Matrix):android.support.transition.GhostViewImpl");
        }

        public void removeGhost(android.view.View r5) {
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
            r4 = this;
            android.support.transition.GhostViewApi21.fetchRemoveGhostMethod();
            r0 = android.support.transition.GhostViewApi21.sRemoveGhostMethod;
            if (r0 == 0) goto L_0x0023;
        L_0x0009:
            r0 = android.support.transition.GhostViewApi21.sRemoveGhostMethod;	 Catch:{ IllegalAccessException -> 0x0023, InvocationTargetException -> 0x0018 }
            r1 = 0;	 Catch:{ IllegalAccessException -> 0x0023, InvocationTargetException -> 0x0018 }
            r2 = 1;	 Catch:{ IllegalAccessException -> 0x0023, InvocationTargetException -> 0x0018 }
            r2 = new java.lang.Object[r2];	 Catch:{ IllegalAccessException -> 0x0023, InvocationTargetException -> 0x0018 }
            r3 = 0;	 Catch:{ IllegalAccessException -> 0x0023, InvocationTargetException -> 0x0018 }
            r2[r3] = r5;	 Catch:{ IllegalAccessException -> 0x0023, InvocationTargetException -> 0x0018 }
            r0.invoke(r1, r2);	 Catch:{ IllegalAccessException -> 0x0023, InvocationTargetException -> 0x0018 }
            goto L_0x0023;
        L_0x0018:
            r5 = move-exception;
            r0 = new java.lang.RuntimeException;
            r5 = r5.getCause();
            r0.<init>(r5);
            throw r0;
        L_0x0023:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.transition.GhostViewApi21.Creator.removeGhost(android.view.View):void");
        }
    }

    public void reserveEndViewTransition(ViewGroup viewGroup, View view) {
    }

    private GhostViewApi21(@NonNull View view) {
        this.mGhostView = view;
    }

    public void setVisibility(int i) {
        this.mGhostView.setVisibility(i);
    }

    private static void fetchGhostViewClass() {
        if (!sGhostViewClassFetched) {
            try {
                sGhostViewClass = Class.forName("android.view.GhostView");
            } catch (Throwable e) {
                Log.i(TAG, "Failed to retrieve GhostView class", e);
            }
            sGhostViewClassFetched = true;
        }
    }

    private static void fetchAddGhostMethod() {
        if (!sAddGhostMethodFetched) {
            try {
                fetchGhostViewClass();
                sAddGhostMethod = sGhostViewClass.getDeclaredMethod("addGhost", new Class[]{View.class, ViewGroup.class, Matrix.class});
                sAddGhostMethod.setAccessible(true);
            } catch (Throwable e) {
                Log.i(TAG, "Failed to retrieve addGhost method", e);
            }
            sAddGhostMethodFetched = true;
        }
    }

    private static void fetchRemoveGhostMethod() {
        if (!sRemoveGhostMethodFetched) {
            try {
                fetchGhostViewClass();
                sRemoveGhostMethod = sGhostViewClass.getDeclaredMethod("removeGhost", new Class[]{View.class});
                sRemoveGhostMethod.setAccessible(true);
            } catch (Throwable e) {
                Log.i(TAG, "Failed to retrieve removeGhost method", e);
            }
            sRemoveGhostMethodFetched = true;
        }
    }
}
