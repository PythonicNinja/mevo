package com.mevo.app.presentation.main.voucher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.mevo.app.C0434R;
import com.mevo.app.data.model.event.EventDataSubscriptionTimeChange;
import com.mevo.app.data.repository.NextbikeApiRepository;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.main.MainFragment;
import org.greenrobot.eventbus.EventBus;

public class VoucherFragment extends MainFragment {
    private EditText inputCode;
    private NextbikeApiRepository nextbikeApiRepository;
    private Button purchaseVoucher;

    public static VoucherFragment newInstance() {
        return new VoucherFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C0434R.layout.fragment_realise_voucher, viewGroup, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        findViews(view);
        setListeners();
        this.nextbikeApiRepository = new NextbikeApiRepository();
    }

    public void findViews(View view) {
        this.inputCode = (EditText) view.findViewById(C0434R.id.fragment_input_voucher_code);
        this.purchaseVoucher = (Button) view.findViewById(C0434R.id.fragment_purchase_voucher_button);
    }

    private void setListeners() {
        this.purchaseVoucher.setOnClickListener(new VoucherFragment$$Lambda$0(this));
    }

    final /* synthetic */ void lambda$setListeners$303$VoucherFragment(View view) {
        getVoucher();
    }

    private void getVoucher() {
        if (getActivityInterface() != null) {
            getActivityInterface().setProgressViewVisibility(true);
            this.nextbikeApiRepository.purchaseVoucher(this.inputCode.getText().toString(), new VoucherFragment$$Lambda$1(this));
        }
    }

    final /* synthetic */ void lambda$getVoucher$304$VoucherFragment(boolean z, Exception exception) {
        if (getActivityInterface() != null) {
            getActivityInterface().setProgressViewVisibility(false);
        }
        if (z) {
            TopToast.show(true, 1, TopToast.DURATION_SHORT);
        } else {
            TopToast.show(true, 0, TopToast.DURATION_SHORT);
        }
        EventBus.getDefault().post(new EventDataSubscriptionTimeChange());
        this.inputCode.getText().clear();
    }
}
