package com.jgo.video.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgo.video.R;
import com.jgo.video.databinding.FragmentSubHomeBinding;
import com.jgo.video.ui.base.BaseFragment;
import com.jgo.video.view_model.GameBViewModel;
import com.jgo.video.view_model.GameViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;


public class MainHomeFragment extends BaseFragment {

    private static final String TAG = MainHomeFragment.class.getSimpleName();
    private GameViewModel mGameA;
    private GameBViewModel mGameB;
    private Handler mHandler;
    private int mGameAFlag;
    private int mGameBFlag;

    private FragmentSubHomeBinding mBinding;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sub_home, container, false);
        mGameA = getAppViewModelProvider().get(GameViewModel.class);
        mGameB = getAppViewModelProvider().get(GameBViewModel.class);
        mBinding = DataBindingUtil.bind(root);
        mGameA.getHomeScore().observe(getViewLifecycleOwner(), homeScore -> {
            mBinding.subGameAHome.changeTextTo(String.valueOf(homeScore));
        });

        mGameA.getGuestScore().observe(getViewLifecycleOwner(), guestScore -> {
            mBinding.subGameAGuest.changeTextTo(String.valueOf(guestScore));
        });

        mGameB.getHomeScore().observe(getViewLifecycleOwner(), homeScore -> {
            mBinding.subGameBHome.changeTextTo(String.valueOf(homeScore));
        });

        mGameB.getGuestScore().observe(getViewLifecycleOwner(), guestScore -> {
            mBinding.subGameBGuest.changeTextTo(String.valueOf(guestScore));
        });
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        mHandler = new Handler();
        mGameA.getHomeScore().setValue(10);
        mGameA.getGuestScore().setValue(12);
        gameARunnable.start();
        mHandler.postDelayed(gameARunnable, 2000);

        mGameB.getHomeScore().setValue(11);
        mGameB.getGuestScore().setValue(13);
        gameBRunnable.start();
        mHandler.postDelayed(gameBRunnable, 3000);
    }

    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeCallbacks(gameARunnable);
        if (gameARunnable != null) {
            gameARunnable.stop();
        }

        mHandler.removeCallbacks(gameBRunnable);
        if (gameBRunnable != null) {
            gameBRunnable.stop();
        }
    }

    private GameBRunnable gameBRunnable = new GameBRunnable();
    private class GameBRunnable implements Runnable {
        private boolean shouldPost;
        @Override
        public void run() {
            mGameBFlag = mGameBFlag == 0 ? 1 :0;
            if (mGameAFlag == 0) {
                int currentValue = mGameB.getHomeScore().getValue();
                mGameB.getHomeScore().setValue(currentValue >= 95 ? 10 : currentValue + 2);
            } else {
                int currentValue = mGameB.getGuestScore().getValue();
                mGameB.getGuestScore().setValue(currentValue >= 95 ? 10 : currentValue + 2);
            }

            if (shouldPost) {
                mHandler.postDelayed(this, 2000);
            }
        }

        public void stop() {
            shouldPost = false;
        }

        public void start() {
            shouldPost = true;
        }
    }

    private GameARunnable gameARunnable = new GameARunnable();
    private class GameARunnable implements Runnable {
        private boolean shouldPost;
        @Override
        public void run() {
            mGameAFlag = mGameAFlag == 0 ? 1 :0;
            if (mGameAFlag == 0) {
                int currentValue = mGameA.getHomeScore().getValue();
                mGameA.getHomeScore().setValue(currentValue >= 95 ? 10 : currentValue + 3);
            } else {
                int currentValue = mGameA.getGuestScore().getValue();
                mGameA.getGuestScore().setValue(currentValue >= 95 ? 10 : currentValue + 3);
            }

            if (shouldPost) {
                mHandler.postDelayed(this, 3000);
            }
        }

        public void stop() {
            shouldPost = false;
        }

        public void start() {
            shouldPost = true;
        }
    }
}