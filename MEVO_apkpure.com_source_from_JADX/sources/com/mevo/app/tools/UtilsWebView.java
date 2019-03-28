package com.mevo.app.tools;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

public class UtilsWebView {
    public static final String ENCODING = "utf-8";
    public static final int FONT_SIZE = 34;
    public static final String MIME = "text/html";

    /* renamed from: com.mevo.app.tools.UtilsWebView$1 */
    static class C04661 implements OnLongClickListener {
        public boolean onLongClick(View view) {
            return true;
        }

        C04661() {
        }
    }

    public static String enlargeFontBruteForce(String str, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("font-size:");
        stringBuilder.append(i);
        stringBuilder.append("px;");
        return str.replaceAll("font-size\\s*:\\s*\\d*\\s*(px|em)", stringBuilder.toString());
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static void setWebviewSettings(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setSupportMultipleWindows(false);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDisplayZoomControls(false);
        settings.setSaveFormData(false);
        settings.setMinimumFontSize(32);
        webView.setHorizontalScrollBarEnabled(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        webView.setOnLongClickListener(new C04661());
        webView.setLongClickable(false);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
    }
}
