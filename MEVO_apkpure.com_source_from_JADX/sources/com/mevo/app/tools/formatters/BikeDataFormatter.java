package com.mevo.app.tools.formatters;

import android.content.Context;
import android.support.annotation.ColorRes;
import com.mevo.app.C0434R;
import com.mevo.app.constants.BikeTypes.BikeGroup;

public class BikeDataFormatter {
    private String bikeNameElectric;
    private String bikeNameKids4;
    private String bikeNameKids6;
    private String bikeNameRegular;
    private String bikeNameTandem;

    @ColorRes
    public int getColorResForBatteryPercent(int i, @ColorRes int i2) {
        return i >= 20 ? C0434R.color.batteryGreen : i >= 0 ? C0434R.color.batteryRed : i2;
    }

    public BikeDataFormatter(Context context) {
        this.bikeNameRegular = context.getString(C0434R.string.bike_type_standard);
        this.bikeNameTandem = context.getString(C0434R.string.bike_type_tandem);
        this.bikeNameKids4 = context.getString(C0434R.string.bike_type_kids_4);
        this.bikeNameKids6 = context.getString(C0434R.string.bike_type_kids_6);
        this.bikeNameElectric = context.getString(C0434R.string.bike_type_electric);
    }

    public String getBikeNameForBikeType(BikeGroup bikeGroup) {
        switch (bikeGroup) {
            case SMARTBIKE:
                return this.bikeNameElectric;
            case KIDS_4:
                return this.bikeNameKids4;
            case KIDS_6:
                return this.bikeNameKids6;
            case TANDEM:
                return this.bikeNameTandem;
            default:
                return this.bikeNameRegular;
        }
    }
}
