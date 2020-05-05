package com.jgo.video.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgo.video.R;
import com.jgo.video.databinding.FragmentSubHomeBinding;
import com.jgo.video.ui.base.BaseFragment;
import com.jgo.video.view_model.NewsViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;


public class MainHomeFragment extends BaseFragment {

    private static final String TAG = MainHomeFragment.class.getSimpleName();
    private NewsViewModel mVm;

    private FragmentSubHomeBinding mBinding;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sub_home, container, false);
        mVm = getAppViewModelProvider().get(NewsViewModel.class);
        mBinding = DataBindingUtil.bind(root);
        //ShadowDrawable.setShadowDrawable(mBinding.resultLayout, Color.parseColor("#FFFFFF"), 30, R.color.light_gray, 50, 0, 0);

        return root;
    }
}