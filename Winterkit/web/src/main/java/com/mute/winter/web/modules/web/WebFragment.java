package com.mute.winter.web.modules.web;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.mute.winter.core.utility.helper.UrlHelper;
import com.mute.winter.web.R;
import com.orhanobut.logger.Logger;

/**
 * @author dkoller
 * @since 23.09.2016
 */

public class WebFragment extends Fragment{

    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private WebView webView;

    private String url;
    private boolean enableJavaScript = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_webview, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        webView = view.findViewById(R.id.webView);

        initElements();
        setColors();
        setListener();

        loadUrl();

        return view;
    }

    private void loadUrl(){
        if (url != null) {
            Logger.d("loading url: %s", url);

            progressBar.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);

            webView.loadUrl(UrlHelper.getValidUrl(url));
        }else {
            Logger.e("url is invalid");
        }
    }

    public void initElements(){
        progressBar.setVisibility(View.VISIBLE);
        webView.setVisibility(View.GONE);

        webView.setWebViewClient(new WebClient());
        webView.getSettings().setJavaScriptEnabled(enableJavaScript);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    public void setColors(){
        //int accentColor = colorThemeHandler.getColor(ThemeColorEnum.COLOR_ACCENT);
        //LayoutHelper.styleProgressBar(progressBar, accentColor);
    }

    public void setListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                loadUrl();
            }
        });

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        webView.clearCache(true);
        super.onDestroyView();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEnableJavaScript(boolean enableJavaScript) {
        this.enableJavaScript = enableJavaScript;
    }

    private class WebClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            showWebViewContent();
        }

        private void showWebViewContent(){
            progressBar.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }
    }
}
