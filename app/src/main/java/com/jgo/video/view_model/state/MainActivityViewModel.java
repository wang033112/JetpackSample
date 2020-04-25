

package com.jgo.video.view_model.state;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

/**
 * Create by jgo at 19/10/29
 */
public class MainActivityViewModel extends ViewModel {

    public final ObservableBoolean openDrawer = new ObservableBoolean();

    public final ObservableBoolean allowDrawerOpen = new ObservableBoolean();

    {
        allowDrawerOpen.set(true);
    }
}
