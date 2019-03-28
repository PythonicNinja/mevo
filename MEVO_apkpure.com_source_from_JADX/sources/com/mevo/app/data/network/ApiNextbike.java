package com.mevo.app.data.network;

import com.mevo.app.data.model.response.ResponseActiveBookings;
import com.mevo.app.data.model.response.ResponseAgreement;
import com.mevo.app.data.model.response.ResponseAgreementAccept;
import com.mevo.app.data.model.response.ResponseAgreementForDomain;
import com.mevo.app.data.model.response.ResponseBikeState;
import com.mevo.app.data.model.response.ResponseBookBike;
import com.mevo.app.data.model.response.ResponseCancelBooking;
import com.mevo.app.data.model.response.ResponseCustomerRfid;
import com.mevo.app.data.model.response.ResponseFlexzones;
import com.mevo.app.data.model.response.ResponseHistory;
import com.mevo.app.data.model.response.ResponseLogin;
import com.mevo.app.data.model.response.ResponseOpenRentals;
import com.mevo.app.data.model.response.ResponsePaymentForm;
import com.mevo.app.data.model.response.ResponsePaymentLink;
import com.mevo.app.data.model.response.ResponsePurchaseVoucher;
import com.mevo.app.data.model.response.ResponsePurchasedTariffs;
import com.mevo.app.data.model.response.ResponseRecoverPin;
import com.mevo.app.data.model.response.ResponseRegister;
import com.mevo.app.data.model.response.ResponseRentBike;
import com.mevo.app.data.model.response.ResponseReportProblem;
import com.mevo.app.data.model.response.ResponseReturnBike;
import com.mevo.app.data.model.response.ResponseStationBikes;
import com.mevo.app.data.model.response.ResponseTariffs;
import com.mevo.app.data.model.response.ResponseUpdateUser;
import com.mevo.app.data.model.response.ResponseUserDetails;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiNextbike {
    @FormUrlEncoded
    @POST("api/acceptAgreement.xml")
    Call<ResponseAgreementAccept> acceptAgreement(@Field("apikey") String str, @Field("loginkey") String str2, @Field("agreement_id") int i);

    @FormUrlEncoded
    @POST("api/booking.xml")
    Call<ResponseBookBike> bookBike(@Field("apikey") String str, @Field("loginkey") String str2, @Field("num_bikes") int i, @Field("place") String str3, @Field("start_time") Long l, @Field("end_time") Long l2);

    @FormUrlEncoded
    @POST("api/cancelBooking.xml")
    Call<ResponseCancelBooking> cancelBooking(@Field("apikey") String str, @Field("loginkey") String str2, @Field("booking_id") int i, @Field("show_errors") int i2);

    @FormUrlEncoded
    @POST("api/checkAgreement.xml")
    Call<ResponseAgreement> checkAgreement(@Field("apikey") String str, @Field("loginkey") String str2, @Field("agreement_id") int i);

    @GET("api/list.xml")
    Call<ResponseHistory> fetchHistory(@Query("apikey") String str, @Query("loginkey") String str2, @Query("limit") Integer num, @Query("offset") Integer num2);

    @GET("api/list.xml")
    @Headers({"Cache-Control: no-cache"})
    Call<ResponseHistory> fetchHistoryNoCache(@Query("apikey") String str, @Query("loginkey") String str2, @Query("limit") Integer num, @Query("offset") Integer num2);

    @FormUrlEncoded
    @POST("api/activeBookings.xml")
    Call<ResponseActiveBookings> getActiveBookings(@Field("apikey") String str, @Field("loginkey") String str2);

    @FormUrlEncoded
    @POST("api/getAgreementDataForDomain.xml")
    Call<ResponseAgreementForDomain> getAgreementForDomain(@Field("apikey") String str, @Field("domain") String str2);

    @FormUrlEncoded
    @POST("api/getBikeState.xml")
    Call<ResponseBikeState> getBikeState(@Field("apikey") String str, @Field("loginkey") String str2, @Field("bike") String str3);

    @FormUrlEncoded
    @POST("api/bikesAvailableToBook.xml")
    Call<ResponseReturnBike> getBikesAvailableToBook(@Field("apikey") String str, @Field("loginkey") String str2, @Field("place") int i);

    @GET("api/getPlaceDetails.xml")
    Call<ResponseStationBikes> getBikesForStation(@Query("apikey") String str, @Query("loginkey") String str2, @Query("place") int i);

    @FormUrlEncoded
    @POST("api/list.xml")
    Call<ResponseUserDetails> getCredits(@Field("apikey") String str, @Field("limit") int i, @Field("loginkey") String str2);

    @FormUrlEncoded
    @POST("api/getOpenRentals.xml")
    Call<ResponseOpenRentals> getCurrentRentals(@Field("apikey") String str, @Field("loginkey") String str2);

    @FormUrlEncoded
    @POST("api/getErrorTreeAsJson.xml")
    Call<ResponseBody> getErrorsList(@Field("apikey") String str, @Field("loginkey") String str2);

    @FormUrlEncoded
    @POST("api/getFlexzones.xml")
    Call<ResponseFlexzones> getFlexzones(@Field("apikey") String str, @Field("domain") String str2);

    @FormUrlEncoded
    @POST("api/getPaymentForm.xml")
    Call<ResponsePaymentForm> getPaymentForm(@Field("apikey") String str, @Field("loginkey") String str2);

    @FormUrlEncoded
    @POST("api/getPaymentForm.xml")
    Call<ResponsePaymentForm> getPaymentForm(@Field("apikey") String str, @Field("loginkey") String str2, @Field("amount") Integer num);

    @GET("api/getPaymentLinks.xml")
    Call<ResponsePaymentLink> getPaymentList(@Query("apikey") String str, @Query("loginkey") String str2);

    @FormUrlEncoded
    @POST("api/getRedeems.xml")
    Call<ResponsePurchasedTariffs> getPurchasedTariffs(@Field("apikey") String str, @Field("loginkey") String str2);

    @FormUrlEncoded
    @POST("api/tariffs.xml")
    Call<ResponseTariffs> getTariffs(@Field("apikey") String str, @Field("loginkey") String str2, @Field("domain") String str3, @Field("language") String str4);

    @FormUrlEncoded
    @POST("api/getUserDetails.xml")
    Call<ResponseUserDetails> getUserDetails(@Field("apikey") String str, @Field("loginkey") String str2);

    @FormUrlEncoded
    @POST("api/login.xml")
    Call<ResponseLogin> login(@Field("apikey") String str, @Field("mobile") String str2, @Field("pin") String str3, @Field("domain") String str4);

    @FormUrlEncoded
    @POST("api/updateUser.xml")
    Call<ResponseUpdateUser> newsletterAgreement(@Field("apikey") String str, @Field("loginkey") String str2, @Field("newsletter") int i);

    @FormUrlEncoded
    @POST("api/voucher.xml")
    Call<ResponsePurchaseVoucher> purchaseVoucher(@Field("apikey") String str, @Field("loginkey") String str2, @Field("code") String str3);

    @FormUrlEncoded
    @POST("api/pinRecover.xml")
    Call<ResponseRecoverPin> recoverPin(@Field("apikey") String str, @Field("mobile") String str2, @Field("domain") String str3);

    @FormUrlEncoded
    @POST("api/register.xml")
    Call<ResponseRegister> register(@Field("apikey") String str, @Field("mobile") String str2, @Field("domain") String str3, @Field("email") String str4, @Field("forename") String str5, @Field("name") String str6, @Field("address") String str7, @Field("zip") String str8, @Field("city") String str9, @Field("country") String str10, @Field("language") String str11, @Field("pesel") String str12, @Field("newsletter") int i, @Field("show_errors") int i2);

    @FormUrlEncoded
    @POST("api/rent.xml")
    Call<ResponseRentBike> rentBike(@Field("apikey") String str, @Field("loginkey") String str2, @Field("bike") String str3, @Field("callback_rent") String str4, @Field("callback_return") String str5);

    @GET("api/rentalBreak.xml")
    @Headers({"Cache-Control: no-cache"})
    Call<ResponseReturnBike> rentalBreak(@Query("apikey") String str, @Query("loginkey") String str2, @Query("rental") String str3, @Query("end") Boolean bool, @Query("lat") String str4, @Query("lng") String str5);

    @FormUrlEncoded
    @POST("api/return.xml")
    Call<ResponseReturnBike> returnBikeAtStation(@Field("apikey") String str, @Field("loginkey") String str2, @Field("bike") String str3, @Field("place") int i, @Field("show_errors") int i2);

    @FormUrlEncoded
    @POST("api/return.xml")
    Call<ResponseReturnBike> returnBikeOutsideStation(@Field("apikey") String str, @Field("loginkey") String str2, @Field("bike") String str3, @Field("place_name") String str4, @Field("lat") String str5, @Field("lng") String str6, @Field("accuracy") String str7, @Field("do_calc_auto_service_fee") int i, @Field("show_errors") int i2);

    @FormUrlEncoded
    @POST("api/sendErrorReport.xml")
    Call<ResponseReportProblem> sendErrorReport(@Field("apikey") String str, @Field("loginkey") String str2, @Field("maincategory") String str3, @Field("reports") String str4, @Field("number") Integer num, @Field("message") String str5, @Field("date") String str6, @Field("lat") String str7, @Field("lng") String str8);

    @FormUrlEncoded
    @POST("api/setCustomerRfid.xml")
    Call<ResponseCustomerRfid> setCustomerRfid(@Field("apikey") String str, @Field("loginkey") String str2, @Field("rfid") int i);

    @FormUrlEncoded
    @POST("api/updateUser.xml")
    Call<ResponseUpdateUser> updateSubscriptionExtraData(@Field("apikey") String str, @Field("loginkey") String str2, @Field("data_daily_reservations") String str3, @Field("data_daily_reservations_last_update") long j, @Field("data_daily_subscription_seconds_used") long j2, @Field("data_subscription_purchase_timestamp") long j3);

    @FormUrlEncoded
    @POST("api/updateUser.xml")
    Call<ResponseUpdateUser> updateUser(@Field("apikey") String str, @Field("loginkey") String str2, @Field("email") String str3, @Field("forename") String str4, @Field("name") String str5, @Field("address") String str6, @Field("zip") String str7, @Field("city") String str8, @Field("country") String str9, @Field("language") String str10, @Field("pesel") String str11, @Field("data_consent_a") String str12, @Field("data_consent_b") String str13, @Field("data_consent_c") String str14, @Field("data_consent_d") String str15, @Field("data_consent_e") String str16, @Field("data_consent_f") String str17);

    @FormUrlEncoded
    @POST("api/updateUser.xml")
    Call<ResponseUpdateUser> updateUserBookingHistory(@Field("apikey") String str, @Field("loginkey") String str2, @Field("data_daily_reservations") String str3, @Field("data_daily_reservations_last_update") long j, @Field("data_daily_subscription_seconds_used") long j2);

    @FormUrlEncoded
    @POST("api/updateUser.xml")
    Call<ResponseUpdateUser> updateUserInMyAccount(@Field("apikey") String str, @Field("loginkey") String str2, @Field("email") String str3, @Field("forename") String str4, @Field("name") String str5, @Field("address") String str6, @Field("zip") String str7, @Field("city") String str8, @Field("country") String str9, @Field("mobile") String str10, @Field("pesel") String str11);

    @FormUrlEncoded
    @POST("api/updateUser.xml")
    Call<ResponseUpdateUser> updateUserLanguage(@Field("apikey") String str, @Field("loginkey") String str2, @Field("language") String str3);

    @FormUrlEncoded
    @POST("api/updateUser.xml")
    Call<ResponseUpdateUser> updateUserReports(@Field("apikey") String str, @Field("loginkey") String str2, @Field("data_battery_reports") String str3);
}
