package com.mevo.app.data.repository;

import com.mevo.app.data.model.RegistrationData;
import com.mevo.app.data.repository.RegisterRepository.RegisterListener;

final /* synthetic */ class RegisterRepository$$Lambda$0 implements Runnable {
    private final RegisterRepository arg$1;
    private final RegistrationData arg$2;
    private final RegisterListener arg$3;

    RegisterRepository$$Lambda$0(RegisterRepository registerRepository, RegistrationData registrationData, RegisterListener registerListener) {
        this.arg$1 = registerRepository;
        this.arg$2 = registrationData;
        this.arg$3 = registerListener;
    }

    public void run() {
        this.arg$1.lambda$register$57$RegisterRepository(this.arg$2, this.arg$3);
    }
}
