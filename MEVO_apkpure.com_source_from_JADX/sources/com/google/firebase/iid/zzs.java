package com.google.firebase.iid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.io.IOException;

final class zzs implements Continuation<Bundle, String> {
    private final /* synthetic */ zzp zzbe;

    zzs(zzp zzp) {
        this.zzbe = zzp;
    }

    public final /* synthetic */ Object then(@NonNull Task task) throws Exception {
        return zzp.zza((Bundle) task.getResult(IOException.class));
    }
}
