package com.mevo.app.presentation.main.how_it_works;

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

public class HowItWorksFragment extends MainFragment {
    private static final String TAG = "HowItWorksFragment";
    private ProgressBar progressBar;
    private WebView webView;

    /* renamed from: com.mevo.app.presentation.main.how_it_works.HowItWorksFragment$1 */
    class C04471 extends WebViewClient {
        C04471() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            HowItWorksFragment.this.progressBar.setVisibility(8);
        }
    }

    public static HowItWorksFragment newInstance() {
        return new HowItWorksFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_how_it_works, viewGroup, false);
        findViews(layoutInflater);
        UtilsWebView.setWebviewSettings(this.webView);
        this.progressBar.setVisibility(0);
        this.webView.setWebViewClient(new C04471());
        this.webView.loadUrl(TermsRepository.getFaqUrl());
        return layoutInflater;
    }

    private void findViews(View view) {
        this.webView = (WebView) view.findViewById(C0434R.id.fragment_how_it_works_web_view);
        this.progressBar = (ProgressBar) view.findViewById(C0434R.id.fragment_how_it_works_progress);
    }
}
