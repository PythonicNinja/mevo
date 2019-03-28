package com.mevo.app.presentation.main.terms_of_use;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.mevo.app.C0434R;
import com.mevo.app.presentation.main.MainFragment;
import com.mevo.app.tools.TermsRepository;
import com.mevo.app.tools.UtilsWebView;

public class TermsPricingFragment extends MainFragment {
    private ProgressBar progressBar;
    private WebView webView;

    /* renamed from: com.mevo.app.presentation.main.terms_of_use.TermsPricingFragment$1 */
    class C04551 extends WebViewClient {
        C04551() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            TermsPricingFragment.this.progressBar.setVisibility(8);
        }
    }

    public static TermsPricingFragment newInstance() {
        return new TermsPricingFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_terms_pricing, viewGroup, false);
        findViews(layoutInflater);
        UtilsWebView.setWebviewSettings(this.webView);
        this.progressBar.setVisibility(0);
        this.webView.setWebViewClient(new C04551());
        this.webView.loadUrl(TermsRepository.getTermsOfUseUrl());
        return layoutInflater;
    }

    private void findViews(View view) {
        this.webView = (WebView) view.findViewById(C0434R.id.fragment_terms_pricing_web_view);
        this.progressBar = (ProgressBar) view.findViewById(C0434R.id.fragment_terms_pricing_progress);
    }
}
