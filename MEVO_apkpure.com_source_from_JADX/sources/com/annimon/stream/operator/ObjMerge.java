package com.annimon.stream.operator;

import com.annimon.stream.function.BiFunction;
import com.annimon.stream.iterator.LsaIterator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ObjMerge<T> extends LsaIterator<T> {
    private final Queue<T> buffer1 = new LinkedList();
    private final Queue<T> buffer2 = new LinkedList();
    private final Iterator<? extends T> iterator1;
    private final Iterator<? extends T> iterator2;
    private final BiFunction<? super T, ? super T, MergeResult> selector;

    /* renamed from: com.annimon.stream.operator.ObjMerge$1 */
    static /* synthetic */ class C03381 {
        static final /* synthetic */ int[] $SwitchMap$com$annimon$stream$operator$ObjMerge$MergeResult = new int[MergeResult.values().length];

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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
            /*
            r0 = com.annimon.stream.operator.ObjMerge.MergeResult.values();
            r0 = r0.length;
            r0 = new int[r0];
            $SwitchMap$com$annimon$stream$operator$ObjMerge$MergeResult = r0;
            r0 = $SwitchMap$com$annimon$stream$operator$ObjMerge$MergeResult;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = com.annimon.stream.operator.ObjMerge.MergeResult.TAKE_FIRST;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = 1;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r0 = $SwitchMap$com$annimon$stream$operator$ObjMerge$MergeResult;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = com.annimon.stream.operator.ObjMerge.MergeResult.TAKE_SECOND;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2 = 2;	 Catch:{ NoSuchFieldError -> 0x001f }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.operator.ObjMerge.1.<clinit>():void");
        }
    }

    public enum MergeResult {
        TAKE_FIRST,
        TAKE_SECOND
    }

    public ObjMerge(Iterator<? extends T> it, Iterator<? extends T> it2, BiFunction<? super T, ? super T, MergeResult> biFunction) {
        this.iterator1 = it;
        this.iterator2 = it2;
        this.selector = biFunction;
    }

    public boolean hasNext() {
        if (this.buffer1.isEmpty() && this.buffer2.isEmpty() && !this.iterator1.hasNext()) {
            if (!this.iterator2.hasNext()) {
                return false;
            }
        }
        return true;
    }

    public T nextIteration() {
        T poll;
        if (!this.buffer1.isEmpty()) {
            poll = this.buffer1.poll();
            return this.iterator2.hasNext() ? select(poll, this.iterator2.next()) : poll;
        } else if (!this.buffer2.isEmpty()) {
            poll = this.buffer2.poll();
            return this.iterator1.hasNext() ? select(this.iterator1.next(), poll) : poll;
        } else if (!this.iterator1.hasNext()) {
            return this.iterator2.next();
        } else {
            if (this.iterator2.hasNext()) {
                return select(this.iterator1.next(), this.iterator2.next());
            }
            return this.iterator1.next();
        }
    }

    private T select(T t, T t2) {
        if (C03381.$SwitchMap$com$annimon$stream$operator$ObjMerge$MergeResult[((MergeResult) this.selector.apply(t, t2)).ordinal()] != 1) {
            this.buffer1.add(t);
            return t2;
        }
        this.buffer2.add(t2);
        return t;
    }
}
