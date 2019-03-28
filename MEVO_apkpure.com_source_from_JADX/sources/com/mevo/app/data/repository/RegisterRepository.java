package com.mevo.app.data.repository;

import android.support.annotation.Nullable;
import com.annimon.stream.Stream;
import com.inverce.mod.core.IM;
import com.mevo.app.data.model.RegistrationData;
import com.mevo.app.data.model.User;
import com.mevo.app.data.model.UserDetails;
import com.mevo.app.data.model.response.ErrorResponse;
import com.mevo.app.data.shared_prefs.SharedPrefs;
import com.mevo.app.modules.tracker.UserTracker;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.Utils;
import java.util.List;

public class RegisterRepository {

    public interface RegisterListener {
        void onResponse(boolean z, @Nullable ErrorResponse errorResponse);
    }

    public void register(RegistrationData registrationData, RegisterListener registerListener) {
        IM.onBg().execute(new RegisterRepository$$Lambda$0(this, registrationData, registerListener));
    }

    final /* synthetic */ void lambda$register$57$RegisterRepository(com.mevo.app.data.model.RegistrationData r18, com.mevo.app.data.repository.RegisterRepository.RegisterListener r19) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r17 = this;
        r0 = r18;
        r1 = r19;
        r2 = com.mevo.app.data.network.Rest.getApiNextbike();
        r3 = com.mevo.app.Cfg.get();
        r3 = r3.apikeyNextbike();
        r4 = r0.phoneNumber;
        r5 = r0.domain;
        r5 = r5.getDomainCode();
        r6 = r0.email;
        r7 = r0.firstname;
        r8 = r0.lastname;
        r9 = r0.streetAdress;
        r10 = r0.zipCode;
        r11 = r0.city;
        r12 = r0.country;
        r13 = r0.languageCode;
        r14 = r0.pesel;
        r15 = r18.getNewsletterAgreement();
        r15 = com.mevo.app.tools.Utils.boolToInt(r15);
        r16 = 2;
        r2 = r2.register(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16);
        r2 = r2.execute();	 Catch:{ Exception -> 0x00b2 }
        r3 = r2.body();	 Catch:{ Exception -> 0x00b2 }
        r3 = (com.mevo.app.data.model.response.ResponseRegister) r3;	 Catch:{ Exception -> 0x00b2 }
        if (r3 == 0) goto L_0x0087;	 Catch:{ Exception -> 0x00b2 }
    L_0x0044:
        r4 = r3.user;	 Catch:{ Exception -> 0x00b2 }
        if (r4 == 0) goto L_0x0087;	 Catch:{ Exception -> 0x00b2 }
    L_0x0048:
        r4 = r3.user;	 Catch:{ Exception -> 0x00b2 }
        r4 = r4.getLoginkey();	 Catch:{ Exception -> 0x00b2 }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x00b2 }
        if (r4 != 0) goto L_0x0087;	 Catch:{ Exception -> 0x00b2 }
    L_0x0054:
        r2 = r0.agreements;	 Catch:{ Exception -> 0x00b2 }
        r2 = com.annimon.stream.Stream.of(r2);	 Catch:{ Exception -> 0x00b2 }
        r4 = com.mevo.app.data.repository.RegisterRepository$$Lambda$3.$instance;	 Catch:{ Exception -> 0x00b2 }
        r2 = r2.filter(r4);	 Catch:{ Exception -> 0x00b2 }
        r4 = com.mevo.app.data.repository.RegisterRepository$$Lambda$4.$instance;	 Catch:{ Exception -> 0x00b2 }
        r2 = r2.map(r4);	 Catch:{ Exception -> 0x00b2 }
        r2 = r2.toList();	 Catch:{ Exception -> 0x00b2 }
        r3 = r3.user;	 Catch:{ Exception -> 0x00b2 }
        r3 = r3.getLoginkey();	 Catch:{ Exception -> 0x00b2 }
        r4 = r0.phoneNumber;	 Catch:{ Exception -> 0x00b2 }
        r5 = r17;
        r5.acceptAgreementsAndSaveFailedSync(r2, r3, r4);	 Catch:{ Exception -> 0x00b4 }
        r17.trackUpdateUserWithAgreements(r18);	 Catch:{ Exception -> 0x00b4 }
        r0 = com.inverce.mod.core.IM.onUi();	 Catch:{ Exception -> 0x00b4 }
        r2 = new com.mevo.app.data.repository.RegisterRepository$$Lambda$5;	 Catch:{ Exception -> 0x00b4 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x00b4 }
        r0.execute(r2);	 Catch:{ Exception -> 0x00b4 }
        goto L_0x00c0;	 Catch:{ Exception -> 0x00b4 }
    L_0x0087:
        r5 = r17;	 Catch:{ Exception -> 0x00b4 }
        r0 = r2.errorBody();	 Catch:{ Exception -> 0x00b4 }
        if (r0 == 0) goto L_0x00a4;	 Catch:{ Exception -> 0x00b4 }
    L_0x008f:
        r0 = com.mevo.app.data.network.Rest.getXmlSerializer();	 Catch:{ Exception -> 0x00b4 }
        r3 = com.mevo.app.data.model.response.ErrorResponse.class;	 Catch:{ Exception -> 0x00b4 }
        r2 = r2.errorBody();	 Catch:{ Exception -> 0x00b4 }
        r2 = r2.string();	 Catch:{ Exception -> 0x00b4 }
        r0 = r0.read(r3, r2);	 Catch:{ Exception -> 0x00b4 }
        r0 = (com.mevo.app.data.model.response.ErrorResponse) r0;	 Catch:{ Exception -> 0x00b4 }
        goto L_0x00a5;	 Catch:{ Exception -> 0x00b4 }
    L_0x00a4:
        r0 = 0;	 Catch:{ Exception -> 0x00b4 }
    L_0x00a5:
        r2 = com.inverce.mod.core.IM.onUi();	 Catch:{ Exception -> 0x00b4 }
        r3 = new com.mevo.app.data.repository.RegisterRepository$$Lambda$6;	 Catch:{ Exception -> 0x00b4 }
        r3.<init>(r1, r0);	 Catch:{ Exception -> 0x00b4 }
        r2.execute(r3);	 Catch:{ Exception -> 0x00b4 }
        goto L_0x00c0;
    L_0x00b2:
        r5 = r17;
    L_0x00b4:
        r0 = com.inverce.mod.core.IM.onUi();
        r2 = new com.mevo.app.data.repository.RegisterRepository$$Lambda$7;
        r2.<init>(r1);
        r0.execute(r2);
    L_0x00c0:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.repository.RegisterRepository.lambda$register$57$RegisterRepository(com.mevo.app.data.model.RegistrationData, com.mevo.app.data.repository.RegisterRepository$RegisterListener):void");
    }

    private void acceptAgreementsAndSaveFailedSync(java.util.List<java.lang.Integer> r4, java.lang.String r5, java.lang.String r6) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r4 = r4.iterator();	 Catch:{ Exception -> 0x0023 }
    L_0x0009:
        r1 = r4.hasNext();	 Catch:{ Exception -> 0x0023 }
        if (r1 == 0) goto L_0x0023;	 Catch:{ Exception -> 0x0023 }
    L_0x000f:
        r1 = r4.next();	 Catch:{ Exception -> 0x0023 }
        r1 = (java.lang.Integer) r1;	 Catch:{ Exception -> 0x0023 }
        r2 = r1.intValue();	 Catch:{ Exception -> 0x0023 }
        r2 = r3.acceptAgreementSync(r2, r5);	 Catch:{ Exception -> 0x0023 }
        if (r2 != 0) goto L_0x0009;	 Catch:{ Exception -> 0x0023 }
    L_0x001f:
        r0.add(r1);	 Catch:{ Exception -> 0x0023 }
        goto L_0x0009;
    L_0x0023:
        com.mevo.app.data.shared_prefs.SharedPrefs.saveFailedRequestsForConsentedAgreements(r6, r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.repository.RegisterRepository.acceptAgreementsAndSaveFailedSync(java.util.List, java.lang.String, java.lang.String):void");
    }

    private boolean acceptAgreementSync(int r3, java.lang.String r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = com.mevo.app.data.network.Rest.getApiNextbike();	 Catch:{ Exception -> 0x002a }
        r1 = com.mevo.app.Cfg.get();	 Catch:{ Exception -> 0x002a }
        r1 = r1.apikeyNextbike();	 Catch:{ Exception -> 0x002a }
        r3 = r0.acceptAgreement(r1, r4, r3);	 Catch:{ Exception -> 0x002a }
        r3 = r3.execute();	 Catch:{ Exception -> 0x002a }
        r3 = r3.body();	 Catch:{ Exception -> 0x002a }
        r3 = (com.mevo.app.data.model.response.ResponseAgreementAccept) r3;	 Catch:{ Exception -> 0x002a }
        if (r3 == 0) goto L_0x002a;	 Catch:{ Exception -> 0x002a }
    L_0x001c:
        r4 = r3.agreementAccept;	 Catch:{ Exception -> 0x002a }
        if (r4 == 0) goto L_0x002a;	 Catch:{ Exception -> 0x002a }
    L_0x0020:
        r3 = r3.agreementAccept;	 Catch:{ Exception -> 0x002a }
        r3 = r3.isStatus();	 Catch:{ Exception -> 0x002a }
        if (r3 != 0) goto L_0x002a;
    L_0x0028:
        r3 = 1;
        return r3;
    L_0x002a:
        r3 = 0;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.repository.RegisterRepository.acceptAgreementSync(int, java.lang.String):boolean");
    }

    private void trackUpdateUserWithAgreements(RegistrationData registrationData) {
        UserDetails userDetails = new UserDetails();
        userDetails.setPhoneNumber(registrationData.phoneNumber);
        userDetails.setEmail(registrationData.email);
        userDetails.setFirstname(registrationData.firstname);
        userDetails.setLastname(registrationData.lastname);
        userDetails.setAddress(registrationData.streetAdress);
        userDetails.setZipCode(registrationData.zipCode);
        userDetails.setCity(registrationData.city);
        userDetails.setLanguage(registrationData.languageCode);
        userDetails.setCountry(registrationData.languageCode);
        userDetails.setHasNewsletter(Utils.boolToInt(registrationData.getNewsletterAgreement()));
        userDetails.setPesel(registrationData.pesel);
        UserTracker.get().updateUserWithAgreements(userDetails, false, (Boolean[]) Stream.of(registrationData.agreements).map(RegisterRepository$$Lambda$1.$instance).toList().toArray(new Boolean[registrationData.agreements.size()]));
    }

    public void resendAgreementsIfShould() {
        IM.onBg().execute(new RegisterRepository$$Lambda$2(this));
    }

    final /* synthetic */ void lambda$resendAgreementsIfShould$58$RegisterRepository() {
        User user = UserManager.getUser();
        List failedRequestsConsentedAgreementIds = SharedPrefs.getFailedRequestsConsentedAgreementIds(user.getPhoneNumber());
        if (!failedRequestsConsentedAgreementIds.isEmpty()) {
            SharedPrefs.clearFailedRequestsConsentedAgreementIds(user.getPhoneNumber());
            acceptAgreementsAndSaveFailedSync(failedRequestsConsentedAgreementIds, user.getLoginkey(), user.getPhoneNumber());
        }
    }
}
