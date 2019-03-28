package com.mevo.app.data.network;

import com.mevo.app.data.model.response.ResponseBikeStateJson;
import com.mevo.app.data.model.response.ResponseReportProblem;
import com.mevo.app.data.model.response.ResponseStationDetails;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiNextbikeJson {
    @FormUrlEncoded
    @POST("api/getBikeState.json")
    Call<ResponseBikeStateJson> getBikeState(@Field("apikey") String str, @Field("loginkey") String str2, @Field("bike") String str3);

    @GET("api/getPlaceDetails.json")
    Call<ResponseStationDetails> getStationDetails(@Query("apikey") String str, @Query("loginkey") String str2, @Query("place") int i);

    @FormUrlEncoded
    @POST("api/sendErrorReport.json")
    Call<ResponseReportProblem> sendBatteryReport(@Field("apikey") String str, @Field("loginkey") String str2, @Field("maincategory") String str3, @Field("reports") String str4, @Field("number") Integer num, @Field("date") String str5);
}
