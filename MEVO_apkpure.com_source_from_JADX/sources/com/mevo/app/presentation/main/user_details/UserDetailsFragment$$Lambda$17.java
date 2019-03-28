package com.mevo.app.presentation.main.user_details;

import com.annimon.stream.function.Predicate;
import com.mevo.app.presentation.custom_views.CustomInput;

final /* synthetic */ class UserDetailsFragment$$Lambda$17 implements Predicate {
    static final Predicate $instance = new UserDetailsFragment$$Lambda$17();

    private UserDetailsFragment$$Lambda$17() {
    }

    public boolean test(Object obj) {
        return (((CustomInput) obj).isValid().booleanValue() ^ 1);
    }
}
