package com.mevo.app.data.model;

import android.support.annotation.Nullable;
import com.annimon.stream.Stream;
import java.util.Comparator;
import java.util.List;
import org.simpleframework.xml.Attribute;

public class PurchasedTariff {
    @Attribute(name = "active")
    public int active;
    @Attribute(name = "created_at")
    public long createdAtTsSeconds;
    @Attribute(name = "currency")
    public String currency;
    @Attribute(name = "deleted")
    public int deleted;
    @Attribute(name = "deleted_at")
    public long deletedAt;
    @Attribute(name = "domain")
    public String domain;
    @Attribute(name = "free_text")
    public String freeText;
    @Attribute(name = "id")
    public int id;
    @Attribute(name = "name")
    public String name;
    @Attribute(name = "price")
    public double priceCents;
    @Attribute(name = "text")
    public String text;
    @Attribute(name = "valid_days")
    public int validDays;
    @Attribute(name = "valid_from")
    public long validFrom;
    @Attribute(name = "valid_to")
    public long validTo;

    public static class CreatedAtNewestComparator implements Comparator<PurchasedTariff> {
        public int compare(PurchasedTariff purchasedTariff, PurchasedTariff purchasedTariff2) {
            return -Long.compare(purchasedTariff.createdAtTsSeconds, purchasedTariff2.createdAtTsSeconds);
        }
    }

    public boolean isDeleted() {
        return this.deleted == 1;
    }

    @Nullable
    public static PurchasedTariff getCurrentlyActive(@Nullable List<PurchasedTariff> list) {
        return getActiveOnTs(list, System.currentTimeMillis() / 1000);
    }

    @Nullable
    public static PurchasedTariff getActiveOnTs(@Nullable List<PurchasedTariff> list, long j) {
        if (list == null) {
            return null;
        }
        return (PurchasedTariff) Stream.of((Iterable) list).filter(PurchasedTariff$$Lambda$0.$instance).filter(new PurchasedTariff$$Lambda$1(j)).sorted(new CreatedAtNewestComparator()).findFirst().orElse(null);
    }

    static final /* synthetic */ boolean lambda$getActiveOnTs$7$PurchasedTariff(long j, PurchasedTariff purchasedTariff) {
        return (purchasedTariff.createdAtTsSeconds > j || purchasedTariff.validTo <= j) ? 0 : 1;
    }
}
