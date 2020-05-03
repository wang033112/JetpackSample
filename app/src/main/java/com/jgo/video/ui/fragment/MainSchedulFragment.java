package com.jgo.video.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgo.video.R;
import com.jgo.video.adapter.SubScheduleAdapter;
import com.jgo.video.data.DataManager;
import com.jgo.video.databinding.FragmentSubSchedulBinding;
import com.jgo.video.ui.base.BaseFragment;
import com.jgo.video.view_model.NewsViewModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;


public class MainSchedulFragment extends BaseFragment {

    private static final String TAG = MainSchedulFragment.class.getSimpleName();
    private FragmentSubSchedulBinding mBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sub_schedul, container, false);
        mBinding = FragmentSubSchedulBinding.bind(root);
        mBinding.subScheduleRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.subScheduleRv.setAdapter(new SubScheduleAdapter(getContext(), DataManager.getScheduleList(getContext()), null));
        return root;
    }
}