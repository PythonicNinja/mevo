package com.mevo.app.constants;

import java.util.HashMap;

public class TermsOfUse {
    public static final String DEFAULT_TERMS_OF_USE_URL = "https://rowermevo.pl/terms_pl.html";
    public static final HashMap<String, String> TERMS_OF_USE_URL = new HashMap();
    private static final String TERMS_OF_USE_URL_DE = "https://rowermevo.pl/terms_de.html";
    private static final String TERMS_OF_USE_URL_EN = "https://rowermevo.pl/terms_en.html";
    private static final String TERMS_OF_USE_URL_PL = "https://rowermevo.pl/terms_pl.html";
    private static final String TERMS_OF_USE_URL_RU = "https://rowermevo.pl/terms_ru.html";

    static {
        TERMS_OF_USE_URL.put(AppLanguageCode.PL.getCode().toLowerCase(), "https://rowermevo.pl/terms_pl.html");
        TERMS_OF_USE_URL.put(AppLanguageCode.EN.getCode().toLowerCase(), TERMS_OF_USE_URL_EN);
        TERMS_OF_USE_URL.put(AppLanguageCode.DE.getCode().toLowerCase(), TERMS_OF_USE_URL_DE);
        TERMS_OF_USE_URL.put(AppLanguageCode.RU.getCode().toLowerCase(), TERMS_OF_USE_URL_RU);
    }
}
