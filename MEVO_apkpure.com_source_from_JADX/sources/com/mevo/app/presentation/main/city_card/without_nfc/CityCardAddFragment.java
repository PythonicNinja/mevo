package com.mevo.app.presentation.main.city_card.without_nfc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.data.repository.NextbikeApiRepository;
import com.mevo.app.data.repository.UserRepository;
import com.mevo.app.modules.nfc.AccountStorage;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.dialogs.InfoDialog.Builder;
import com.mevo.app.presentation.main.MainFragment;
import com.mevo.app.presentation.main.city_card.CityCardAddScan;
import com.mevo.app.tools.UiTools;

public class CityCardAddFragment extends MainFragment {
    private Button addCardBt;
    private EditText cartNumberEt;
    private Button scanCard;

    public static CityCardAddFragment newInstance() {
        return new CityCardAddFragment();
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_city_card_add, viewGroup, false);
        findViews(layoutInflater);
        this.addCardBt.setOnClickListener(new CityCardAddFragment$$Lambda$0(this));
        this.scanCard.setOnClickListener(new CityCardAddFragment$$Lambda$1(this));
        return layoutInflater;
    }

    final /* synthetic */ void lambda$onCreateView$185$CityCardAddFragment(View view) {
        onAddCardButton();
    }

    final /* synthetic */ void lambda$onCreateView$186$CityCardAddFragment(View view) {
        onScanCardButton();
    }

    private void findViews(View view) {
        this.addCardBt = (Button) view.findViewById(C0434R.id.add_card_button);
        this.cartNumberEt = (EditText) view.findViewById(C0434R.id.cart_number);
        this.scanCard = (Button) view.findViewById(C0434R.id.scan_card_button);
    }

    private void onAddCardButton() {
        UiTools.hideSoftInput(this.addCardBt);
        if (getActivity() != null) {
            if (getActivityInterface() != null) {
                String obj = this.cartNumberEt.getText().toString();
                if (obj.isEmpty()) {
                    TopToast.show(C0434R.string.fill_number, 0, TopToast.DURATION_SHORT);
                } else {
                    getActivityInterface().setProgressViewVisibility(true);
                    new UserRepository().sendCityCardNumber(obj, new CityCardAddFragment$$Lambda$2(this, obj));
                }
            }
        }
    }

    final /* synthetic */ void lambda$onAddCardButton$187$CityCardAddFragment(String str, boolean z, Exception exception) {
        getActivityInterface().setProgressViewVisibility(false);
        if (z) {
            AccountStorage.SetAccount(getActivity(), str);
            navigateToFragment();
            return;
        }
        TopToast.show(C0434R.string.error_ocurred, 0, true);
    }

    void navigateToFragment() {
        IM.onUi().execute(new CityCardAddFragment$$Lambda$3(this));
    }

    final /* synthetic */ void lambda$navigateToFragment$189$CityCardAddFragment() {
        if (this.activityInterface != null) {
            this.activityInterface.setProgressViewVisibility(true);
            new UserRepository().getCardNumber(new CityCardAddFragment$$Lambda$5(this));
        }
    }

    final /* synthetic */ void lambda$null$188$CityCardAddFragment(String str, boolean z, Exception exception) {
        this.activityInterface.setProgressViewVisibility(false);
        if (z && !str.equals("")) {
            this.activityInterface.popFragment();
            this.activityInterface.changeFragment(CardFragment.newInstance(str), true);
        }
    }

    private void onScanCardButton() {
        if (this.activityInterface != null) {
            this.activityInterface.setProgressViewVisibility(true);
            new NextbikeApiRepository().hasActiveRentals(new CityCardAddFragment$$Lambda$4(this));
        }
    }

    final /* synthetic */ void lambda$onScanCardButton$190$CityCardAddFragment(Boolean bool, boolean z, Exception exception) {
        this.activityInterface.setProgressViewVisibility(false);
        if (!z) {
            TopToast.show(C0434R.string.error_ocurred, 0, true);
        } else if (bool.booleanValue() != null) {
            this.activityInterface.changeFragment(CityCardAddScan.newInstance(), true);
        } else {
            new Builder().setText((int) true).setNeutralButton((int) true, null).build().show();
        }
    }
}
