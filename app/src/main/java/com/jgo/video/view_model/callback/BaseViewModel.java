

package com.jgo.video.view_model.callback;

import androidx.lifecycle.ViewModel;

import com.jgo.architecture.bridge.callback.UnPeekLiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by jgo at 19/10/16
 */
public class BaseViewModel extends ViewModel {

    public final UnPeekLiveData<Boolean> timeToAddSlideListener = new UnPeekLiveData<>();

    public final UnPeekLiveData<Boolean> closeSlidePanelIfExpanded = new UnPeekLiveData<>();

    public final UnPeekLiveData<Boolean> activityCanBeClosedDirectly = new UnPeekLiveData<>();

    public final UnPeekLiveData<Boolean> openOrCloseDrawer = new UnPeekLiveData<>();

    public final UnPeekLiveData<Boolean> enableSwipeDrawer = new UnPeekLiveData<>();

    public static List<String> tagOfSecondaryPages = new ArrayList<>();

}
