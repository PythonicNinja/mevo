package com.mevo.app.constants;

import android.support.annotation.Nullable;
import com.annimon.stream.Stream;
import com.mevo.app.C0434R;
import com.mevo.app.data.model.TariffExtraInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tariffs {
    public static final List<TariffExtraInfo> ALL_SUBSCRIPTIONS = new ArrayList();
    private static final TariffExtraInfo SUBSCRIPTION_2_DAYS = new TariffExtraInfo(3651, C0434R.string.subscription_name_2_days, 2000.0d, "PLN", "658065", 2, 300, 5, 2, 30000);
    private static final TariffExtraInfo SUBSCRIPTION_2_DAYS_PLUS = new TariffExtraInfo(3681, C0434R.string.subscription_name_2_days_plus, 4000.0d, "PLN", "311031", 2, 700, 5, 5, 30000);
    private static final TariffExtraInfo SUBSCRIPTION_30_DAYS = new TariffExtraInfo(3647, C0434R.string.subscription_name_30_days, 1000.0d, "PLN", "471047", 30, 90, 5, 1, 20000);
    private static final TariffExtraInfo SUBSCRIPTION_5_DAYS = new TariffExtraInfo(3649, C0434R.string.subscription_name_5_days, 4000.0d, "PLN", "956895", 5, 300, 5, 2, 30000);
    private static final TariffExtraInfo SUBSCRIPTION_5_DAYS_PLUS = new TariffExtraInfo(3683, C0434R.string.subscription_name_5_days_plus, 8000.0d, "PLN", "179717", 5, 700, 5, 5, 30000);
    private static final TariffExtraInfo SUBSCRIPTION_YEAR = new TariffExtraInfo(3646, C0434R.string.subscription_name_year, 10000.0d, "PLN", "758075", 365, 90, 5, 1, 20000);
    private static final TariffExtraInfo SUBSCRIPTION_YEAR_PLUS = new TariffExtraInfo(3679, C0434R.string.subscription_name_year_plus, 15000.0d, "PLN", "122612", 365, 120, 5, 2, 20000);
    public static final TariffExtraInfo Tariff_PAY_AS_YOU_GO = new TariffExtraInfo(-1, C0434R.string.subscription_name_pay_as_you_go, 0.0d, "PLN", "no_code", 0, 0, 10, 1, 30000);

    static {
        ALL_SUBSCRIPTIONS.addAll(Arrays.asList(new TariffExtraInfo[]{SUBSCRIPTION_2_DAYS, SUBSCRIPTION_2_DAYS_PLUS, SUBSCRIPTION_5_DAYS, SUBSCRIPTION_5_DAYS_PLUS, SUBSCRIPTION_30_DAYS, SUBSCRIPTION_YEAR, SUBSCRIPTION_YEAR_PLUS}));
    }

    @Nullable
    public static TariffExtraInfo getForCode(String str) {
        return (TariffExtraInfo) Stream.of(ALL_SUBSCRIPTIONS).filter(new Tariffs$$Lambda$0(str)).findFirst().orElse(null);
    }

    @Nullable
    public static TariffExtraInfo getForId(int i) {
        return (TariffExtraInfo) Stream.of(ALL_SUBSCRIPTIONS).filter(new Tariffs$$Lambda$1(i)).findFirst().orElse(null);
    }

    static final /* synthetic */ boolean lambda$getForId$3$Tariffs(int i, TariffExtraInfo tariffExtraInfo) {
        return tariffExtraInfo.getUid() == i;
    }
}
