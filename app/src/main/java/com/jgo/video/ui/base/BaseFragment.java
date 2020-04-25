

package com.jgo.video.ui.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.jgo.video.App;
import com.jgo.video.view_model.callback.BaseViewModel;


/**
 * Create by jgo at 19/7/11
 */
public class BaseFragment extends Fragment {

    protected AppCompatActivity mActivity;
    protected BaseViewModel mBaseViewModel;
    protected boolean mAnimationLoaded;
    protected boolean mInitDataCame;

    private static Handler sHandler = new Handler();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseViewModel = getAppViewModelProvider().get(BaseViewModel.class);
    }

    @Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        sHandler.postDelayed(() -> {
            mAnimationLoaded = true;
            if (mInitDataCame) {
                loadInitData();
            }
        }, 280);
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    public void loadInitData() {

    }

    public boolean isDebug() {
        return mActivity.getApplicationContext().getApplicationInfo() != null &&
                (mActivity.getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    public void showLongToast(String text) {
        Toast.makeText(mActivity.getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    public void showShortToast(String text) {
        Toast.makeText(mActivity.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(int stringRes) {
        showLongToast(mActivity.getApplicationContext().getString(stringRes));
    }

    public void showShortToast(int stringRes) {
        showShortToast(mActivity.getApplicationContext().getString(stringRes));
    }

    protected ViewModelProvider getAppViewModelProvider() {
        return ((App) mActivity.getApplicationContext()).getAppViewModelProvider(mActivity);
    }

    protected NavController nav() {
        return NavHostFragment.findNavController(this);
    }

    protected void closeDrawer(){
        mBaseViewModel.openOrCloseDrawer.setValue(false);
    }

    protected void openDrawer(){
        mBaseViewModel.openOrCloseDrawer.setValue(true);
    }

    public BaseViewModel getSharedViewModel() {
        return mBaseViewModel;
    }
}
