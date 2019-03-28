package android.support.v4.content;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.util.ArrayList;

public final class MimeTypeFilter {
    private MimeTypeFilter() {
    }

    private static boolean mimeTypeAgainstFilter(@NonNull String[] strArr, @NonNull String[] strArr2) {
        if (strArr2.length != 2) {
            throw new IllegalArgumentException("Ill-formatted MIME type filter. Must be type/subtype.");
        }
        if (!strArr2[0].isEmpty()) {
            if (!strArr2[1].isEmpty()) {
                if (strArr.length != 2) {
                    return false;
                }
                if (!Operation.MULTIPLY.equals(strArr2[0]) && !strArr2[0].equals(strArr[0])) {
                    return false;
                }
                if (Operation.MULTIPLY.equals(strArr2[1]) || strArr2[1].equals(strArr[1]) != null) {
                    return true;
                }
                return false;
            }
        }
        throw new IllegalArgumentException("Ill-formatted MIME type filter. Type or subtype empty.");
    }

    public static boolean matches(@Nullable String str, @NonNull String str2) {
        if (str == null) {
            return null;
        }
        return mimeTypeAgainstFilter(str.split(Operation.DIVISION), str2.split(Operation.DIVISION));
    }

    @Nullable
    public static String matches(@Nullable String str, @NonNull String[] strArr) {
        if (str == null) {
            return null;
        }
        str = str.split(Operation.DIVISION);
        for (String str2 : strArr) {
            if (mimeTypeAgainstFilter(str, str2.split(Operation.DIVISION))) {
                return str2;
            }
        }
        return null;
    }

    @Nullable
    public static String matches(@Nullable String[] strArr, @NonNull String str) {
        if (strArr == null) {
            return null;
        }
        str = str.split(Operation.DIVISION);
        for (String str2 : strArr) {
            if (mimeTypeAgainstFilter(str2.split(Operation.DIVISION), str)) {
                return str2;
            }
        }
        return null;
    }

    @NonNull
    public static String[] matchesMany(@Nullable String[] strArr, @NonNull String str) {
        int i = 0;
        if (strArr == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        str = str.split(Operation.DIVISION);
        int length = strArr.length;
        while (i < length) {
            String str2 = strArr[i];
            if (mimeTypeAgainstFilter(str2.split(Operation.DIVISION), str)) {
                arrayList.add(str2);
            }
            i++;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
