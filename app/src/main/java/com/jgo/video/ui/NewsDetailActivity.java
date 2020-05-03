

package com.jgo.video.ui;

import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;

import com.jgo.video.R;
import com.jgo.video.databinding.ActivityNewsDetailBinding;
import com.jgo.video.ui.base.BaseActivity;
import com.jgo.video.utils.ParaUtils;
import com.jgo.video.view_model.state.MainActivityViewModel;

import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

/**
 * Create by jgo at 19/10/16
 */

public class NewsDetailActivity extends BaseActivity {

    private ActivityNewsDetailBinding mBinding;
    private MainActivityViewModel mMainActivityViewModel;
    private boolean isListened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail);
        mBinding.fragmentNewsDetailImg.setTransitionName(ParaUtils.ImageTransitonName);
        mBinding.newsDetailTitle.setTransitionName(ParaUtils.TitleTransitonName);
        Transition transition = TransitionInflater.from(this).inflateTransition(android.R.transition.move);
        transition.setDuration(100);
        getWindow().setSharedElementEnterTransition(transition);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!isListened) {
            mSharedViewModel.timeToAddSlideListener.setValue(true);
            isListened = true;
        }
    }
}
