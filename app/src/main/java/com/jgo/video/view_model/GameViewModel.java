package com.jgo.video.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameViewModel extends ViewModel {

    private MutableLiveData<Integer> mHomeScore;
    private MutableLiveData<Integer> mGuestScore;

    public GameViewModel(/*int homeScore, int guestScore*/) {
        mHomeScore = new MutableLiveData<>();
        mHomeScore.setValue(10);

        mGuestScore = new MutableLiveData<>();
        mGuestScore.setValue(11);
    }

    public MutableLiveData<Integer> getHomeScore() {
        return mHomeScore;
    }

    public void setHomeScore(MutableLiveData<Integer> mHomeScore) {
        this.mHomeScore = mHomeScore;
    }

    public MutableLiveData<Integer> getGuestScore() {
        return mGuestScore;
    }

    public void setGuestScore(MutableLiveData<Integer> mGuestScore) {
        this.mGuestScore = mGuestScore;
    }
}