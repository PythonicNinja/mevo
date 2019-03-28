package com.mevo.app.tools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.maps.model.LatLng;
import com.inverce.mod.core.IM;
import com.mevo.app.App;
import com.mevo.app.C0434R;
import com.mevo.app.constants.BikeTypes.BikeGroup;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Tools {
    private static final String TAG = "Tools";

    public static class IntentPicker {
        public static Uri outputFileUri;

        public static Intent getPickIntent(Context context) {
            List arrayList = new ArrayList();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Environment.getExternalStorageDirectory());
            stringBuilder.append(File.separator);
            stringBuilder.append("_veturilo_");
            stringBuilder.append(File.separator);
            File file = new File(stringBuilder.toString());
            file.mkdirs();
            stringBuilder = new StringBuilder();
            stringBuilder.append("problem_image_");
            stringBuilder.append(System.currentTimeMillis());
            outputFileUri = Uri.fromFile(new File(file, stringBuilder.toString()));
            setCameraIntents(arrayList, outputFileUri, context);
            context = new Intent();
            context.setType("image/*");
            context.setAction("android.intent.action.GET_CONTENT");
            arrayList.add(new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI));
            Intent createChooser = Intent.createChooser((Intent) arrayList.remove(0), null);
            if (!arrayList.isEmpty()) {
                createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
            }
            return createChooser;
        }

        private static void setCameraIntents(List<Intent> list, Uri uri, Context context) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
                String str = resolveInfo.activityInfo.packageName;
                Intent intent2 = new Intent(intent);
                intent2.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                intent2.setPackage(str);
                intent2.putExtra("output", uri);
                list.add(intent2);
            }
        }
    }

    public static class Stream {
        public static void close(java.io.Closeable r0) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
            /*
            if (r0 == 0) goto L_0x0005;
        L_0x0002:
            r0.close();	 Catch:{ Exception -> 0x0005 }
        L_0x0005:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.tools.Tools.Stream.close(java.io.Closeable):void");
        }
    }

    public static boolean ifLessThanMinute(long j) {
        return j < 60;
    }

    public static String getBikesType(BikeGroup bikeGroup) {
        Context context = IM.context();
        String string = context.getString(C0434R.string.bike_type_standard);
        String string2 = context.getString(C0434R.string.bike_type_tandem);
        String string3 = context.getString(C0434R.string.bike_type_kids_4);
        String string4 = context.getString(C0434R.string.bike_type_kids_6);
        String string5 = context.getString(C0434R.string.bike_type_electric);
        switch (bikeGroup) {
            case SMARTBIKE:
                return string5;
            case KIDS_4:
                return string3;
            case KIDS_6:
                return string4;
            case TANDEM:
                return string2;
            default:
                return string;
        }
    }

    public static boolean isNullorEmpty(String str) {
        if (str != null) {
            if ("".equals(str) == null) {
                return null;
            }
        }
        return true;
    }

    public static String ifStringIsNullOrEmpty(String str) {
        if (str != null) {
            if (!str.isEmpty()) {
                return str;
            }
        }
        return "";
    }

    public static String changeToNullIfEmpty(String str) {
        return isNullorEmpty(str) ? null : str;
    }

    public static String getAppVersion() {
        try {
            return App.getAppContext().getPackageManager().getPackageInfo(App.getAppContext().getPackageName(), 0).versionName;
        } catch (Throwable e) {
            Log.ex(TAG, e);
            return null;
        }
    }

    public static String phoneNumberFormatter(String str) {
        return str.length() > 9 ? str.substring(2) : str;
    }

    public static void goToApplicationSystemSettings() {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package:");
        stringBuilder.append(App.getAppContext().getPackageName());
        intent.setData(Uri.parse(stringBuilder.toString()));
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        App.getAppContext().startActivity(intent);
    }

    @Nullable
    public static LatLng locationToLatLng(Location location) {
        return location != null ? new LatLng(location.getLatitude(), location.getLongitude()) : null;
    }

    public static long getUnixTime() {
        return (long) ((int) (Calendar.getInstance().getTimeInMillis() / 1000));
    }

    @ColorInt
    public static int adjustAlphaHex(String str, float f) {
        if (!str.startsWith("#")) {
            str = "#".concat(str);
        }
        str = Color.parseColor(str);
        return Color.argb(Math.round(((float) Color.alpha(str)) * f), Color.red(str), Color.green(str), Color.blue(str));
    }

    public static String coordinateToApiString(double d) {
        return Double.toString(d).replace(",", ".");
    }
}
