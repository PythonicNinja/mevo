package com.inverce.mod.core;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.StateSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import java.text.Normalizer;
import java.text.Normalizer.Form;

public class Ui {
    @NonNull
    private static String PADDING_NPE = "To set padding you must provide VIEW";

    public static class Layout {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        LayoutParams params;
        @Nullable
        View view;

        static {
            Class cls = Ui.class;
        }

        public Layout(@NonNull View view) {
            this.view = view;
            this.params = view.getLayoutParams();
        }

        public Layout(@NonNull LayoutParams layoutParams) {
            this.params = layoutParams;
        }

        @NonNull
        public static Layout on(@NonNull View view) {
            return new Layout(view);
        }

        @NonNull
        public static Layout on(@NonNull LayoutParams layoutParams) {
            return new Layout(layoutParams);
        }

        private void checkView(String str) throws NullPointerException {
            if (this.view == null) {
                throw new NullPointerException(str);
            }
        }

        @NonNull
        public Layout width(int i, boolean z) {
            if (this.params != null) {
                LayoutParams layoutParams = this.params;
                if (!z) {
                    i = Screen.dpToPx((float) i);
                }
                layoutParams.width = i;
            }
            return this;
        }

        @NonNull
        public Layout height(int i, boolean z) {
            if (this.params != null) {
                LayoutParams layoutParams = this.params;
                if (!z) {
                    i = Screen.dpToPx((float) i);
                }
                layoutParams.height = i;
            }
            return this;
        }

        @NonNull
        public Layout topPadding(int i, boolean z) {
            checkView(Ui.PADDING_NPE);
            View view = this.view;
            int paddingLeft = this.view.getPaddingLeft();
            if (!z) {
                i = Screen.dpToPx((float) i);
            }
            view.setPadding(paddingLeft, i, this.view.getPaddingRight(), this.view.getPaddingBottom());
            return this;
        }

        @NonNull
        public Layout leftPadding(int i, boolean z) {
            checkView(Ui.PADDING_NPE);
            View view = this.view;
            if (!z) {
                i = Screen.dpToPx((float) i);
            }
            view.setPadding(i, this.view.getPaddingTop(), this.view.getPaddingRight(), this.view.getPaddingBottom());
            return this;
        }

        @NonNull
        public Layout rightPadding(int i, boolean z) {
            checkView(Ui.PADDING_NPE);
            View view = this.view;
            int paddingLeft = this.view.getPaddingLeft();
            int paddingTop = this.view.getPaddingTop();
            if (!z) {
                i = Screen.dpToPx((float) i);
            }
            view.setPadding(paddingLeft, paddingTop, i, this.view.getPaddingBottom());
            return this;
        }

        @NonNull
        public Layout bottomPadding(int i, boolean z) {
            checkView(Ui.PADDING_NPE);
            View view = this.view;
            int paddingLeft = this.view.getPaddingLeft();
            int paddingTop = this.view.getPaddingTop();
            int paddingRight = this.view.getPaddingRight();
            if (!z) {
                i = Screen.dpToPx((float) i);
            }
            view.setPadding(paddingLeft, paddingTop, paddingRight, i);
            return this;
        }

        public void done() {
            if (this.view != null) {
                this.view.setLayoutParams(this.params);
            }
        }
    }

    public static class Margin extends Layout {
        MarginLayoutParams params;

        Margin(@NonNull View view) {
            super(view);
            this.params = (MarginLayoutParams) view.getLayoutParams();
        }

        Margin(@NonNull MarginLayoutParams marginLayoutParams) {
            super((LayoutParams) marginLayoutParams);
            this.params = marginLayoutParams;
        }

        @NonNull
        public static Margin on(@NonNull View view) {
            return new Margin(view);
        }

        @NonNull
        public static Margin on(@NonNull MarginLayoutParams marginLayoutParams) {
            return new Margin(marginLayoutParams);
        }

        @NonNull
        public Margin top(int i, boolean z) {
            MarginLayoutParams marginLayoutParams = this.params;
            if (!z) {
                i = Screen.dpToPx((float) i);
            }
            marginLayoutParams.topMargin = i;
            return this;
        }

        @NonNull
        public Margin left(int i, boolean z) {
            MarginLayoutParams marginLayoutParams = this.params;
            if (!z) {
                i = Screen.dpToPx((float) i);
            }
            marginLayoutParams.leftMargin = i;
            return this;
        }

        @NonNull
        public Margin right(int i, boolean z) {
            MarginLayoutParams marginLayoutParams = this.params;
            if (!z) {
                i = Screen.dpToPx((float) i);
            }
            marginLayoutParams.rightMargin = i;
            return this;
        }

        @NonNull
        public Margin bottom(int i, boolean z) {
            MarginLayoutParams marginLayoutParams = this.params;
            if (!z) {
                i = Screen.dpToPx((float) i);
            }
            marginLayoutParams.bottomMargin = i;
            return this;
        }

        public void done() {
            if (this.view != null) {
                this.view.setLayoutParams(this.params);
            }
        }
    }

    public static boolean isUiThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    @NonNull
    public static StateListDrawable makeSelector(@NonNull Drawable drawable, @ColorRes int i, @ColorRes int i2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable layerDrawable = new LayerDrawable(new Drawable[]{drawable, new ColorDrawable(ContextCompat.getColor(IM.context(), i))});
        Drawable layerDrawable2 = new LayerDrawable(new Drawable[]{drawable, new ColorDrawable(ContextCompat.getColor(IM.context(), i2))});
        stateListDrawable.addState(new int[]{16842919}, layerDrawable);
        stateListDrawable.addState(new int[]{16842908}, layerDrawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, drawable);
        return stateListDrawable;
    }

    public static boolean visible(@Nullable View view, boolean z) {
        return visible(view, z, true);
    }

    public static boolean visible(@Nullable View view, boolean z, boolean z2) {
        if (view == null) {
            return false;
        }
        if (z) {
            view.setVisibility(0);
            return true;
        }
        if (z2) {
            view.setVisibility(true);
        } else {
            view.setVisibility(true);
        }
        return false;
    }

    @NonNull
    public static Point getRelativePosition(@NonNull View view, @Nullable View view2) {
        Point point = new Point(view.getLeft(), view.getTop());
        view = view.getParent();
        if (view == null) {
            return point;
        }
        do {
            if (view instanceof View) {
                View view3 = view;
                point.x += view3.getLeft();
                point.y += view3.getTop();
            }
            view = view.getParent();
            if (!(view instanceof View)) {
                break;
            }
        } while (view != view2);
        return point;
    }

    @NonNull
    public static Point getPositionOnScreen(@NonNull View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new Point(iArr[0], iArr[1]);
    }

    @Nullable
    public static Bitmap createScreenShoot(@Nullable Fragment fragment) {
        return fragment != null ? createScreenShoot(fragment.getView()) : null;
    }

    @Nullable
    public static Bitmap createScreenShoot(@Nullable View view) {
        if (view == null) {
            return null;
        }
        try {
            view.setDrawingCacheEnabled(true);
            Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
            return createBitmap;
        } catch (View view2) {
            Log.exs(view2);
            return null;
        }
    }

    public static void runOnNextLayout(@NonNull final View view, @NonNull final Runnable runnable) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (VERSION.SDK_INT >= 16) {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                runnable.run();
            }
        });
    }

    public static void hideSoftInput(@android.support.annotation.NonNull android.view.View r2) {
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
        r0 = com.inverce.mod.core.IM.context();	 Catch:{ Exception -> 0x0014 }
        r1 = "input_method";	 Catch:{ Exception -> 0x0014 }
        r0 = r0.getSystemService(r1);	 Catch:{ Exception -> 0x0014 }
        r0 = (android.view.inputmethod.InputMethodManager) r0;	 Catch:{ Exception -> 0x0014 }
        r2 = r2.getWindowToken();	 Catch:{ Exception -> 0x0014 }
        r1 = 0;	 Catch:{ Exception -> 0x0014 }
        r0.hideSoftInputFromWindow(r2, r1);	 Catch:{ Exception -> 0x0014 }
    L_0x0014:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inverce.mod.core.Ui.hideSoftInput(android.view.View):void");
    }

    public static void showSoftInput(@NonNull View view) {
        showSoftInput(view, false);
    }

    public static void showSoftInput(@android.support.annotation.NonNull android.view.View r2, boolean r3) {
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
        r0 = com.inverce.mod.core.IM.context();	 Catch:{ Exception -> 0x0017 }
        r1 = "input_method";	 Catch:{ Exception -> 0x0017 }
        r0 = r0.getSystemService(r1);	 Catch:{ Exception -> 0x0017 }
        r0 = (android.view.inputmethod.InputMethodManager) r0;	 Catch:{ Exception -> 0x0017 }
        if (r3 == 0) goto L_0x0013;	 Catch:{ Exception -> 0x0017 }
    L_0x000e:
        r3 = 1;	 Catch:{ Exception -> 0x0017 }
        r0.showSoftInput(r2, r3);	 Catch:{ Exception -> 0x0017 }
        goto L_0x0017;	 Catch:{ Exception -> 0x0017 }
    L_0x0013:
        r2 = 0;	 Catch:{ Exception -> 0x0017 }
        r0.toggleSoftInput(r2, r2);	 Catch:{ Exception -> 0x0017 }
    L_0x0017:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inverce.mod.core.Ui.showSoftInput(android.view.View, boolean):void");
    }

    public static String deAccent(@NonNull String str) {
        return Normalizer.normalize(str, Form.NFD).replaceAll("ł", "l").replaceAll("Ł", "L").replaceAll("[^\\p{ASCII}]", "");
    }
}
