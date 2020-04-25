package com.jgo.video.view_model;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<String>> mTitles;

    public NewsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");

        mTitles = new MutableLiveData<>();

        ArrayList<String> titles = new ArrayList<>();
        titles.add("NBA");
        titles.add("CBA");
        titles.add("JBA");
        mTitles.setValue(titles);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<ArrayList<String>> getTitles() {

        return mTitles;
    }
}