package com.mevo.app.presentation.main.top_up;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.inverce.mod.core.Log;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.constants.TopUp;
import com.mevo.app.data.model.response.ResponsePaymentForm;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.repository.NextbikeApiRepository;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.dialogs.TopUpDialog;
import com.mevo.app.presentation.dialogs.TopUpDialog.TopUpListener;
import com.mevo.app.presentation.main.MainFragment;
import com.mevo.app.presentation.main.voucher.VoucherFragment;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.Utils;
import com.mevo.app.tools.UtilsWebView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopUpFragment extends MainFragment implements TopUpListener {
    private static final String TAG = "TopUpFragment";
    private TextView finishInBrowserInfo;
    private boolean isPageLoaded = false;
    private View progressBar;
    private TextView subtitle;
    private WebView webView;
    private WebView webViewCustomAmount;

    /* renamed from: com.mevo.app.presentation.main.top_up.TopUpFragment$1 */
    class C04561 extends WebViewClient {
        C04561() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            TopUpFragment.this.launchBrowserAndHideWebView(str);
            return true;
        }

        @TargetApi(21)
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            TopUpFragment.this.launchBrowserAndHideWebView(webResourceRequest.getUrl().toString());
            return true;
        }
    }

    /* renamed from: com.mevo.app.presentation.main.top_up.TopUpFragment$2 */
    class C04582 extends WebViewClient {

        /* renamed from: com.mevo.app.presentation.main.top_up.TopUpFragment$2$1 */
        class C04571 implements ValueCallback<String> {
            C04571() {
            }

            public void onReceiveValue(String str) {
                Log.m37d("Log", str);
            }
        }

        C04582() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            TopUpFragment.this.launchBrowserAndHideWebView(str);
            return true;
        }

        @TargetApi(21)
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            TopUpFragment.this.launchBrowserAndHideWebView(webResourceRequest.getUrl().toString());
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            if (TopUpFragment.this.isPageLoaded == null) {
                TopUpFragment.this.webViewCustomAmount.evaluateJavascript("javascript:document.forms[0].submit();", new C04571());
                TopUpFragment.this.isPageLoaded = true;
            }
        }
    }

    /* renamed from: com.mevo.app.presentation.main.top_up.TopUpFragment$3 */
    class C08333 implements Callback<ResponsePaymentForm> {
        C08333() {
        }

        public void onResponse(Call<ResponsePaymentForm> call, Response<ResponsePaymentForm> response) {
            if (response.body() == null || ((ResponsePaymentForm) response.body()).paymentForm == null) {
                TopToast.show(C0434R.string.error_ocurred, 0, TopToast.DURATION_LONG);
            } else {
                TopUpFragment.this.webViewCustomAmount.loadDataWithBaseURL(null, ((ResponsePaymentForm) response.body()).paymentForm, UtilsWebView.MIME, UtilsWebView.ENCODING, null);
            }
            TopUpFragment.this.activityInterface.setProgressViewVisibility(false);
        }

        public void onFailure(Call<ResponsePaymentForm> call, Throwable th) {
            TopToast.show(C0434R.string.error_ocurred, 0, TopToast.DURATION_LONG);
            TopUpFragment.this.activityInterface.setProgressViewVisibility(false);
        }
    }

    public static TopUpFragment newInstance() {
        return new TopUpFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_top_up, viewGroup, false);
        findViews(layoutInflater);
        prepareWebview();
        fetchFormHtml();
        return layoutInflater;
    }

    private void prepareWebview() {
        UtilsWebView.setWebviewSettings(this.webView);
        this.webView.setWebViewClient(new C04561());
        UtilsWebView.setWebviewSettings(this.webViewCustomAmount);
        this.webViewCustomAmount.setWebViewClient(new C04582());
    }

    private void launchBrowserAndHideWebView(String str) {
        if (str.contains(TopUp.CUSTOM_AMOUNT_URL)) {
            str = TopUpDialog.newInstance();
            str.setListener(this);
            str.show();
        } else if (str.contains(TopUp.CUSTOM_VOUCHER_URL)) {
            getActivityInterface().changeFragment(VoucherFragment.newInstance(), false);
        } else {
            Utils.launchBrowser(getContext(), str);
            this.webView.setVisibility(8);
            this.subtitle.setVisibility(8);
            this.finishInBrowserInfo.setVisibility(0);
        }
    }

    private void findViews(View view) {
        this.webViewCustomAmount = (WebView) view.findViewById(C0434R.id.fragment_top_up_webview_custom_amount);
        this.webView = (WebView) view.findViewById(C0434R.id.fragment_top_up_webview);
        this.progressBar = view.findViewById(C0434R.id.fragment_top_up_progress_bar);
        this.finishInBrowserInfo = (TextView) view.findViewById(C0434R.id.fragment_top_up_finish_in_browser_info);
        this.subtitle = (TextView) view.findViewById(C0434R.id.fragment_top_up_subtitle);
    }

    private void fetchFormHtml() {
        if (UserManager.getUser() != null) {
            this.progressBar.setVisibility(0);
            new NextbikeApiRepository().getPaymentForm(TopUp.TOP_UP_AMOUNTS, new TopUpFragment$$Lambda$0(this));
        }
    }

    final /* synthetic */ void lambda$fetchFormHtml$279$TopUpFragment(String str, boolean z, Exception exception) {
        this.progressBar.setVisibility(8);
        if (z) {
            this.webView.loadDataWithBaseURL(null, str, UtilsWebView.MIME, UtilsWebView.ENCODING, null);
            return;
        }
        this.subtitle.setText(true);
    }

    public void onTopUpClick(String str) {
        str = Integer.valueOf(str).intValue() * 100;
        if (this.activityInterface != null) {
            this.activityInterface.setProgressViewVisibility(true);
            Rest.getApiNextbike().getPaymentForm(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), Integer.valueOf(str)).enqueue(new C08333());
        }
    }
}
