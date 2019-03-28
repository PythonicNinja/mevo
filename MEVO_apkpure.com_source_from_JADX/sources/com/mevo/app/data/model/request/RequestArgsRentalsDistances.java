package com.mevo.app.data.model.request;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RequestArgsRentalsDistances {
    @SerializedName("key")
    public final String apiKey;
    @SerializedName("rentals")
    public final List<Integer> rentalIds;

    public RequestArgsRentalsDistances(String str, List<Integer> list) {
        this.apiKey = str;
        this.rentalIds = list;
    }
}
