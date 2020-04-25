package com.jgo.video.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgo.video.R;
import com.jgo.video.adapter.NewsAdapter;
import com.jgo.video.data.DataManager;
import com.jgo.video.data.bean.NewsInfo;
import com.jgo.video.databinding.FragmentSubNewsBinding;
import com.jgo.video.ui.base.BaseFragment;
import com.jgo.video.utils.ParaUtils;
import com.jgo.video.view_model.NewsViewModel;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;


public class MainNewsFragment extends BaseFragment implements NewsAdapter.ItemClick {

    private static final String TAG = MainNewsFragment.class.getSimpleName();
    private NewsViewModel mVm;
    private FragmentSubNewsBinding mBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sub_news, container, false);
        mBinding = FragmentSubNewsBinding.bind(root);
        mVm = getAppViewModelProvider().get(NewsViewModel.class);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        mBinding.rv.setAdapter(new NewsAdapter(getContext(), DataManager.getNewsList(getContext()), this));
    }

    @Override
    public void onClickItem(NewsInfo newsInfo) {

        ImageView imageView = Objects.requireNonNull(mBinding.rv.findViewHolderForLayoutPosition(newsInfo.getPosition())).itemView.findViewById(R.id.news_item_img);
        TextView titleView = Objects.requireNonNull(mBinding.rv.findViewHolderForLayoutPosition(newsInfo.getPosition())).itemView.findViewById(R.id.news_item_title);
        imageView.setTransitionName("imageViewShadreDebug" + newsInfo.getPosition());
        titleView.setTransitionName("titleViewShadreDebug" + newsInfo.getPosition());
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),
                new Pair(imageView, imageView.getTransitionName()),
                new Pair(titleView, titleView.getTransitionName()));

        MainFragmentDirections.ActionMainToNewsDetail directions = MainFragmentDirections.actionMainToNewsDetail("imageViewShadreDebug" + newsInfo.getPosition());
        ActivityNavigator.Extras extras = new ActivityNavigator.Extras.Builder().setActivityOptions(options).build();
        Bundle bundle = new Bundle();
        bundle.putString("transitionName", "imageViewShadreDebug" + newsInfo.getPosition());
        //Navigation.findNavController(imageView).navigate(R.id.detail,bundle, null, extras);
        //TODO
        ParaUtils.ImageTransitonName = "imageViewShadreDebug" + newsInfo.getPosition();
        ParaUtils.TitleTransitonName = "titleViewShadreDebug" + newsInfo.getPosition();
        Navigation.findNavController(imageView).navigate(directions, extras);
    }
}