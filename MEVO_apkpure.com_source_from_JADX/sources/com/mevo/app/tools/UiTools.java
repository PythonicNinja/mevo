package com.mevo.app.tools;

public class UiTools {
    public static void hideSoftInput(android.view.View r2) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r0 = com.mevo.app.tools.Lifecycle.getActivity();	 Catch:{ Exception -> 0x001a }
        if (r0 == 0) goto L_0x001a;	 Catch:{ Exception -> 0x001a }
    L_0x0006:
        r0 = com.mevo.app.tools.Lifecycle.getActivity();	 Catch:{ Exception -> 0x001a }
        r1 = "input_method";	 Catch:{ Exception -> 0x001a }
        r0 = r0.getSystemService(r1);	 Catch:{ Exception -> 0x001a }
        r0 = (android.view.inputmethod.InputMethodManager) r0;	 Catch:{ Exception -> 0x001a }
        r2 = r2.getWindowToken();	 Catch:{ Exception -> 0x001a }
        r1 = 0;	 Catch:{ Exception -> 0x001a }
        r0.hideSoftInputFromWindow(r2, r1);	 Catch:{ Exception -> 0x001a }
    L_0x001a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.tools.UiTools.hideSoftInput(android.view.View):void");
    }
}
