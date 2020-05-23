package com.jgo.video.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.jgo.architecture.ui.adapter.SimpleBaseBindingAdapter;
import com.jgo.video.R;
import com.jgo.video.view_model.request.InfoRequestViewModel;
import com.jgo.video.view_model.state.DrawerViewModel;
import com.jgo.video.data.bean.MenuInfo;
import com.jgo.video.databinding.FragmentDrawerBinding;
import com.jgo.video.ui.base.BaseFragment;
import com.jgo.video.databinding.AdapterLibraryBinding;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DrawerFragment extends BaseFragment {

    private FragmentDrawerBinding mBinding;
    private DrawerViewModel mDrawerViewModel;
    private InfoRequestViewModel mInfoRequestViewModel;
    private SimpleBaseBindingAdapter<MenuInfo, AdapterLibraryBinding> mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInfoRequestViewModel = ViewModelProviders.of(this).get(InfoRequestViewModel.class);
        mDrawerViewModel = ViewModelProviders.of(this).get(DrawerViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        mBinding = FragmentDrawerBinding.bind(view);
        mBinding.setVm(mDrawerViewModel);
        mBinding.setClick(new ClickProxy());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new SimpleBaseBindingAdapter<MenuInfo, AdapterLibraryBinding>(getContext(), R.layout.adapter_library) {
            @Override
            protected void onSimpleBindItem(AdapterLibraryBinding binding, MenuInfo item, RecyclerView.ViewHolder holder) {
                binding.tvIcon.setImageDrawable(getActivity().getDrawable(item.getIconId()));
                binding.tvSummary.setText(item.getTitle());
                binding.getRoot().setOnClickListener((v) -> {
                    int navAction = -1;
                    switch (item.getIconId()) {
                        case R.drawable.ic_news :
                            navAction = R.id.action_mainFragment_to_newsFragment;

                            break;
                    }

                    if (navAction != -1) {

                        closeDrawer();

                        NavController nav = Navigation.findNavController(getActivity(), R.id.main_fragment_host);
                        nav.navigate(navAction);
                    }
                });

                //binding.tvSummary.setText(item.getSummary());
            }
        };

        mBinding.rv.setAdapter(mAdapter);

        mInfoRequestViewModel.getLibraryLiveData().observe(getViewLifecycleOwner(), libraryInfos -> {
            mInitDataCame = true;
            if (mAnimationLoaded && libraryInfos != null) {
                mAdapter.setList(libraryInfos);
                mAdapter.notifyDataSetChanged();
            }
        });


        List<MenuInfo> menuInfos = new ArrayList<>();
        menuInfos.add(new MenuInfo("ホーム", R.drawable.ic_home));
        menuInfos.add(new MenuInfo("ニュース", R.drawable.ic_news));
        menuInfos.add(new MenuInfo("ビデオ", R.drawable.ic_video));
        menuInfos.add(new MenuInfo("日程", R.drawable.ic_date));
        menuInfos.add(new MenuInfo("選手情報", R.drawable.ic_players));
        mInfoRequestViewModel.setLibraryLiveData(menuInfos);
        //mInfoRequestViewModel.requestLibraryInfo();
    }

    @Override
    public void loadInitData() {
        super.loadInitData();
        if (mInfoRequestViewModel.getLibraryLiveData().getValue() != null) {
            mAdapter.setList(mInfoRequestViewModel.getLibraryLiveData().getValue());
            mAdapter.notifyDataSetChanged();
        }
    }

    public class ClickProxy {

        public void logoClick() {
            NavController nav = Navigation.findNavController(getActivity(), R.id.main_fragment_host);
            nav.navigate(R.id.action_mainFragment_to_newsFragment);
        }

        public void newsFragmentClick() {
            nav().navigate(R.id.action_mainFragment_to_newsFragment);
        }
    }

}
