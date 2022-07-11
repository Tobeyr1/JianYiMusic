package com.tobery.personalmusic.ui.daily;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.databinding.ActivityDailySongsBinding;
import com.tobery.personalmusic.util.ClickUtil;

public class DailySongsActivity extends BaseActivity {

    private ActivityDailySongsBinding binding;
    private DailySongsAdapter adapter;
    private DailySongsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDailySongsBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(DailySongsViewModel.class);
        setContentView(binding.getRoot());
        initRecycle();
        initView();
        initObserver();
    }

    private void initView() {
        binding.ivBack.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                finish();
            }
        });
    }

    private void initObserver() {
        viewModel.getDailySongs().observe(this, dailySongsEntityApiResponse -> {
            if (dailySongsEntityApiResponse.getStatus() == Status.SUCCESS){
                adapter.setDataList(dailySongsEntityApiResponse.getData().getData().getDailySongs());
            }
        });
    }

    private void initRecycle() {
        adapter = new DailySongsAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvDaily.setLayoutManager(manager);
        binding.rvDaily.setAdapter(adapter);
        binding.rvDaily.setHasFixedSize(true);
    }
}