package com.jgo.video.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgo.video.R;
import com.jgo.video.adapter.NewsAdapter;
import com.jgo.video.data.DataManager;
import com.jgo.video.data.bean.NewsInfo;
import com.jgo.video.databinding.FragmentNewBinding;
import com.jgo.video.ui.base.BaseFragment;
import com.jgo.video.utils.ParaUtils;
import com.jgo.video.view_model.NewsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.SharedElementCallback;
import androidx.core.util.Pair;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class NewsFragment extends BaseFragment {

    private static final String TAG = NewsFragment.class.getSimpleName();

    private FragmentNewBinding mBinding;
    private NewsViewModel mVm;

    private int mCurrentItemPositon = -1;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_new, container, false);
        mVm = getAppViewModelProvider().get(NewsViewModel.class);
        mBinding = FragmentNewBinding.bind(root);
        mBinding.setVm(mVm);

        mBinding.newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        mBinding.newsRecyclerView.setAdapter(new NewsAdapter(getContext(), DataManager.getNewsList(getContext()), new NewsItemClick()));

        return root;
    }

    public class NewsItemClick implements NewsAdapter.ItemClick {

        @Override
        public void onClickItem(NewsInfo newsInfo) {
            Log.d(TAG, "onClick postion : " + newsInfo.getPosition());
            mCurrentItemPositon = newsInfo.getPosition();

            /*ImageView imageView = mBinding.newsRecyclerView.findViewHolderForLayoutPosition(newsInfo.getPosition()).itemView.findViewById(R.id.news_item_img);
            imageView.setTransitionName("imageViewShadreDebug" + newsInfo.getPosition());
            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(imageView, imageView.getTransitionName())
                    .build();

            NavController nav = Navigation.findNavController(imageView);
            nav.navigate(R.id.detail, null, null, extras);*/
            ImageView imageView = mBinding.newsRecyclerView.findViewHolderForLayoutPosition(newsInfo.getPosition()).itemView.findViewById(R.id.news_item_img);
            TextView titleView = mBinding.newsRecyclerView.findViewHolderForLayoutPosition(newsInfo.getPosition()).itemView.findViewById(R.id.news_item_title);
            imageView.setTransitionName("imageViewShadreDebug" + newsInfo.getPosition());
            titleView.setTransitionName("titleViewShadreDebug" + newsInfo.getPosition());
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    requireActivity(),
                    new Pair(imageView, imageView.getTransitionName()),
                    new Pair(titleView, titleView.getTransitionName()));

            NewsFragmentDirections.Detail directions = NewsFragmentDirections.detail("imageViewShadreDebug" + newsInfo.getPosition());
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
}