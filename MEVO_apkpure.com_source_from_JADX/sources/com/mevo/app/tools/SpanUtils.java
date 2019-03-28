package com.mevo.app.tools;

import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.widget.EditText;
import android.widget.TextView;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;

public class SpanUtils {
    SpannableStringBuilder builder;
    int length = 0;
    private final TextView tw;

    public static abstract class ClickableSpanNoUnderline extends ClickableSpan {
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
        }
    }

    private static class URLSpanNoUnderline extends URLSpan {
        public URLSpanNoUnderline(String str) {
            super(str);
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
        }
    }

    public static SpanUtils on(TextView textView) {
        return new SpanUtils(textView);
    }

    private SpanUtils(TextView textView) {
        this.tw = textView;
        this.builder = new SpannableStringBuilder();
    }

    public SpanUtils asterisks(@ColorRes int i) {
        return coloredText(Operation.MULTIPLY, i);
    }

    public SpanUtils space() {
        return normalText(" ");
    }

    public SpanUtils enter() {
        return normalText("\n");
    }

    public SpanUtils coloredText(@StringRes int i, @ColorRes int i2) {
        return coloredText(text(i), i2);
    }

    public SpanUtils coloredText(String str, @ColorRes int i) {
        this.builder.append(str);
        this.builder.setSpan(new ForegroundColorSpan(color(i)), this.length, this.length + str.length(), 17);
        this.length += str.length();
        return this;
    }

    public SpanUtils clickableText(@StringRes int i, ClickableSpan clickableSpan) {
        return clickableText(text(i), clickableSpan);
    }

    public SpanUtils clickableText(String str, ClickableSpan clickableSpan) {
        this.builder.append(str);
        this.builder.setSpan(clickableSpan, this.length, this.length + str.length(), 17);
        this.builder.setSpan(new ForegroundColorSpan(color(C0434R.color.backgroundLightBlue)), this.length, this.length, 17);
        this.length += str.length();
        return this;
    }

    public SpanUtils clickableTextUrl(@StringRes int i, String str, @ColorRes int i2) {
        return clickableTextUrl(text(i), str, i2);
    }

    public SpanUtils clickableTextUrl(String str, String str2, @ColorRes int i) {
        this.builder.append(str);
        this.builder.setSpan(new URLSpanNoUnderline(str2), this.length, this.length + str.length(), 17);
        this.builder.setSpan(new ForegroundColorSpan(color(i)), this.length, this.length + str.length(), 17);
        this.length += str.length();
        return this;
    }

    public SpanUtils clickableTextUrlBold(@StringRes int i, String str) {
        return clickableTextUrlBold(text(i), str);
    }

    public SpanUtils clickableTextUrlBold(String str, String str2) {
        this.builder.append(str);
        this.builder.setSpan(new URLSpanNoUnderline(str2), this.length, this.length + str.length(), 17);
        this.builder.setSpan(new StyleSpan(1), this.length, this.length + str.length(), 17);
        this.builder.setSpan(new ForegroundColorSpan(color(C0434R.color.white)), this.length, this.length + str.length(), 17);
        this.length += str.length();
        return this;
    }

    public SpanUtils coloredBoldText(String str, @ColorRes int i) {
        this.builder.append(str);
        this.builder.setSpan(new ForegroundColorSpan(color(i)), this.length, this.length + str.length(), 17);
        this.builder.setSpan(new StyleSpan(1), this.length, this.length + str.length(), 17);
        this.length += str.length();
        return this;
    }

    public SpanUtils clickableTextDialog(@StringRes int i, @ColorRes int i2, ClickableSpan clickableSpan) {
        return clickableTextDialog(text(i), i2, clickableSpan);
    }

    public SpanUtils clickableTextDialog(String str, @ColorRes int i, ClickableSpan clickableSpan) {
        this.builder.append(str);
        this.builder.setSpan(clickableSpan, this.length, this.length + str.length(), 17);
        this.builder.setSpan(new ForegroundColorSpan(color(i)), this.length, this.length + str.length(), 17);
        this.length += str.length();
        return this;
    }

    public SpanUtils normalText(@StringRes int i) {
        return normalText(text(i));
    }

    public SpanUtils normalText(String str) {
        if (str == null) {
            return this;
        }
        this.builder.append(str);
        this.length += str.length();
        return this;
    }

    public SpanUtils boldText(@StringRes int i) {
        return boldText(text(i));
    }

    public SpanUtils boldText(String str) {
        this.builder.append(str);
        this.builder.setSpan(new StyleSpan(1), this.length, this.length + str.length(), 17);
        this.length += str.length();
        return this;
    }

    private static int color(@ColorRes int i) {
        return IM.context().getResources().getColor(i);
    }

    private static String text(@StringRes int i) {
        return IM.context().getResources().getString(i);
    }

    public SpanUtils setLinkColor(@ColorRes int i) {
        this.tw.setLinkTextColor(color(i));
        return this;
    }

    public void doneHint() {
        this.tw.setHint(this.builder);
        if (this.tw instanceof EditText) {
            this.tw.setCursorVisible(true);
        } else {
            this.tw.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    public void done() {
        this.tw.setText(this.builder);
        this.tw.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void doneSetText() {
        this.tw.setText(this.builder);
    }

    public Spannable makeSpannable() {
        return this.builder;
    }
}
