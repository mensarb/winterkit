package com.mute.winter.core.modules.private_policy;

import android.app.ActionBar;
import android.os.Bundle;
import android.webkit.WebView;

import com.mute.winter.core.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * @author sanakan
 * @since 30.09.2017
 */

public class PrivatePolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_policy);

        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        WebView webview = findViewById(R.id.webview);

        String url = getString(R.string.private_policy_url);
        webview.loadUrl(url);
    }
}
