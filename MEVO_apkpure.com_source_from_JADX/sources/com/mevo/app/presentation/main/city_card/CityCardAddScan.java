package com.mevo.app.presentation.main.city_card;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.Ui;
import com.mevo.app.C0434R;
import com.mevo.app.data.repository.NextbikeApiRepository;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.dialogs.InfoDialog.Builder;
import com.mevo.app.presentation.main.MainFragment;
import com.mevo.app.presentation.main.city_card.without_nfc.CardFragment;

public class CityCardAddScan extends MainFragment {
    private TextView numberTxt;
    int secondLeft = 30;
    @Nullable
    CountDownTimer ticker;

    final /* bridge */ /* synthetic */ void bridge$lambda$0$CityCardAddScan() {
        bindData();
    }

    public static CityCardAddScan newInstance() {
        return new CityCardAddScan();
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_add_proximity_card_step_two, viewGroup, false);
        findViews(layoutInflater);
        startTimer();
        return layoutInflater;
    }

    void startTimer() {
        this.secondLeft = Math.max(this.secondLeft, 1);
        this.ticker = new CountDownTimer((long) (this.secondLeft * 1000), 100) {
            public void onTick(long j) {
                CityCardAddScan.this.secondLeft = ((int) (j / 1000)) + 1;
                CityCardAddScan.this.bindData();
            }

            public void onFinish() {
                if (CityCardAddScan.this.activityInterface != null) {
                    CityCardAddScan.this.activityInterface.setProgressViewVisibility(true);
                }
                CityCardAddScan.this.hasCardAdded();
            }
        };
        this.ticker.start();
    }

    public void onStart() {
        super.onStart();
        if (getActivity() != null && getActivity().getWindow() != null) {
            getActivity().getWindow().addFlags(128);
        }
    }

    public void onStop() {
        super.onStop();
        if (getActivity() != null && getActivity().getWindow() != null) {
            getActivity().getWindow().clearFlags(128);
        }
    }

    public void onPause() {
        super.onPause();
        if (this.ticker != null) {
            this.ticker.cancel();
            this.ticker = null;
        }
    }

    private void hasCardAdded() {
        if (this.activityInterface != null) {
            this.activityInterface.setProgressViewVisibility(true);
            new NextbikeApiRepository().getUserCardRfidUid(new CityCardAddScan$$Lambda$0(this));
        }
    }

    final /* synthetic */ void lambda$hasCardAdded$179$CityCardAddScan(String str, boolean z, Exception exception) {
        this.activityInterface.setProgressViewVisibility(false);
        if (!z) {
            showErrorDialog(z);
        } else if (str == null || str.equals("") != null) {
            showErrorDialog(z);
        } else {
            this.activityInterface.changeFragment(CardFragment.newInstance(str), false);
            TopToast.show(C0434R.string.card_added, true, TopToast.DURATION_SHORT);
        }
    }

    private void findViews(View view) {
        this.numberTxt = (TextView) view.findViewById(C0434R.id.add_proximity_card_number);
    }

    private void bindData() {
        if (Ui.isUiThread()) {
            this.numberTxt.setText(String.valueOf(this.secondLeft));
        } else {
            IM.onUi().execute(new CityCardAddScan$$Lambda$1(this));
        }
    }

    private void showErrorDialog(boolean z) {
        new Builder().setText(z ? true : true).setNeutralButton((int) C0434R.string.close, new CityCardAddScan$$Lambda$2(this)).build().show();
    }

    final /* synthetic */ void lambda$showErrorDialog$180$CityCardAddScan(View view) {
        this.activityInterface.popFragment();
    }
}
