package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.xmlpull.v1.XmlPullParser;

public class Slide extends Visibility {
    private static final String PROPNAME_SCREEN_POSITION = "android:slide:screenPosition";
    private static final TimeInterpolator sAccelerate = new AccelerateInterpolator();
    private static final CalculateSlide sCalculateBottom = new C09376();
    private static final CalculateSlide sCalculateEnd = new C09365();
    private static final CalculateSlide sCalculateLeft = new C09321();
    private static final CalculateSlide sCalculateRight = new C09354();
    private static final CalculateSlide sCalculateStart = new C09332();
    private static final CalculateSlide sCalculateTop = new C09343();
    private static final TimeInterpolator sDecelerate = new DecelerateInterpolator();
    private CalculateSlide mSlideCalculator = sCalculateBottom;
    private int mSlideEdge = 80;

    private interface CalculateSlide {
        float getGoneX(ViewGroup viewGroup, View view);

        float getGoneY(ViewGroup viewGroup, View view);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GravityFlag {
    }

    private static abstract class CalculateSlideHorizontal implements CalculateSlide {
        private CalculateSlideHorizontal() {
        }

        public float getGoneY(ViewGroup viewGroup, View view) {
            return view.getTranslationY();
        }
    }

    private static abstract class CalculateSlideVertical implements CalculateSlide {
        private CalculateSlideVertical() {
        }

        public float getGoneX(ViewGroup viewGroup, View view) {
            return view.getTranslationX();
        }
    }

    /* renamed from: android.support.transition.Slide$1 */
    static class C09321 extends CalculateSlideHorizontal {
        C09321() {
            super();
        }

        public float getGoneX(ViewGroup viewGroup, View view) {
            return view.getTranslationX() - ((float) viewGroup.getWidth());
        }
    }

    /* renamed from: android.support.transition.Slide$2 */
    static class C09332 extends CalculateSlideHorizontal {
        C09332() {
            super();
        }

        public float getGoneX(ViewGroup viewGroup, View view) {
            Object obj = 1;
            if (ViewCompat.getLayoutDirection(viewGroup) != 1) {
                obj = null;
            }
            if (obj != null) {
                return view.getTranslationX() + ((float) viewGroup.getWidth());
            }
            return view.getTranslationX() - ((float) viewGroup.getWidth());
        }
    }

    /* renamed from: android.support.transition.Slide$3 */
    static class C09343 extends CalculateSlideVertical {
        C09343() {
            super();
        }

        public float getGoneY(ViewGroup viewGroup, View view) {
            return view.getTranslationY() - ((float) viewGroup.getHeight());
        }
    }

    /* renamed from: android.support.transition.Slide$4 */
    static class C09354 extends CalculateSlideHorizontal {
        C09354() {
            super();
        }

        public float getGoneX(ViewGroup viewGroup, View view) {
            return view.getTranslationX() + ((float) viewGroup.getWidth());
        }
    }

    /* renamed from: android.support.transition.Slide$5 */
    static class C09365 extends CalculateSlideHorizontal {
        C09365() {
            super();
        }

        public float getGoneX(ViewGroup viewGroup, View view) {
            Object obj = 1;
            if (ViewCompat.getLayoutDirection(viewGroup) != 1) {
                obj = null;
            }
            if (obj != null) {
                return view.getTranslationX() - ((float) viewGroup.getWidth());
            }
            return view.getTranslationX() + ((float) viewGroup.getWidth());
        }
    }

    /* renamed from: android.support.transition.Slide$6 */
    static class C09376 extends CalculateSlideVertical {
        C09376() {
            super();
        }

        public float getGoneY(ViewGroup viewGroup, View view) {
            return view.getTranslationY() + ((float) viewGroup.getHeight());
        }
    }

    public Slide() {
        setSlideEdge(80);
    }

    public Slide(int i) {
        setSlideEdge(i);
    }

    public Slide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        context = context.obtainStyledAttributes(attributeSet, Styleable.SLIDE);
        attributeSet = TypedArrayUtils.getNamedInt(context, (XmlPullParser) attributeSet, "slideEdge", 0, 80);
        context.recycle();
        setSlideEdge(attributeSet);
    }

    private void captureValues(TransitionValues transitionValues) {
        Object obj = new int[2];
        transitionValues.view.getLocationOnScreen(obj);
        transitionValues.values.put(PROPNAME_SCREEN_POSITION, obj);
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        captureValues(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
        captureValues(transitionValues);
    }

    public void setSlideEdge(int i) {
        if (i == 3) {
            this.mSlideCalculator = sCalculateLeft;
        } else if (i == 5) {
            this.mSlideCalculator = sCalculateRight;
        } else if (i == 48) {
            this.mSlideCalculator = sCalculateTop;
        } else if (i == 80) {
            this.mSlideCalculator = sCalculateBottom;
        } else if (i == GravityCompat.START) {
            this.mSlideCalculator = sCalculateStart;
        } else if (i != GravityCompat.END) {
            throw new IllegalArgumentException("Invalid slide direction");
        } else {
            this.mSlideCalculator = sCalculateEnd;
        }
        this.mSlideEdge = i;
        TransitionPropagation sidePropagation = new SidePropagation();
        sidePropagation.setSide(i);
        setPropagation(sidePropagation);
    }

    public int getSlideEdge() {
        return this.mSlideEdge;
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues2 == null) {
            return null;
        }
        int[] iArr = (int[]) transitionValues2.values.get(PROPNAME_SCREEN_POSITION);
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        return TranslationAnimationCreator.createAnimation(view, transitionValues2, iArr[null], iArr[1], this.mSlideCalculator.getGoneX(viewGroup, view), this.mSlideCalculator.getGoneY(viewGroup, view), translationX, translationY, sDecelerate);
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null) {
            return null;
        }
        int[] iArr = (int[]) transitionValues.values.get(PROPNAME_SCREEN_POSITION);
        return TranslationAnimationCreator.createAnimation(view, transitionValues, iArr[null], iArr[1], view.getTranslationX(), view.getTranslationY(), this.mSlideCalculator.getGoneX(viewGroup, view), this.mSlideCalculator.getGoneY(viewGroup, view), sAccelerate);
    }
}
