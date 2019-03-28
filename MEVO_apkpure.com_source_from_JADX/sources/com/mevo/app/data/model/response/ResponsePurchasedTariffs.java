package com.mevo.app.data.model.response;

import android.support.annotation.Nullable;
import com.mevo.app.data.model.PurchasedTariff;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponsePurchasedTariffs {
    @Element(name = "tariffs", required = false)
    private PurchasedTariffsWrapper purchasedTarrifsWrapper;
    @Attribute(name = "server_time", required = false)
    private long serverTime;

    public static class PurchasedTariffsWrapper {
        @ElementList(entry = "tariff", inline = true, required = false)
        private List<PurchasedTariff> purchasedTariffs;

        public List<PurchasedTariff> getPurchasedTariffs() {
            if (this.purchasedTariffs == null) {
                this.purchasedTariffs = new ArrayList();
            }
            return this.purchasedTariffs;
        }

        public PurchasedTariffsWrapper setPurchasedTariffs(List<PurchasedTariff> list) {
            this.purchasedTariffs = list;
            return this;
        }

        @Nullable
        public PurchasedTariff getCurrentActiveTariff() {
            return PurchasedTariff.getCurrentlyActive(getPurchasedTariffs());
        }

        @Nullable
        public PurchasedTariff getTariffActiveOnTs(long j) {
            return PurchasedTariff.getActiveOnTs(getPurchasedTariffs(), j);
        }
    }

    public PurchasedTariffsWrapper getPurchasedTarrifsWrapper() {
        return this.purchasedTarrifsWrapper;
    }

    public ResponsePurchasedTariffs setPurchasedTarrifsWrapper(PurchasedTariffsWrapper purchasedTariffsWrapper) {
        this.purchasedTarrifsWrapper = purchasedTariffsWrapper;
        return this;
    }

    public List<PurchasedTariff> getPurchasedTariffs() {
        if (this.purchasedTarrifsWrapper == null) {
            return new ArrayList();
        }
        return this.purchasedTarrifsWrapper.getPurchasedTariffs();
    }

    @Nullable
    public PurchasedTariff getCurrentActiveTariff() {
        if (this.purchasedTarrifsWrapper == null) {
            return null;
        }
        return this.purchasedTarrifsWrapper.getCurrentActiveTariff();
    }

    @Nullable
    public PurchasedTariff getTariffActiveOnTs(long j) {
        if (this.purchasedTarrifsWrapper == null) {
            return 0;
        }
        return this.purchasedTarrifsWrapper.getTariffActiveOnTs(j);
    }
}
