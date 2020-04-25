package com.jgo.video.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgo.video.R;
import com.jgo.video.ui.base.BaseFragment;
import com.jgo.video.view_model.NewsViewModel;

import androidx.annotation.NonNull;


public class MainSchedulFragment extends BaseFragment {

    private static final String TAG = MainSchedulFragment.class.getSimpleName();
    private NewsViewModel mVm;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sub_schedul, container, false);
        mVm = getAppViewModelProvider().get(NewsViewModel.class);

        return root;
    }
}