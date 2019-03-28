package com.mevo.app.tools.formatters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.HashMap;

public class CurrencyFormatter {
    private static final HashMap<String, String> CURRENCY_MAPPING = new HashMap();

    static {
        CURRENCY_MAPPING.put("PLN", "zł");
        CURRENCY_MAPPING.put("USD", "$");
    }

    @NonNull
    public static String formatCurrencyAbbreviation(@Nullable String str) {
        if (str == null) {
            return "";
        }
        String str2 = (String) CURRENCY_MAPPING.get(str.toUpperCase());
        return str2 != null ? str2 : str;
    }
}
