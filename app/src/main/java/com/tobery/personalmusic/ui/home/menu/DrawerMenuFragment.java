package com.tobery.personalmusic.ui.home.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tobery.personalmusic.databinding.FragmentDrawberMenuBinding;
import com.tobery.personalmusic.ui.home.MainViewModel;

public class DrawerMenuFragment extends Fragment {

    private FragmentDrawberMenuBinding binding;
    private MainViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDrawberMenuBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setVm(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.initUi();
        Log.e("网址", viewModel.ui.imageUrl.get());
    }
}
