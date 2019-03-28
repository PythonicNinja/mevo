package com.mevo.app.presentation.register.register_step_two;

import com.mevo.app.data.model.response.ErrorResponse;
import com.mevo.app.data.repository.RegisterRepository.RegisterListener;

final /* synthetic */ class RegisterStepTwo$$Lambda$3 implements RegisterListener {
    private final RegisterStepTwo arg$1;

    RegisterStepTwo$$Lambda$3(RegisterStepTwo registerStepTwo) {
        this.arg$1 = registerStepTwo;
    }

    public void onResponse(boolean z, ErrorResponse errorResponse) {
        this.arg$1.lambda$register$328$RegisterStepTwo(z, errorResponse);
    }
}
