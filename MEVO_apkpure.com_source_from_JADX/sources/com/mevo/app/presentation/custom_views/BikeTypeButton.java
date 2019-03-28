package com.mevo.app.presentation.custom_views;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mevo.app.C0434R;

public class BikeTypeButton extends RelativeLayout implements OnClickListener {
    private ImageView bikeTypeImage;
    private TextView bikeTypeText;
    private CheckBox checkBox;
    private CheckedChangedListener checkedChangedListener;
    private View clickInterceptor;
    private View container;

    public interface CheckedChangedListener {
        void onCheckedChanged(View view, boolean z);
    }

    public BikeTypeButton(Context context) {
        super(context);
        init(context, null, -1, -1);
    }

    public BikeTypeButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, -1, -1);
    }

    public BikeTypeButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, -1);
    }

    @RequiresApi(api = 21)
    public BikeTypeButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, C0434R.layout.button_bike_type, this);
        findViews();
        setListeners();
        context = context.obtainStyledAttributes(attributeSet, C0434R.styleable.BikeTypeButton, 0, 0);
        try {
            this.bikeTypeImage.setImageResource(context.getResourceId(1, C0434R.drawable.placeholder_image));
            this.container.setBackgroundColor(context.getColor(0, ResourcesCompat.getColor(getResources(), C0434R.color.transparent, null)));
            setBikeTypeText(context.getString(2));
        } finally {
            context.recycle();
        }
    }

    private void findViews() {
        this.checkBox = (CheckBox) findViewById(C0434R.id.button_bike_type_check_box);
        this.bikeTypeImage = (ImageView) findViewById(C0434R.id.button_bike_type_image);
        this.bikeTypeText = (TextView) findViewById(C0434R.id.button_bike_type_text);
        this.container = findViewById(C0434R.id.button_bike_type_rl);
        this.clickInterceptor = findViewById(C0434R.id.button_bike_type_click_interceptor);
    }

    private void setListeners() {
        this.clickInterceptor.setOnClickListener(this);
    }

    public void setCheckedChangedListener(CheckedChangedListener checkedChangedListener) {
        this.checkedChangedListener = checkedChangedListener;
    }

    public void setBikeTypeText(String str) {
        if (str != null) {
            this.bikeTypeText.setText(str);
        }
    }

    public void changeChecked() {
        if (getChecked()) {
            this.checkBox.setChecked(false);
        } else {
            this.checkBox.setChecked(true);
        }
        if (this.checkedChangedListener != null) {
            this.checkedChangedListener.onCheckedChanged(this, getChecked());
        }
    }

    public boolean getChecked() {
        return this.checkBox.isChecked();
    }

    public void onClick(View view) {
        changeChecked();
    }
}
