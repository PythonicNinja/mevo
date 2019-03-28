package com.mevo.app.data.network;

import com.mevo.app.data.model.response.ResponseStationsData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiNextbikeMap {
    @GET("maps/nextbike-live.xml")
    Call<ResponseStationsData> getStationsData(@Query("domains") String str, @Query("lat") Double d, @Query("lng") Double d2, @Query("distance") Integer num);
}
