

package com.jgo.video;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.jgo.video.view_model.state.MainActivityViewModel;
import com.jgo.video.databinding.ActivityMainBinding;
import com.jgo.video.ui.base.BaseActivity;

/**
 * Create by jgo at 19/10/16
 */

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mBinding;
    private MainActivityViewModel mMainActivityViewModel;
    private boolean isListened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setVm(mMainActivityViewModel);

        mSharedViewModel.activityCanBeClosedDirectly.observe(this, aBoolean -> {
            NavController nav = Navigation.findNavController(this, R.id.main_fragment_host);
            if (nav.getCurrentDestination() != null && nav.getCurrentDestination().getId() != R.id.mainFragment) {
                nav.navigateUp();

            } else if (mBinding.dl != null && mBinding.dl.isDrawerOpen(GravityCompat.START)) {
                mBinding.dl.closeDrawer(GravityCompat.START);

            } else {
                super.onBackPressed();
            }
        });

        mSharedViewModel.openOrCloseDrawer.observe(this, aBoolean -> {

            //TODO bindingAdapter just working well once here

//            mMainActivityViewModel.openDrawer.set(aBoolean);

            if (mBinding.dl != null) {
                if (aBoolean && !mBinding.dl.isDrawerOpen(GravityCompat.START)) {
                    mBinding.dl.openDrawer(GravityCompat.START);
                } else {
                    mBinding.dl.closeDrawer(GravityCompat.START);
                }
            }
        });


        mSharedViewModel.enableSwipeDrawer.observe(this, aBoolean -> {

            if (mBinding.dl != null) {
                mBinding.dl.setDrawerLockMode(aBoolean
                        ? DrawerLayout.LOCK_MODE_UNLOCKED
                        : DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!isListened) {
            mSharedViewModel.timeToAddSlideListener.setValue(true);
            isListened = true;
        }
    }

    @Override
    public void onBackPressed() {
        mSharedViewModel.closeSlidePanelIfExpanded.setValue(true);
    }
}
