package com.jgo.video.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgo.video.R;
import com.jgo.video.adapter.SubImagesAdapter;
import com.jgo.video.data.DataManager;
import com.jgo.video.databinding.FragmentSubImageBinding;
import com.jgo.video.ui.base.BaseFragment;
import com.jgo.video.view_model.NewsViewModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


public class MainImageFragment extends BaseFragment {

    private static final String TAG = MainImageFragment.class.getSimpleName();
    private FragmentSubImageBinding mBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sub_image, container, false);
        //mVm = getAppViewModelProvider().get(NewsViewModel.class);
        mBinding = FragmentSubImageBinding.bind(root);
        mBinding.subImageRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mBinding.subImageRv.setAdapter(new SubImagesAdapter(getContext(), DataManager.getImageList(getContext()), null));
        return root;
    }
}