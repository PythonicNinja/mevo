package com.mevo.app.constants;

import com.mevo.app.C0434R;
import com.mevo.app.data.model.RegisterAgreement;
import java.util.Arrays;
import java.util.List;

public class RegisterAgreements {
    public static final List<RegisterAgreement> AGREEMENTS_PROD = Arrays.asList(new RegisterAgreement[]{TERMS, TC_TRANSMISSION_1, TC_PROCESSING_1, TC_PROCESSING_2, TC_TRANSMISSION_2, TC_TRANSMISSION_3});
    private static final RegisterAgreement TC_PROCESSING_1 = new RegisterAgreement(605, C0434R.string.register_terms_3, false);
    private static final RegisterAgreement TC_PROCESSING_2 = new RegisterAgreement(606, C0434R.string.register_terms_4, false);
    private static final RegisterAgreement TC_TRANSMISSION_1 = new RegisterAgreement(604, C0434R.string.register_terms_2, false);
    public static final RegisterAgreement TC_TRANSMISSION_2 = new RegisterAgreement(607, C0434R.string.register_terms_5, false);
    private static final RegisterAgreement TC_TRANSMISSION_3 = new RegisterAgreement(608, C0434R.string.register_terms_6, false);
    private static final RegisterAgreement TERMS = new RegisterAgreement(568, C0434R.string.register_terms_1, true);
}
