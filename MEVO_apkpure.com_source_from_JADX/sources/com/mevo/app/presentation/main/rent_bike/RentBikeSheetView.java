package com.mevo.app.presentation.main.rent_bike;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.Log;
import com.mevo.app.C0434R;
import com.mevo.app.presentation.custom_views.CustomInput;
import com.mevo.app.presentation.main.MainActivityInterface;
import com.mevo.app.tools.NetTools.CallsToken;
import com.mevo.app.tools.Validator;
import com.mevo.app.tools.bike_tools.RentBikeRepository;
import me.grantland.widget.AutofitTextView;

public class RentBikeSheetView extends LinearLayout {
    private static final String TAG = "RentBikeSheetView";
    private MainActivityInterface activityInterface;
    private EditText bikeNumberEdit;
    private CustomInput bikeNumberInput;
    private CallsToken callsToken;
    private AutofitTextView header;
    private RentBikeSheetViewListener listener;
    private RentBikeRepository rentBikeRepository;
    private Button rentButton;

    public interface RentBikeSheetViewListener {
        void onHeaderClick();
    }

    public RentBikeSheetView(Context context) {
        super(context);
        init(context);
    }

    public RentBikeSheetView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public RentBikeSheetView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    @RequiresApi(api = 21)
    public RentBikeSheetView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.activityInterface = (MainActivityInterface) IM.activity();
        Log.m61v(TAG, "Attached");
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.activityInterface = null;
        Log.m61v(TAG, "Dettached");
    }

    private void init(Context context) {
        inflate(context, C0434R.layout.view_rent_bike, this);
        this.callsToken = new CallsToken();
        this.rentBikeRepository = new RentBikeRepository(this.callsToken, false);
        findViews();
        setListeners();
        setValidator();
    }

    private void findViews() {
        this.bikeNumberInput = (CustomInput) findViewById(C0434R.id.view_rent_bike_bike_number_input);
        this.bikeNumberEdit = (EditText) findViewById(C0434R.id.view_rent_bike_bike_number_edit);
        this.rentButton = (Button) findViewById(C0434R.id.view_rent_bike_rent_button);
        this.header = (AutofitTextView) findViewById(C0434R.id.view_basic_header_header_text);
    }

    private void setListeners() {
        this.rentButton.setOnClickListener(new RentBikeSheetView$$Lambda$0(this));
        this.header.setOnClickListener(new RentBikeSheetView$$Lambda$1(this));
    }

    final /* synthetic */ void lambda$setListeners$231$RentBikeSheetView(View view) {
        rentBike();
    }

    final /* synthetic */ void lambda$setListeners$232$RentBikeSheetView(View view) {
        if (this.listener != null) {
            this.listener.onHeaderClick();
        }
    }

    private void setValidator() {
        this.bikeNumberInput.setValidator(null, new RentBikeSheetView$$Lambda$2(this));
    }

    final /* synthetic */ Pair lambda$setValidator$233$RentBikeSheetView(String str) {
        if (Validator.isBikeNumberValid(this.bikeNumberEdit.getText().toString()) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), null);
    }

    private boolean checkBikeNumberValid() {
        this.bikeNumberInput.checkValid();
        return this.bikeNumberInput.isValid().booleanValue();
    }

    private void rentBike() {
        if (checkBikeNumberValid()) {
            if (this.activityInterface != null) {
                String obj = this.bikeNumberEdit.getText().toString();
                this.activityInterface.setProgressViewVisibility(true);
                this.rentBikeRepository.tryRentBike(obj, new RentBikeSheetView$$Lambda$3(this));
            }
        }
    }

    final /* synthetic */ void lambda$rentBike$234$RentBikeSheetView(boolean z) {
        if (this.activityInterface != null) {
            this.activityInterface.setProgressViewVisibility(false);
            if (z) {
                this.activityInterface.goToHome();
            }
        }
    }

    public void setListener(RentBikeSheetViewListener rentBikeSheetViewListener) {
        this.listener = rentBikeSheetViewListener;
    }
}
