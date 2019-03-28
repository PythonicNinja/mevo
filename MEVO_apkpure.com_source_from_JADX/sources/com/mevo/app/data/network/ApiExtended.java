package com.mevo.app.data.network;

import com.mevo.app.data.model.MevoErrorReport;
import com.mevo.app.data.model.VersionCheck;
import com.mevo.app.data.model.api_extended.response.ResponseTrackUser;
import com.mevo.app.data.model.request.RequestArgsRentalsDistances;
import com.mevo.app.data.model.response.ResponseGoogleRoute;
import com.mevo.app.data.model.response.ResponseRentalBasedPositions;
import com.mevo.app.data.model.response.ResponseRentalsDistances;
import com.mevo.app.data.model.response.ResponseReportResult;
import com.mevo.app.data.model.response.ResponseShouldRegisterForPushMessages;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiExtended {
    @FormUrlEncoded
    @POST("v1/confirm-user")
    Call<ResponseTrackUser> confirmUser(@Field("system_id") String str, @Field("phone") String str2);

    @GET("v1/version-check")
    Call<VersionCheck> getCurrentAppVersion();

    @POST("https://noweapi.nextbike.pl/api/rental/distance")
    Call<ResponseRentalsDistances> getDistanceForRentals(@Body RequestArgsRentalsDistances requestArgsRentalsDistances);

    @GET("https://mapaciepla.rowermevo.pl/api/rental/positions.json")
    Call<ResponseRentalBasedPositions> getRentalBasedPositions(@Query("shared_key") String str, @Query("rental_id") String str2);

    @GET("https://maps.googleapis.com/maps/api/directions/json")
    Call<ResponseGoogleRoute> getRoute(@Query("key") String str, @Query("origin") String str2, @Query("destination") String str3, @Query("mode") String str4);

    @POST("https://raporty.rowermevo.pl/api/callbacks/issue")
    Call<ResponseReportResult> sendErrorReport(@Query("shared_key") String str, @Body MevoErrorReport mevoErrorReport);

    @GET("v1/send-push-settings")
    Call<ResponseShouldRegisterForPushMessages> shouldRegisterForPushMessages();

    @FormUrlEncoded
    @POST("v1/update-user")
    Call<ResponseTrackUser> updateUser(@Field("system_id") String str, @Field("phone") String str2, @Field("firstname") String str3, @Field("lastname") String str4, @Field("email") String str5, @Field("street") String str6, @Field("post_code") String str7, @Field("newsletter") boolean z, @Field("pesel") String str8, @Field("status") int i, @Field("consent_1") Boolean bool, @Field("consent_2") Boolean bool2, @Field("consent_3") Boolean bool3, @Field("consent_4") Boolean bool4, @Field("consent_5") Boolean bool5, @Field("consent_6") Boolean bool6);
}
