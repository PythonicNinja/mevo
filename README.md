# mevo
mevo app decompiled


# interesting api routes
https://mevo-api.nextbike.net/maps/nextbike-live.xml

there is scrapper for live maps so that map analysis can be performed how bikes moved across the infrastructure
Crontab rule
`$ crontab -e`
```
*/2   *       *       *       *       cd /path_to_poject/mevo/maps && python3 daily.py  >> output.log 2>&1
```

# example curl login based on code analysis

```
curl -X POST \
  https://mevo-api.nextbike.net/api/login.xml \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
	"api_key": "2QwXmCM6h7p6DMeE",
	"domain": "tc",
	"mobile": "<phonenumber>",
	"pin": "<pin>"
}'

<?xml version="1.0" encoding="utf-8"?>
<nextbike version="1.0" server_time="1553557835" >
    <user mobile="<phonenumber>" loginkey="<important_token>" rfid_uids="" active="0" lang="PL" domain="tc" currency="PLN" credits="0" free_seconds="0" payment="cs" team_owner="0" team_id="0" max_bikes="1" screen_name="<name>" newsletter="0" accepted_terms="tc" needs_email_verification="0" required_actions="activate_account" is_vcn_active="0" is_swift_active="0" data_daily_reservations="[]" data_daily_reservations_last_update="" data_subscription_purchase_timestamp="1552651627" pesel="<pesel>" />
    <account server_time="" />
</nextbike>
```

`loginkey` is most important from above response


# how to reserve bike programatically

1. obtain loginkey from above
2. obtain place
3. obtain start_time
4. obtain end_time
5. make api_call

```
curl -X POST \
  https://mevo-api.nextbike.net/api/booking.xml \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
	"apikey": "2QwXmCM6h7p6DMeE",
	"loginkey": "<login_key>",
	"num_bikes": 1,
	"place": "11121753",
	"start_time": 1553814199, # this needs to be synced with server_time + 3 seconds at least as in source
	"end_time": 1553814925 # this can be max start_time + 900 (s) - 900 / 60 = 15min
}'
```
as a result you will get:
```
<?xml version="1.0" encoding="utf-8"?>
<nextbike version="1.0" server_time="1553814100" >
    <booking id="102295" biketypes="[]" place_id="11121753" place_name="10100" lat="54.445355" lng="18.570946" spot="1" start_time="1553814125" end_time="1553814925" num_bikes="1" booked="1" max_bookingtime_before_rental="0" state="" state_id="0" cust_id="4326798" booking_code="711805" price="0" price_service="0" confirm="1" used_free_seconds="0" />
</nextbike>
```


# other parts of the code with constants:

```
package com.mevo.app.constants;

public class Servers {
    public static final String API_EXTENDED_PUSH_NODE_URL = "v1/send-push";
    public static final String API_EXTENDED_URL_PROD = "http://apiapp.veturilo.net.pl/";
    public static final String API_NEXTBIKE_MAP_PROD = "https://mevo-maps.nextbike.net/";
    public static final String API_NEXTBIKE_MAP_STG = "https://px-15.nextbike.net/";
    public static final String API_NEXTBIKE_PROD = "https://mevo-api.nextbike.net";
    public static final String API_NEXTBIKE_STG = "https://px-15.nextbike.net/";
    public static final String EXTENDED_API_SYSTEM_ID = "mevo";
    public static final String MEVO_HEATMAP_KEY = "96dfb8fb4fb9";
    public static final String MEVO_REPORTS_KEY = "PAu8qBmuT9iH5pWs=";
    public static final String NEXTBIKE_APIKEY_PROD = "2QwXmCM6h7p6DMeE";
    public static final String NEXTBIKE_APIKEY_STG = "2QwXmCM6h7p6DMeE";
    public static final String POLLS_APIKEY = "AQKEbjwK5syQW6Ff";
}

```


```
public static class CONFIG_DEBUG extends AppConfig {
        public CONFIG_DEBUG() {
            this.isRelease = true;
            this.apiNextbikeUrl = "https://px-15.nextbike.net/";
            this.apikeyNextbike = "2QwXmCM6h7p6DMeE";
            this.apiNextbikeMapUrl = "https://px-15.nextbike.net/";
            this.domain = Domains.DOMAIN_MEVO;
            this.bikeDomains = Collections.singletonList(Domains.DOMAIN_MEVO);
            this.apiExtendedUrl = Servers.API_EXTENDED_URL_PROD;
            this.fabricEnabled = false;
            this.loggerEnabled = true;
            this.serverSwitchAvailable = false;
            this.returnMaxDistanceMeters = 700000.0d;
            this.rentMaxDistanceMeters = 700000.0d;
            this.cacheEnabled = true;
        }
    }

    public static class CONFIG_RELEASE_PROD extends AppConfig {
        public CONFIG_RELEASE_PROD() {
            this.isRelease = true;
            this.apiNextbikeUrl = Servers.API_NEXTBIKE_PROD;
            this.apikeyNextbike = "2QwXmCM6h7p6DMeE";
            this.apiNextbikeMapUrl = Servers.API_NEXTBIKE_MAP_PROD;
            this.domain = Domains.DOMAIN_MEVO;
            this.bikeDomains = Collections.singletonList(Domains.DOMAIN_MEVO);
            this.apiExtendedUrl = Servers.API_EXTENDED_URL_PROD;
            this.fabricEnabled = true;
            this.loggerEnabled = false;
            this.serverSwitchAvailable = false;
            this.returnMaxDistanceMeters = 30.0d;
            this.rentMaxDistanceMeters = 75.0d;
            this.cacheEnabled = true;
        }
    }

    public static class CONFIG_RELEASE_STG extends CONFIG_RELEASE_PROD {
        public CONFIG_RELEASE_STG() {
            this.apiNextbikeUrl = "https://px-15.nextbike.net/";
            this.apikeyNextbike = "2QwXmCM6h7p6DMeE";
            this.apiNextbikeMapUrl = "https://px-15.nextbike.net/";
            this.domain = Domains.DOMAIN_MEVO;
            this.bikeDomains = Collections.singletonList(Domains.DOMAIN_MEVO);
        }
    }
```


```
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
```



```
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
```

```
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
```

```
public interface ApiNextbikeMap {
    @GET("maps/nextbike-live.xml")
    Call<ResponseStationsData> getStationsData(@Query("domains") String str, @Query("lat") Double d, @Query("lng") Double d2, @Query("distance") Integer num);
}
```
