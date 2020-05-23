package com.jgo.video.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import com.jgo.video.R;
import com.jgo.video.view_model.state.MainViewModel;
import com.jgo.video.databinding.FragmentMainBinding;
import com.jgo.video.ui.base.BaseFragment;
import com.jgo.video.ui.helper.DrawerCoordinateHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by jgo at 19/10/29
 */
public class MainFragment extends BaseFragment{

    private FragmentMainBinding mBinding;
    private MainViewModel mMainViewModel;
    private ClickProxy mClickProxy;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mBinding = FragmentMainBinding.bind(view);
        mBinding.setClick(mClickProxy = new ClickProxy());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.viewPager.setAdapter(new MainFragmentAdapter(getActivity().getSupportFragmentManager()));

        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);


        DrawerCoordinateHelper.getInstance().openDrawer.observe(getViewLifecycleOwner(), aBoolean -> {
            mBaseViewModel.openOrCloseDrawer.setValue(true);
        });
    }

    public class MainFragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList = new ArrayList<>();
        private int[] titles = new int[]{R.string.item_home,
                R.string.item_news, R.string.item_video,
                R.string.item_image, R.string.item_schedule};

        MainFragmentAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.fragmentList.add(new MainHomeFragment());
            this.fragmentList.add(new MainNewsFragment());
            this.fragmentList.add(new MainVideoFragment());
            this.fragmentList.add(new MainImageFragment());
            this.fragmentList.add(new MainSchedulFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(titles[position]);
        }
    }

    public class ClickProxy {

        public void openMenu() {
            mBaseViewModel.openOrCloseDrawer.setValue(true);
        }

        public void search() {
            //nav().navigate(R.id.action_mainFragment_to_searchFragment);
        }
    }

}
