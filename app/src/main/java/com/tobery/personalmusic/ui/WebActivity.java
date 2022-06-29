package com.tobery.personalmusic.ui;

import static com.tobery.personalmusic.util.Constant.BANNER_URI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.databinding.ActivityWebBinding;
import com.tobery.personalmusic.util.ClickUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

public class WebActivity extends BaseActivity {

    private ActivityWebBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.web.setSettings(this);
        String url = getIntent().getStringExtra(BANNER_URI);
        binding.web.setUrl(url);
        binding.viewTitle.ivBack.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                finish();
            }
        });
        binding.web.setCromeClient(binding.web.getWebChromeClient());
        binding.web.setCromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                binding.viewTitle.tvTitle.setText(title);
                binding.viewTitle.tvTitle.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.web.setPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.web.setResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.web.setDestory();
    }
}