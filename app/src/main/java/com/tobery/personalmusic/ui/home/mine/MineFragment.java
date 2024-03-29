package com.tobery.personalmusic.ui.home.mine;

import static com.tobery.personalmusic.util.Constant.PLAYLIST_ID;
import static com.tobery.personalmusic.util.Constant.PLAY_NAME;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.MarginPageTransformer;

import com.google.android.material.tabs.TabLayoutMediator;
import com.tobery.lib.util.PxUtils;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.FragmentMineBinding;
import com.tobery.personalmusic.ui.home.MainViewModel;
import com.tobery.personalmusic.util.ClickUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MineFragment extends Fragment {

    private FragmentMineBinding binding;

    private MainViewModel homeViewModel;

    private MineFragmentViewModel viewModel;

    private List<Fragment> fragmentList;
    private List<String> titleList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMineBinding.inflate(inflater,container,false);
        homeViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        viewModel = new ViewModelProvider(this).get(MineFragmentViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setVm(homeViewModel);
        binding.setMy(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel.initUi();
        initWebView();
        initView();
        initObserver();
    }

    private void initView() {
        binding.viewLikeItem.setOnClickListener(v -> {
            if (ClickUtil.enableClick()){
                Bundle bundle = new Bundle();
                bundle.putLong(PLAYLIST_ID,homeViewModel.userLikeCreator);
                bundle.putString(PLAY_NAME,"歌单");
                Navigation.findNavController(v).navigate(R.id.navigation_play_list,bundle);
               /* startActivity(new Intent(getActivity(), MinePlayListActivity.class)
                        .putExtra(PLAYLIST_ID,viewModel.userLikeCreator)
                        .putExtra(PLAY_NAME,"歌单"));*/
            }
        });
        initViewPager();
    }

    private void initViewPager() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new CreateSongListFragment());
        fragmentList.add(new CollectSongListFragment());
        fragmentList.add(new CollectSongListFragment());
        titleList = new ArrayList<>();
        titleList.add("创建歌单");
        titleList.add("收藏歌单");
        titleList.add("歌单助手");
        binding.viewpager2.setOffscreenPageLimit(3);
        int pix = PxUtils.dpToPx(16,getContext());
        MarginPageTransformer pageTransformer = new MarginPageTransformer(pix);
        binding.viewpager2.setPageTransformer(pageTransformer);
        binding.viewpager2.setAdapter(new ScreenPagerAdapter(this));
        new TabLayoutMediator(binding.tabLayout, binding.viewpager2,((tab, position) -> {
            tab.setText(titleList.get(position));
        })).attach();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        binding.webVip.getSettings().setJavaScriptEnabled(true);
    }

    private void initObserver() {

        viewModel.getVipInfo().observe(getViewLifecycleOwner(), vipInfoEntityApiResponse -> {
            if (vipInfoEntityApiResponse.getStatus() == Status.SUCCESS && vipInfoEntityApiResponse.getData().getData().getRedVipDynamicIconUrl2()!= null){
                binding.webVip.loadDataWithBaseURL(null,changeImageUrl(vipInfoEntityApiResponse.getData().getData().getRedVipDynamicIconUrl2()),"text/html", "utf-8", null);
            }
        });

        homeViewModel.getUserDetails().observe(getViewLifecycleOwner(), userDetailEntityApiResponse -> {
            if (userDetailEntityApiResponse.getStatus() == Status.SUCCESS){
                viewModel.level.set("Lv."+userDetailEntityApiResponse.getData().getLevel());
            }
        });
    }

    private String changeImageUrl(String url) {
        Document doc = null;
        try {
            InputStream file = getResources().getAssets().open("web/vip.html");
            doc = Jsoup.parse(file, "UTF-8", url);
            Elements pngs = doc.select("img[src$=.png]");
            pngs.attr("src",url);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert doc != null;
        return doc.toString();
    }

    private class ScreenPagerAdapter extends FragmentStateAdapter{

        public ScreenPagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentList.clear();
        fragmentList = null;
    }
}
