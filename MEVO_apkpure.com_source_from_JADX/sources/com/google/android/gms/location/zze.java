package com.google.android.gms.location;

import java.util.Comparator;

final class zze implements Comparator<ActivityTransition> {
    zze() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        ActivityTransition activityTransition = (ActivityTransition) obj;
        ActivityTransition activityTransition2 = (ActivityTransition) obj2;
        int activityType = activityTransition.getActivityType();
        int activityType2 = activityTransition2.getActivityType();
        if (activityType != activityType2) {
            return activityType < activityType2 ? -1 : 1;
        } else {
            int transitionType = activityTransition.getTransitionType();
            int transitionType2 = activityTransition2.getTransitionType();
            return transitionType == transitionType2 ? 0 : transitionType < transitionType2 ? -1 : 1;
        }
    }
}
