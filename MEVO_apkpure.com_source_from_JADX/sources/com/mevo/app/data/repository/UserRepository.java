package com.mevo.app.data.repository;

import com.mevo.app.Cfg;
import com.mevo.app.data.database.adapters.EncodedBatteryTimestamps;
import com.mevo.app.data.model.response.ResponseCustomerRfid;
import com.mevo.app.data.model.response.ResponseUpdateUser;
import com.mevo.app.data.model.response.ResponseUserDetails;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseBatteryReport;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;
import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.UserManager;
import java.util.ArrayList;
import java.util.Calendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    /* renamed from: com.mevo.app.data.repository.UserRepository$4 */
    class C08174 implements Callback<ResponseUserDetails> {

        /* renamed from: com.mevo.app.data.repository.UserRepository$4$1 */
        class C08161 implements Callback<ResponseUpdateUser> {
            public void onFailure(Call<ResponseUpdateUser> call, Throwable th) {
            }

            public void onResponse(Call<ResponseUpdateUser> call, Response<ResponseUpdateUser> response) {
            }

            C08161() {
            }
        }

        public void onFailure(Call<ResponseUserDetails> call, Throwable th) {
        }

        C08174() {
        }

        public void onResponse(Call<ResponseUserDetails> call, Response<ResponseUserDetails> response) {
            if (response.isSuccessful() != null && response.body() != null && ((ResponseUserDetails) response.body()).userDetails != null) {
                call = EncodedBatteryTimestamps.getModelValue(((ResponseUserDetails) response.body()).userDetails.getBatteryTimestamps());
                if (call == null) {
                    call = new ArrayList();
                }
                if (call.size() < 2) {
                    call.add(Long.valueOf(System.currentTimeMillis()));
                } else {
                    call.remove(null);
                    call.add(Long.valueOf(System.currentTimeMillis()));
                }
                Rest.getApiNextbike().updateUserReports(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), EncodedBatteryTimestamps.getStringValue(call)).enqueue(new C08161());
            }
        }
    }

    public void sendCityCardNumber(final String str, final SimpleResponseListener simpleResponseListener) {
        int parseInt;
        try {
            parseInt = Integer.parseInt(str);
        } catch (Throwable e) {
            Log.ex(e);
            parseInt = 0;
        }
        if (parseInt == 0) {
            simpleResponseListener.onResponse(false, null);
        } else {
            Rest.getApiNextbike().setCustomerRfid(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), parseInt).enqueue(new Callback<ResponseCustomerRfid>() {
                public void onResponse(Call<ResponseCustomerRfid> call, Response<ResponseCustomerRfid> response) {
                    if (response.isSuccessful() == null || response.body() == null || ((ResponseCustomerRfid) response.body()).user.getCustomerCardIds() == null || ((ResponseCustomerRfid) response.body()).user.getCustomerCardIds().contains(str) == null) {
                        simpleResponseListener.onResponse(null, null);
                    } else {
                        simpleResponseListener.onResponse(true, null);
                    }
                }

                public void onFailure(Call<ResponseCustomerRfid> call, Throwable th) {
                    simpleResponseListener.onResponse(null, null);
                }
            });
        }
    }

    public void getCardNumber(final ResponseListener<String> responseListener) {
        Rest.getApiNextbike().getUserDetails(Cfg.get().apikeyNextbike(), UserManager.getLoginKey()).enqueue(new Callback<ResponseUserDetails>() {
            public void onResponse(Call<ResponseUserDetails> call, Response<ResponseUserDetails> response) {
                if (response.isSuccessful() == null || response.body() == null || ((ResponseUserDetails) response.body()).userDetails == null || ((ResponseUserDetails) response.body()).userDetails.getRfidUid() == null) {
                    responseListener.onResponse(null, null, null);
                } else {
                    responseListener.onResponse(((ResponseUserDetails) response.body()).userDetails.getMultipleRfidUid(), true, null);
                }
            }

            public void onFailure(Call<ResponseUserDetails> call, Throwable th) {
                responseListener.onResponse(null, false, null);
            }
        });
    }

    public void removeCardNumber(SimpleResponseListener simpleResponseListener) {
        sendCityCardNumber("", simpleResponseListener);
    }

    public void canUserSendBatteryReport(final ResponseBatteryReport responseBatteryReport) {
        Rest.getApiNextbike().getUserDetails(Cfg.get().apikeyNextbike(), UserManager.getLoginKey()).enqueue(new Callback<ResponseUserDetails>() {
            public void onResponse(Call<ResponseUserDetails> call, Response<ResponseUserDetails> response) {
                if (response.isSuccessful() == null || response.body() == null || ((ResponseUserDetails) response.body()).userDetails == null) {
                    responseBatteryReport.onResponse(Boolean.valueOf(false), Boolean.valueOf(false), null);
                    return;
                }
                call = EncodedBatteryTimestamps.getModelValue(((ResponseUserDetails) response.body()).userDetails.getBatteryTimestamps());
                if (((ResponseUserDetails) response.body()).userDetails.getBatteryTimestamps() != null) {
                    if (call.size() >= 2) {
                        response = Calendar.getInstance();
                        Calendar instance = Calendar.getInstance();
                        response.setTimeInMillis(((Long) call.get(0)).longValue());
                        instance.setTimeInMillis(System.currentTimeMillis());
                        if (response.get(6) < instance.get(6)) {
                            responseBatteryReport.onResponse(Boolean.valueOf(true), Boolean.valueOf(true), null);
                            return;
                        } else {
                            responseBatteryReport.onResponse(Boolean.valueOf(true), Boolean.valueOf(false), null);
                            return;
                        }
                    }
                }
                responseBatteryReport.onResponse(Boolean.valueOf(true), Boolean.valueOf(true), null);
            }

            public void onFailure(Call<ResponseUserDetails> call, Throwable th) {
                responseBatteryReport.onResponse(Boolean.valueOf(false), Boolean.valueOf(false), null);
            }
        });
    }

    public void updateBatteryReports() {
        Rest.getApiNextbike().getUserDetails(Cfg.get().apikeyNextbike(), UserManager.getLoginKey()).enqueue(new C08174());
    }
}
