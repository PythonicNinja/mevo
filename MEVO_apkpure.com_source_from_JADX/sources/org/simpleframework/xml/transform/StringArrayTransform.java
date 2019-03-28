package org.simpleframework.xml.transform;

import java.util.regex.Pattern;

class StringArrayTransform implements Transform<String[]> {
    private final Pattern pattern;
    private final String token;

    public StringArrayTransform() {
        this(",");
    }

    public StringArrayTransform(String str) {
        this.pattern = Pattern.compile(str);
        this.token = str;
    }

    public String[] read(String str) {
        return read(str, this.token);
    }

    private String[] read(String str, String str2) {
        str = this.pattern.split(str);
        for (str2 = null; str2 < str.length; str2++) {
            String str3 = str[str2];
            if (str3 != null) {
                str[str2] = str3.trim();
            }
        }
        return str;
    }

    public String write(String[] strArr) {
        return write(strArr, this.token);
    }

    private String write(String[] strArr, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : strArr) {
            if (str2 != null) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(str);
                    stringBuilder.append(' ');
                }
                stringBuilder.append(str2);
            }
        }
        return stringBuilder.toString();
    }
}
