package com.mevo.app.data.model;

import android.support.annotation.Nullable;
import android.util.Pair;
import com.mevo.app.tools.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.simpleframework.xml.Attribute;

public class Tariff {
    @Attribute(name = "brand")
    public String brand;
    @Attribute(name = "code")
    public String code;
    @Attribute(name = "currency")
    public String currency;
    @Attribute(name = "valid_days")
    public int daysValid;
    @Attribute(name = "text")
    public String description;
    @Attribute(name = "domain")
    public String domain;
    @Attribute(name = "price")
    public double price;
    @Attribute(name = "name")
    public String tariffName;
    @Attribute(name = "uid")
    public int uid;

    public Tariff(int i, String str, String str2, String str3, double d, String str4, String str5, String str6, int i2) {
        this.uid = i;
        this.tariffName = str;
        this.domain = str2;
        this.brand = str3;
        this.price = d;
        this.description = str4;
        this.currency = str5;
        this.code = str6;
        this.daysValid = i2;
    }

    @Nullable
    public Pair<Date, Date> getStartEndValidityDates() {
        Object parse;
        Object parse2;
        Throwable e;
        if (this.description == null) {
            return null;
        }
        Matcher matcher = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}").matcher(this.description);
        String group = matcher.find() ? matcher.group() : null;
        String group2 = matcher.find() ? matcher.group() : null;
        if (!(group == null || group2 == null)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.mm.yyyy");
            try {
                parse = simpleDateFormat.parse(group);
                try {
                    parse2 = simpleDateFormat.parse(group2);
                } catch (Exception e2) {
                    e = e2;
                    Log.ex(e);
                    parse2 = null;
                    return new Pair(parse, parse2);
                }
            } catch (Exception e3) {
                e = e3;
                parse = null;
                Log.ex(e);
                parse2 = null;
                return new Pair(parse, parse2);
            }
            if (!(parse == null || parse2 == null)) {
                return new Pair(parse, parse2);
            }
        }
        return null;
    }
}
