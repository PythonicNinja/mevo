package com.mevo.app.data.model.response;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ResponseRentalBasedPositions {
    @SerializedName("data")
    public ArrayList<ResponseLatLon> data;
    @SerializedName("valid")
    public String valid;
}
