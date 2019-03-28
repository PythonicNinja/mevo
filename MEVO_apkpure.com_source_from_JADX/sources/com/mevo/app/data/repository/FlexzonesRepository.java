package com.mevo.app.data.repository;

import android.support.annotation.NonNull;
import com.mevo.app.Cfg;
import com.mevo.app.data.model.response.ResponseFlexzones;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlexzonesRepository {
    public void getFlexzones(@NonNull final ResponseListener<ResponseFlexzones> responseListener) {
        Rest.getApiNextbike().getFlexzones(Cfg.get().apikeyNextbike(), Cfg.get().domain().getDomainCode()).enqueue(new Callback<ResponseFlexzones>() {
            public void onResponse(Call<ResponseFlexzones> call, Response<ResponseFlexzones> response) {
                ResponseFlexzones responseFlexzones = (ResponseFlexzones) response.body();
                if (response.isSuccessful() == null || responseFlexzones == null) {
                    responseListener.onResponse(null, null, null);
                } else {
                    responseListener.onResponse(responseFlexzones, true, null);
                }
            }

            public void onFailure(Call<ResponseFlexzones> call, Throwable th) {
                responseListener.onResponse(null, false, null);
            }
        });
    }
}
