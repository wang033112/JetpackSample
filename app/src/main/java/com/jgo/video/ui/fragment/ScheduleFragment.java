

package com.jgo.video.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgo.video.R;
import com.jgo.video.view_model.state.SearchViewModel;
import com.jgo.video.ui.base.BaseFragment;
import com.jgo.video.ui.helper.DrawerCoordinateHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

/**
 * Create by jgo at 19/10/29
 */
public class ScheduleFragment extends BaseFragment {

    //private FragmentSearchBinding mBinding;
    private SearchViewModel mSearchViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        getLifecycle().addObserver(DrawerCoordinateHelper.getInstance());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        //mBinding = FragmentSearchBinding.bind(view);
        //mBinding.setClick(new ClickProxy());
        //mBinding.setVm(mSearchViewModel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public class ClickProxy {

        public void back() {
            nav().navigateUp();
        }

        public void testNav() {
            String u = "https://xiaozhuanlan.com/topic/5860149732";
            Uri uri = Uri.parse(u);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

        public void subscribe() {
            String u = "https://xiaozhuanlan.com/jgo";
            Uri uri = Uri.parse(u);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }
}
