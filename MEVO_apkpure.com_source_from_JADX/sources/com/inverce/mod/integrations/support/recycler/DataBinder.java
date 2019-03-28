package com.inverce.mod.integrations.support.recycler;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.functional.IFunction;
import com.inverce.mod.integrations.support.annotations.IBind;
import com.inverce.mod.integrations.support.annotations.IBind.ToHolder;
import com.inverce.mod.integrations.support.annotations.IBind.ToView;
import com.inverce.mod.integrations.support.annotations.MapValue;
import com.inverce.mod.integrations.support.annotations.MapValue.ToDrawable;
import com.inverce.mod.integrations.support.annotations.MapValue.ToDrawableRes;
import com.inverce.mod.integrations.support.annotations.MapValue.ToStringRes;
import java.util.ArrayList;
import java.util.List;

public class DataBinder<T> implements IBind<T, BindViewHolder> {
    ToView<String, ImageView> loadImage = DataBinder$$Lambda$0.$instance;
    List<IBind<T, BindViewHolder>> tasks = new ArrayList();

    public Resources res() {
        return IM.resources();
    }

    static final /* synthetic */ void lambda$new$0$DataBinder(String str, ImageView imageView, int i) {
        throw new IllegalStateException("Image processor not specified");
    }

    @NonNull
    public <V extends View> DataBinder<T> bind(@NonNull ToHolder<T> toHolder) {
        this.tasks.add(new DataBinder$$Lambda$1(toHolder));
        return this;
    }

    @NonNull
    public <V extends View> DataBinder<T> bind(@NonNull IFunction<BindViewHolder, V> iFunction, @NonNull ToView<T, V> toView) {
        this.tasks.add(new DataBinder$$Lambda$2(iFunction, toView));
        return this;
    }

    static final /* synthetic */ void lambda$bind$2$DataBinder(@NonNull IFunction iFunction, @NonNull ToView toView, BindViewHolder bindViewHolder, Object obj, int i) {
        View view = (View) iFunction.apply(bindViewHolder);
        if (view != null) {
            toView.bind(obj, view, i);
        }
    }

    @NonNull
    public <V extends View> DataBinder<T> bind(@IdRes int i, @NonNull ToView<T, V> toView) {
        return bind(new DataBinder$$Lambda$3(i), (ToView) toView);
    }

    @NonNull
    public DataBinder<T> bindText(@IdRes int i, @NonNull MapValue<T, String> mapValue) {
        return bind(new DataBinder$$Lambda$4(i), new DataBinder$$Lambda$5(mapValue));
    }

    @NonNull
    public DataBinder<T> bindTextRes(@IdRes int i, @NonNull ToStringRes<T> toStringRes) {
        return bind(new DataBinder$$Lambda$6(i), new DataBinder$$Lambda$7(toStringRes));
    }

    @NonNull
    public DataBinder<T> bindImageRes(@IdRes int i, @NonNull ToDrawableRes<T> toDrawableRes) {
        return bind(new DataBinder$$Lambda$8(i), new DataBinder$$Lambda$9(toDrawableRes));
    }

    @NonNull
    public DataBinder<T> bindImage(@IdRes int i, @NonNull MapValue<T, String> mapValue) {
        return bind(new DataBinder$$Lambda$10(i), new DataBinder$$Lambda$11(this, mapValue));
    }

    final /* synthetic */ void lambda$bindImage$11$DataBinder(@NonNull MapValue mapValue, Object obj, ImageView imageView, int i) {
        this.loadImage.bind(mapValue.get(obj), imageView, i);
    }

    @NonNull
    public DataBinder<T> bindBackgroundRes(@IdRes int i, @NonNull ToDrawableRes<T> toDrawableRes) {
        return bind(new DataBinder$$Lambda$12(i), new DataBinder$$Lambda$13(toDrawableRes));
    }

    @NonNull
    public DataBinder<T> bindBackground(@IdRes int i, @NonNull ToDrawable<T> toDrawable) {
        return bind(new DataBinder$$Lambda$14(i), new DataBinder$$Lambda$15(toDrawable));
    }

    static final /* synthetic */ void lambda$bindBackground$15$DataBinder(@NonNull ToDrawable toDrawable, Object obj, View view, int i) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(toDrawable.get(obj));
        } else {
            view.setBackgroundDrawable(toDrawable.get(obj));
        }
    }

    @NonNull
    public DataBinder<T> bindVisibility(@IdRes int i, @NonNull MapValue<T, Boolean> mapValue) {
        return bind(new DataBinder$$Lambda$16(i), new DataBinder$$Lambda$17(mapValue));
    }

    @NonNull
    public DataBinder<T> bindOnClickListener(@IdRes int i, @NonNull MapValue<T, OnClickListener> mapValue) {
        return bind(new DataBinder$$Lambda$18(i), new DataBinder$$Lambda$19(mapValue));
    }

    public synchronized void onBindViewHolder(BindViewHolder bindViewHolder, T t, int i) {
        for (IBind onBindViewHolder : this.tasks) {
            onBindViewHolder.onBindViewHolder(bindViewHolder, t, i);
        }
    }

    @NonNull
    public DataBinder setLoadImage(ToView<String, ImageView> toView) {
        this.loadImage = toView;
        return this;
    }
}
