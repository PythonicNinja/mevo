package com.mevo.app.data.model.response;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;

public class ResponseRentalsDistances {
    @SerializedName("data")
    private HashMap<Integer, Double> distances;
    @SerializedName("valid")
    private boolean valid;

    public ResponseRentalsDistances(boolean z, HashMap<Integer, Double> hashMap) {
        this.valid = z;
        this.distances = hashMap;
    }

    public boolean isValid() {
        return this.valid;
    }

    public ResponseRentalsDistances setValid(boolean z) {
        this.valid = z;
        return this;
    }

    public HashMap<Integer, Double> getDistances() {
        return this.distances;
    }

    public ResponseRentalsDistances setDistances(HashMap<Integer, Double> hashMap) {
        this.distances = hashMap;
        return this;
    }
}
