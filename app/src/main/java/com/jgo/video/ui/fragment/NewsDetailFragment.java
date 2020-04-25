package com.jgo.video.ui.fragment;

import android.app.SharedElementCallback;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jgo.video.R;
import com.jgo.video.ui.base.BaseFragment;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.transition.Transition;
import androidx.viewpager.widget.ViewPager;

public class NewsDetailFragment extends BaseFragment {

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_news_detail, container, false);


    return view;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Transition transition = TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move);
    transition.setDuration(3000);
    getActivity().getWindow().setSharedElementEnterTransition(transition);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
}
