package com.mevo.app.tools.formatters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;
import com.google.android.gms.maps.model.LatLng;
import com.mevo.app.C0434R;
import com.mevo.app.constants.BikeTypes;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.tools.Utils;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Locale;

public class Formatter {
    private static final long DAY = 86400000;
    private static final long HOUR = 3600000;
    public static final long MINUTE = 60000;
    public static final long SECOND = 1000;
    private static final String TEXT_ARROW = "➝";

    public static String formatStandardDecimal(double d) {
        d = getBasicValue(d);
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(0);
        decimalFormat.setGroupingUsed(false);
        return decimalFormat.format(d);
    }

    public static String formatMoney(RentalItem rentalItem) {
        return new BigDecimal(rentalItem.price).divide(new BigDecimal(100), 2, RoundingMode.HALF_DOWN).toString().replace(".", ",");
    }

    public static String formatMoney(double d) {
        d = getBasicValue(d);
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setGroupingUsed(false);
        return decimalFormat.format(d).replace(".", ",");
    }

    public static String formatMoneyFromCents(double d) {
        d = new BigDecimal(d).divide(new BigDecimal(100), 2, RoundingMode.HALF_DOWN);
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setGroupingUsed(false);
        return decimalFormat.format(d).replace(".", ",");
    }

    private static BigDecimal getBasicValue(double d) {
        return getBasicValue(d, 2);
    }

    private static BigDecimal getBasicValue(double d, int i) {
        return new BigDecimal(d).setScale(i, RoundingMode.HALF_EVEN);
    }

    public static String formatDouble(double d, int i) {
        return formatDouble(d, i, 2);
    }

    public static String formatDouble(double d, int i, int i2) {
        d = getBasicValue(d, i2);
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(i);
        decimalFormat.setMinimumFractionDigits(i);
        decimalFormat.setGroupingUsed(0);
        return decimalFormat.format(d);
    }

    public static String formatDistance(double d) {
        if (d > 999.0d) {
            d = String.format(Locale.getDefault(), "%.1f km", new Object[]{Double.valueOf(d / 1000.0d)});
            if (d.endsWith("0 km")) {
                d = d.replace(".0", "");
            }
            return d;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((int) d);
        stringBuilder.append(" m");
        return stringBuilder.toString();
    }

    public static String formatBikeRange(double d) {
        if (d > 999.0d) {
            d = String.format(Locale.getDefault(), "%.1fkm", new Object[]{Double.valueOf(d / 1000.0d)});
            if (d.endsWith("0km")) {
                d = d.replace(".0", "");
            }
            return d;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((int) d);
        stringBuilder.append("m");
        return stringBuilder.toString();
    }

    public static String formatDuration(long j, Context context) {
        if (j < 60) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(j);
            stringBuilder.append(" ");
            stringBuilder.append(context.getString(2131689473));
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(j / 60);
        stringBuilder2.append(" ");
        stringBuilder2.append(context.getString(2131689472));
        return stringBuilder2.toString();
    }

    public static String formatDurationRent(long j, @NonNull Context context) {
        long j2 = j;
        Context context2 = context;
        String str = "";
        long j3 = j2 / 60;
        long j4 = j3 / 60;
        j3 %= 60;
        if (j2 < 60) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(j2);
            stringBuilder.append("s");
            str = stringBuilder.toString();
        }
        if (j4 < 1 && j3 < 10 && j3 >= 1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(j3);
            stringBuilder.append(context2.getString(C0434R.string.duration_minutes));
            str = stringBuilder.toString();
        }
        if (j4 < 1 && j3 > 9) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(j3);
            stringBuilder.append(context2.getString(C0434R.string.duration_minutes));
            str = stringBuilder.toString();
        }
        if (j4 > 0 && j3 < 10 && j3 >= 1) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(j4);
            stringBuilder2.append(context2.getString(C0434R.string.duration_hour));
            stringBuilder2.append(" ");
            stringBuilder2.append(BikeTypes.DEFAULT);
            stringBuilder2.append(j3);
            stringBuilder2.append(context2.getString(C0434R.string.duration_minutes));
            str = stringBuilder2.toString();
        }
        if (j4 > 0 && j3 > 9) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(j4);
            stringBuilder2.append(context2.getString(C0434R.string.duration_hour));
            stringBuilder2.append(" ");
            stringBuilder2.append(j3);
            stringBuilder2.append(context2.getString(C0434R.string.duration_minutes));
            str = stringBuilder2.toString();
        }
        if (j4 <= 0 || j3 != 0) {
            return str;
        }
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(j4);
        stringBuilder2.append(context2.getString(C0434R.string.duration_hour));
        return stringBuilder2.toString();
    }

    @SuppressLint({"DefaultLocale"})
    public static String formatDurationBooking(long j, Context context) {
        long j2 = j / 60;
        long j3 = j2 / 60;
        j = (j - (3600 * j3)) - (60 * (j2 % 60));
        if (j3 > 0) {
            return String.format("%d:%02d:%02d", new Object[]{Long.valueOf(j3), Long.valueOf(j2), Long.valueOf(j)});
        }
        return String.format("%02d:%02d", new Object[]{Long.valueOf(j2), Long.valueOf(j)});
    }

    public static String formatDurationColom(long j) {
        if (j > 3600) {
            return DateFormat.format("HH:mm:ss", new Date(j)).toString();
        }
        CharSequence charSequence = "HH:mm:ss";
        if (j <= 0) {
            j = 0;
        }
        return DateFormat.format(charSequence, new Date(j)).toString();
    }

    public static String formatHourMinute(long j) {
        if (j <= 0) {
            j = 0;
        }
        return DateFormat.format("HH:mm", new Date(j)).toString();
    }

    public static String formatDateAndTime(long j) {
        return j > 0 ? DateFormat.format("dd MMMM yyyy, HH:mm", new Date(j)).toString() : "";
    }

    public static String formatDate(long j) {
        return j > 0 ? DateFormat.format("dd MMMM yyyy", new Date(j)).toString() : "";
    }

    public static String formatDurationStartEndDates(long j, long j2) {
        String formatDateAndTime = formatDateAndTime(j);
        if (j2 <= 0) {
            return formatDateAndTime;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(formatDateAndTime);
        stringBuilder.append(" ➝ ");
        formatDateAndTime = stringBuilder.toString();
        if (Utils.isSameDay(j, j2) != null) {
            j = new StringBuilder();
            j.append(formatDateAndTime);
            j.append(formatHourMinute(j2));
            return j.toString();
        }
        j = new StringBuilder();
        j.append(formatDateAndTime);
        j.append("\n");
        j.append(formatDateAndTime(j2));
        return j.toString();
    }

    public static String formatRentStartEndDatesWithoutHours(RentalItem rentalItem) {
        String formatDate = formatDate(rentalItem.startTimestampSeconds * 1000);
        if (rentalItem.endTimestampSeconds <= 0 || Utils.isSameDay(rentalItem.startTimestampSeconds * 1000, rentalItem.endTimestampSeconds * 1000)) {
            return formatDate;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(formatDate);
        stringBuilder.append(" ➝ ");
        formatDate = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(formatDate);
        stringBuilder.append("\n");
        stringBuilder.append(formatDate(rentalItem.endTimestampSeconds * 1000));
        return stringBuilder.toString();
    }

    public static String formatLatLngToPlace(LatLng latLng, int i) {
        if (latLng == null) {
            return Operation.MINUS;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        stringBuilder.append(formatDouble(latLng.latitude, i, 4));
        stringBuilder.append(", ");
        stringBuilder.append(formatDouble(latLng.longitude, i, 4));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
