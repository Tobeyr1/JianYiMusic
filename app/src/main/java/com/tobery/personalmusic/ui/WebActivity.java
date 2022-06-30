package com.tobery.personalmusic.ui;

import static com.tobery.personalmusic.util.Constant.BANNER_URI;

import android.os.Bundle;
import android.view.View;

import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.databinding.ActivityWebBinding;
import com.tobery.personalmusic.util.ClickUtil;


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
        binding.web.getTitle(title ->{
            binding.viewTitle.tvTitle.setText(title);
            binding.viewTitle.tvTitle.setVisibility(View.VISIBLE);
            return null;
        });
        binding.web.setUrl(url);
        binding.viewTitle.ivBack.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                finish();
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
        binding.web.setDestroy();
    }
}