package io.fabric.sdk.android.services.common;

public class ResponseParser {
    public static final int ResponseActionDiscard = 0;
    public static final int ResponseActionRetry = 1;

    public static int parse(int i) {
        return (i < 200 || i > 299) ? (i < 300 || i > 399) ? (i < 400 || i > 499) ? i >= 500 ? 1 : 1 : 0 : 1 : 0;
    }
}
