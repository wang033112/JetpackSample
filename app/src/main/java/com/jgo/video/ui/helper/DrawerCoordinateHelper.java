

package com.jgo.video.ui.helper;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.jgo.architecture.bridge.callback.UnPeekLiveData;
import com.jgo.video.view_model.callback.BaseViewModel;
import com.jgo.video.ui.base.BaseFragment;

/**
 * TODO tip：通过 Lifecycle 来解决抽屉侧滑禁用与否的判断的 一致性问题，
 * <p>
 * 每个需要注册和监听生命周期来判断的视图控制器，无需在各自内部手动书写解绑等操作。
 * 如果这样说还不理解，详见 https://xiaozhuanlan.com/topic/3684721950
 * <p>
 * Create by jgo at 19/11/3
 */
public class DrawerCoordinateHelper implements DefaultLifecycleObserver, View.OnTouchListener {

    private float downX;
    private float downY;

    public final UnPeekLiveData<Boolean> openDrawer = new UnPeekLiveData<>();

    private static DrawerCoordinateHelper sHelper = new DrawerCoordinateHelper();

    public static DrawerCoordinateHelper getInstance() {
        return sHelper;
    }

    private DrawerCoordinateHelper() {
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {

        BaseViewModel.tagOfSecondaryPages.add(owner.getClass().getSimpleName());

        ((BaseFragment) owner).getSharedViewModel()
                .enableSwipeDrawer.setValue(BaseViewModel.tagOfSecondaryPages.size() == 0);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {

        BaseViewModel.tagOfSecondaryPages.remove(owner.getClass().getSimpleName());

        ((BaseFragment) owner).getSharedViewModel()
                .enableSwipeDrawer.setValue(BaseViewModel.tagOfSecondaryPages.size() == 0);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                break;
            case MotionEvent.ACTION_UP:
                float dx = x - downX;
                float dy = y - downY;
                if (Math.abs(dx) > 8 && Math.abs(dy) > 8) {
                    int orientation = getOrientation(dx, dy);
                    switch (orientation) {
                        case 'r':
                            openDrawer.setValue(true);
                            break;
                        case 'l':
                            break;
                        case 't':
                            break;
                        case 'b':
                            break;
                    }
                }
                break;
        }
        return false;
    }

    private int getOrientation(float dx, float dy) {
        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? 'r' : 'l';
        } else {
            return dy > 0 ? 'b' : 't';
        }
    }
}
