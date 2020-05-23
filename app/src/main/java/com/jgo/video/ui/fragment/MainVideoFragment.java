package com.jgo.video.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgo.video.R;
import com.jgo.video.adapter.SubVideoAdapter;
import com.jgo.video.data.DataManager;
import com.jgo.video.databinding.FragmentSubVideoBinding;
import com.jgo.video.ui.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;


public class MainVideoFragment extends BaseFragment {

    private static final String TAG = MainVideoFragment.class.getSimpleName();
    private FragmentSubVideoBinding mBinding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sub_video, container, false);
        mBinding = DataBindingUtil.bind(root);
        mBinding.rv.setAdapter(new SubVideoAdapter(getActivity(), DataManager.getVideoList(getContext()), null));
        mBinding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }
}