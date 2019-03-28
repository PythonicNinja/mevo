package com.inverce.mod.integrations.support.recycler;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.inverce.mod.core.functional.IFunction;
import com.inverce.mod.core.functional.IPredicate;
import com.inverce.mod.integrations.support.annotations.IBind;
import com.inverce.mod.integrations.support.annotations.IBinder;
import com.inverce.mod.integrations.support.annotations.ICreateVH;
import java.util.ArrayList;
import java.util.List;

public class MultiRecyclerAdapter<ITEM> extends RecyclerAdapter<ITEM, ViewHolder> {
    @NonNull
    public IPredicate<?> ANY = MultiRecyclerAdapter$$Lambda$0.$instance;
    protected List<MultiInfo<?, ?>> types = new ArrayList();
    protected SparseArray<MultiInfo<?, ?>> typesSparse = new SparseArray();

    protected class MultiInfo<I extends ITEM, VH extends ViewHolder> {
        IBind<I, VH> binder;
        IPredicate<ITEM> checkType;
        ICreateVH<VH> createViewHolder;
        int registeredType;

        public MultiInfo(IPredicate<ITEM> iPredicate, IBind<I, VH> iBind, ICreateVH<VH> iCreateVH) {
            this.checkType = iPredicate;
            this.binder = iBind;
            this.createViewHolder = iCreateVH;
        }

        public void onBindViewHolder(ViewHolder viewHolder, ITEM item, int i) {
            this.binder.onBindViewHolder(viewHolder, item, i);
        }
    }

    /* renamed from: com.inverce.mod.integrations.support.recycler.MultiRecyclerAdapter$2 */
    class C07962 implements IBind<I, VH> {
        C07962() {
        }

        public void onBindViewHolder(@NonNull VH vh, I i, int i2) {
            ((IBinder) vh).onBindViewHolder(i, i2);
        }
    }

    /* renamed from: com.inverce.mod.integrations.support.recycler.MultiRecyclerAdapter$3 */
    class C07973 implements IBind<I, VH> {
        C07973() {
        }

        public void onBindViewHolder(@NonNull VH vh, I i, int i2) {
            ((IBinder) vh).onBindViewHolder(i, i2);
        }
    }

    public final void onBindViewHolder(ViewHolder viewHolder, ITEM item, int i) {
        ((MultiInfo) this.typesSparse.get(getItemViewType(i))).onBindViewHolder(viewHolder, item, i);
    }

    public final ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MultiInfo multiInfo = (MultiInfo) this.typesSparse.get(i);
        return multiInfo.createViewHolder.onCreateViewHolder(viewGroup, LayoutInflater.from(viewGroup.getContext()));
    }

    public final int getItemViewType(int i) {
        i = getItem(i);
        for (MultiInfo multiInfo : this.types) {
            if (multiInfo.checkType.test(i)) {
                return multiInfo.registeredType;
            }
        }
        return -1;
    }

    public <I extends ITEM, VH extends ViewHolder> int register(IPredicate<ITEM> iPredicate, IBind<I, VH> iBind, ICreateVH<VH> iCreateVH) {
        MultiInfo multiInfo = new MultiInfo(iPredicate, iBind, iCreateVH);
        this.types.add(multiInfo);
        multiInfo.registeredType = this.types.size();
        this.typesSparse.put(multiInfo.registeredType, multiInfo);
        return multiInfo.registeredType;
    }

    public <I extends ITEM, VH extends ViewHolder> int register(IPredicate<ITEM> iPredicate, IBind<I, VH> iBind, @NonNull final IFunction<View, VH> iFunction, @LayoutRes final int i) {
        return register((IPredicate) iPredicate, (IBind) iBind, new ICreateVH<VH>() {
            public VH onCreateViewHolder(ViewGroup viewGroup, @NonNull LayoutInflater layoutInflater) {
                return (ViewHolder) iFunction.apply(layoutInflater.inflate(i, viewGroup, false));
            }
        });
    }

    public <I extends ITEM, VH extends ViewHolder & IBinder<I>> int register(IPredicate<ITEM> iPredicate, ICreateVH<VH> iCreateVH) {
        return register((IPredicate) iPredicate, new C07962(), (ICreateVH) iCreateVH);
    }

    public <I extends ITEM, VH extends ViewHolder & IBinder<I>> int register(IPredicate<ITEM> iPredicate, @NonNull final IFunction<View, VH> iFunction, @LayoutRes final int i) {
        return register((IPredicate) iPredicate, new C07973(), new ICreateVH<VH>() {
            public VH onCreateViewHolder(ViewGroup viewGroup, @NonNull LayoutInflater layoutInflater) {
                return (ViewHolder) iFunction.apply(layoutInflater.inflate(i, viewGroup, false));
            }
        });
    }

    public <I extends ITEM> int register(IPredicate<ITEM> iPredicate, DataBinder<I> dataBinder, @LayoutRes int i) {
        return register(iPredicate, dataBinder, MultiRecyclerAdapter$$Lambda$1.$instance, i);
    }
}
