package org.simpleframework.xml.transform;

import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Locale;
import java.util.regex.Pattern;

class LocaleTransform implements Transform<Locale> {
    private final Pattern pattern = Pattern.compile(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);

    public Locale read(String str) throws Exception {
        String[] split = this.pattern.split(str);
        if (split.length >= 1) {
            return read(split);
        }
        throw new InvalidFormatException("Invalid locale %s", str);
    }

    private Locale read(String[] strArr) throws Exception {
        String[] strArr2 = new String[]{"", "", ""};
        for (int i = 0; i < strArr2.length; i++) {
            if (i < strArr.length) {
                strArr2[i] = strArr[i];
            }
        }
        return new Locale(strArr2[0], strArr2[1], strArr2[2]);
    }

    public String write(Locale locale) {
        return locale.toString();
    }
}
