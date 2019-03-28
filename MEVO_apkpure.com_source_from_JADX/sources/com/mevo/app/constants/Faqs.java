package com.mevo.app.constants;

import java.util.HashMap;

public class Faqs {
    public static final String DEFAULT_FAQ_URL = "https://rowermevo.pl/faq_pl.html";
    private static final String FAQ_URL_DE = "https://rowermevo.pl/faq_de.html";
    private static final String FAQ_URL_EN = "https://rowermevo.pl/faq_en.html";
    private static final String FAQ_URL_PL = "https://rowermevo.pl/faq_pl.html";
    private static final String FAQ_URL_RU = "https://rowermevo.pl/faq_ru.html";
    public static final HashMap<String, String> FAQ_USE_URL = new HashMap();

    static {
        FAQ_USE_URL.put(AppLanguageCode.PL.getCode().toLowerCase(), "https://rowermevo.pl/faq_pl.html");
        FAQ_USE_URL.put(AppLanguageCode.EN.getCode().toLowerCase(), FAQ_URL_EN);
        FAQ_USE_URL.put(AppLanguageCode.DE.getCode().toLowerCase(), FAQ_URL_DE);
        FAQ_USE_URL.put(AppLanguageCode.RU.getCode().toLowerCase(), FAQ_URL_RU);
    }
}
