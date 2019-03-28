package com.mevo.app.data.model.response;

import com.google.gson.annotations.SerializedName;
import com.mevo.app.data.model.GoogleRoute;
import java.util.List;

public class ResponseGoogleRoute {
    public static final String STATUS_INVALID_REQUEST = "INVALID_REQUEST";
    public static final String STATUS_MAX_ROUTE_LENGTH_EXCEEDED = "MAX_ROUTE_LENGTH_EXCEEDED";
    public static final String STATUS_MAX_WAYPOINTS_EXCEEDED = "MAX_WAYPOINTS_EXCEEDED";
    public static final String STATUS_NOT_FOUND = "NOT_FOUND";
    public static final String STATUS_OK = "OK";
    public static final String STATUS_OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";
    public static final String STATUS_REQUEST_DENIED = "REQUEST_DENIED";
    public static final String STATUS_ZERO_RESULTS = "ZERO_RESULTS";
    public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    @SerializedName("routes")
    public List<GoogleRoute> routes;
    @SerializedName("status")
    public String status;
}
