package com.mevo.app.constants;

import java.util.HashMap;

public class PrivacyPolicies {
    public static final String DEFAULT_PRIVACY_POLICY_URL = "https://rowermevo.pl/privacy_pl.html";
    private static final String PRIVACY_POLICY_URL_DE = "https://rowermevo.pl/privacy_de.html";
    private static final String PRIVACY_POLICY_URL_EN = "https://rowermevo.pl/privacy_en.html";
    private static final String PRIVACY_POLICY_URL_PL = "https://rowermevo.pl/privacy_pl.html";
    private static final String PRIVACY_POLICY_URL_RU = "https://rowermevo.pl/privacy_ru.html";
    public static final HashMap<String, String> PRIVACY_POLICY_USE_URL = new HashMap();

    static {
        PRIVACY_POLICY_USE_URL.put(AppLanguageCode.PL.getCode().toLowerCase(), "https://rowermevo.pl/privacy_pl.html");
        PRIVACY_POLICY_USE_URL.put(AppLanguageCode.EN.getCode().toLowerCase(), PRIVACY_POLICY_URL_EN);
        PRIVACY_POLICY_USE_URL.put(AppLanguageCode.DE.getCode().toLowerCase(), PRIVACY_POLICY_URL_DE);
        PRIVACY_POLICY_USE_URL.put(AppLanguageCode.RU.getCode().toLowerCase(), PRIVACY_POLICY_URL_RU);
    }
}
