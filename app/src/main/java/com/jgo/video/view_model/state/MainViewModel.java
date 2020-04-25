package com.jgo.video.view_model.state;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * Create by jgo at 19/10/29
 */
public class MainViewModel extends ViewModel {

    public final ObservableBoolean initTabAndPage = new ObservableBoolean();
    public final ObservableField<String> pageAssetPath = new ObservableField<>();


}
