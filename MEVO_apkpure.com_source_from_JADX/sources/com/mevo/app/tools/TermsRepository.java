package com.mevo.app.tools;

import com.inverce.mod.core.IM;
import com.mevo.app.constants.Faqs;
import com.mevo.app.constants.PrivacyPolicies;
import com.mevo.app.constants.TermsOfUse;
import java.util.HashMap;

public class TermsRepository {
    public static String getTermsOfUseUrl() {
        return getUrlByLanguage(TermsOfUse.TERMS_OF_USE_URL, TermsOfUse.DEFAULT_TERMS_OF_USE_URL);
    }

    public static String getFaqUrl() {
        return getUrlByLanguage(Faqs.FAQ_USE_URL, Faqs.DEFAULT_FAQ_URL);
    }

    public static String getPrivacyUrl() {
        return getUrlByLanguage(PrivacyPolicies.PRIVACY_POLICY_USE_URL, PrivacyPolicies.DEFAULT_PRIVACY_POLICY_URL);
    }

    private static String getUrlByLanguage(HashMap<String, String> hashMap, String str) {
        String str2 = (String) hashMap.get(LocaleHelper.getLanguage(IM.context()).toLowerCase());
        return str2 == null ? str : str2;
    }
}
