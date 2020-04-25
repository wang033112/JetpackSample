package com.jgo.video.view_model.request;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jgo.video.data.bean.MenuInfo;

import java.util.List;

public class InfoRequestViewModel extends ViewModel {

    private MutableLiveData<List<MenuInfo>> libraryLiveData;

    public MutableLiveData<List<MenuInfo>> getLibraryLiveData() {
        if (libraryLiveData == null) {
            libraryLiveData = new MutableLiveData<>();
        }
        return libraryLiveData;
    }

    public void setLibraryLiveData(List<MenuInfo> list) {
        if (libraryLiveData == null) {
            libraryLiveData = new MutableLiveData<>();
        }

        libraryLiveData.setValue(list);
    }
}
