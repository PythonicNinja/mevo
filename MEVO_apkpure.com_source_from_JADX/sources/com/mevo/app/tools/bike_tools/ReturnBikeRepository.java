package com.mevo.app.tools.bike_tools;

import android.location.Location;
import android.support.annotation.StringRes;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.data.model.Bike$$CC;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.model.response.ResponseBikeState;
import com.mevo.app.data.model.response.ResponseHistory;
import com.mevo.app.data.model.response.ResponseReturnBike;
import com.mevo.app.data.network.Rest;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.dialogs.ReturnConfirmDialog;
import com.mevo.app.presentation.dialogs.ReturnSuccessDialog;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.NetTools;
import com.mevo.app.tools.NetTools.CallsToken;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.Utils;
import com.mevo.app.tools.Validator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReturnBikeRepository {
    private static final String TAG = "ReturnBikeRepository";
    private String bikeNumber;
    private CallsToken callsToken;
    private int placeId;
    private ReturnCallback returnCallback;

    public interface ReturnCallback {
        void onReturn(boolean z, RentalItem rentalItem);
    }

    /* renamed from: com.mevo.app.tools.bike_tools.ReturnBikeRepository$1 */
    class C08481 implements Callback<ResponseBikeState> {
        C08481() {
        }

        public void onResponse(Call<ResponseBikeState> call, Response<ResponseBikeState> response) {
            if (response.isSuccessful() == null || response.body() == null) {
                ReturnBikeRepository.this.onReturnFail(C0434R.string.return_bike_error);
            } else if (Bike$$CC.canBikeBeReturned$$STATIC$$(((ResponseBikeState) response.body()).bike) != null) {
                ReturnBikeRepository.this.returnBike();
            } else {
                ReturnBikeRepository.this.onReturnFail(C0434R.string.bike_cannot_be_returned_trough_app);
            }
        }

        public void onFailure(Call<ResponseBikeState> call, Throwable th) {
            if (call.isCanceled() == null) {
                ReturnBikeRepository.this.onReturnFail(C0434R.string.error_ocurred);
            }
        }
    }

    /* renamed from: com.mevo.app.tools.bike_tools.ReturnBikeRepository$2 */
    class C08492 implements Callback<ResponseReturnBike> {
        C08492() {
        }

        public void onResponse(Call<ResponseReturnBike> call, Response<ResponseReturnBike> response) {
            if (response.body() == null || ((ResponseReturnBike) response.body()).rental == null) {
                ReturnBikeRepository.this.onReturnFail(C0434R.string.error_return_bike_in_rack);
            } else {
                ReturnBikeRepository.this.getRentalItemFullData(((ResponseReturnBike) response.body()).rental);
            }
        }

        public void onFailure(Call<ResponseReturnBike> call, Throwable th) {
            if (call.isCanceled() == null) {
                ReturnBikeRepository.this.onReturnFail(C0434R.string.error_ocurred);
            }
        }
    }

    public ReturnBikeRepository(CallsToken callsToken) {
        this.callsToken = callsToken;
    }

    public void tryReturnBikeOnStation(String str, int i, String str2, ReturnCallback returnCallback) {
        if (this.returnCallback != null) {
            Log.m90e(TAG, "Bike is being returned");
            return;
        }
        if (!Validator.isBikeNumberValid(str)) {
            onReturnFail(C0434R.string.error_wrong_bike_number);
        }
        this.bikeNumber = str;
        this.returnCallback = returnCallback;
        ReturnConfirmDialog.newInstance(str, str2, new ReturnBikeRepository$$Lambda$0(this, i)).show();
    }

    final /* synthetic */ void lambda$tryReturnBikeOnStation$333$ReturnBikeRepository(int i, boolean z) {
        if (z) {
            this.placeId = i;
            checkBikeState();
            return;
        }
        notifyListenerAndClearData(0, false);
    }

    private void checkBikeState() {
        NetTools.saveCall(this.callsToken, Rest.getApiNextbike().getBikeState(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), this.bikeNumber)).enqueue(new C08481());
    }

    private void returnBike() {
        NetTools.saveCall(this.callsToken, Rest.getApiNextbike().returnBikeAtStation(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), this.bikeNumber, this.placeId, Utils.boolToInt(true))).enqueue(new C08492());
    }

    public void returnBikeOutsideStation(String str, String str2, Location location, ReturnCallback returnCallback) {
        if (this.returnCallback != null) {
            Log.m90e(TAG, "Bike is being returned");
            return;
        }
        if (Validator.isBikeNumberValid(str) == null) {
            onReturnFail(C0434R.string.error_wrong_bike_number);
        }
        this.bikeNumber = str;
        this.returnCallback = returnCallback;
        ReturnConfirmDialog.newInstance(str, str2, new ReturnBikeRepository$$Lambda$1(this)).show();
    }

    final /* synthetic */ void lambda$returnBikeOutsideStation$334$ReturnBikeRepository(boolean z) {
        if (z) {
            this.placeId = this.placeId;
            checkBikeState();
            return;
        }
        notifyListenerAndClearData(false, null);
    }

    private void getRentalItemFullData(final RentalItem rentalItem) {
        NetTools.saveCall(this.callsToken, Rest.getApiNextbike().fetchHistoryNoCache(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), Integer.valueOf(1), Integer.valueOf(0))).enqueue(new Callback<ResponseHistory>() {
            public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) {
                if (response.body() == null || ((ResponseHistory) response.body()).accountHistory == null || ((ResponseHistory) response.body()).accountHistory.getRentalItems().isEmpty() != null || ((RentalItem) ((ResponseHistory) response.body()).accountHistory.getRentalItems().get(0)).id != rentalItem.id) {
                    ReturnBikeRepository.this.onReturnSuccess(null);
                    return;
                }
                ReturnBikeRepository.this.onReturnSuccess((RentalItem) ((ResponseHistory) response.body()).accountHistory.getRentalItems().get(0));
            }

            public void onFailure(Call<ResponseHistory> call, Throwable th) {
                ReturnBikeRepository.this.onReturnFail(C0434R.string.error_ocurred);
            }
        });
    }

    private void onReturnSuccess(RentalItem rentalItem) {
        ReturnSuccessDialog.newInstance(rentalItem != null ? rentalItem.bikeNumber : null).show();
        notifyListenerAndClearData(true, rentalItem);
    }

    private void onReturnFail(@StringRes int i) {
        TopToast.show(i, 0, TopToast.DURATION_LONG);
        notifyListenerAndClearData(false, 0);
    }

    private void notifyListenerAndClearData(boolean z, RentalItem rentalItem) {
        this.returnCallback.onReturn(z, rentalItem);
        this.returnCallback = false;
    }
}
