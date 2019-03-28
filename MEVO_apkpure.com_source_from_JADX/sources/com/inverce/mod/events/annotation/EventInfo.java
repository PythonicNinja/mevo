package com.inverce.mod.events.annotation;

import android.support.annotation.NonNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EventInfo {
    @NonNull
    ThreadPolicy thread() default ThreadPolicy.CallingThread;

    boolean yieldAll() default false;
}
