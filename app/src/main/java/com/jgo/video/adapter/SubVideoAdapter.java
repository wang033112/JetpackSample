package com.jgo.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.jgo.video.R;
import com.jgo.video.data.bean.NewsInfo;
import com.jgo.video.data.bean.ScheduleInfo;
import com.jgo.video.data.bean.VideoInfo;
import com.jgo.video.databinding.ItemScheduleBinding;
import com.jgo.video.databinding.ItemVideosBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ke-oh on 2020-05-20.
 */
public class SubVideoAdapter extends RecyclerView.Adapter<SubVideoAdapter.SubVideoViewHolder> {

    private Context mContext;
    private List<VideoInfo> mVideosList;

    private ItemClick mItemClick;

    public SubVideoAdapter(Context context, List<VideoInfo> videoList, ItemClick itemClick) {
        this.mContext = context;
        this.mVideosList = videoList;
        this.mItemClick = itemClick;
    }

    @NonNull
    @Override
    public SubVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideosBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_videos, parent, false);
        SubVideoViewHolder videoViewHolder = new SubVideoViewHolder(binding.getRoot());
        videoViewHolder.setBinding(binding);
        return videoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubVideoViewHolder holder, int position) {
        VideoInfo videoInfo = mVideosList.get(position);
        holder.bind(videoInfo, mItemClick);
    }

    @Override
    public int getItemCount() {
        return mVideosList.size();
    }

    public class SubVideoViewHolder extends RecyclerView.ViewHolder {

        private ItemVideosBinding mBinding;

        public SubVideoViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(@NonNull VideoInfo videoInfo,ItemClick itemClick) {
            //Glide.with(mContext).load(imageInfo.getSrc()).into(mBinding.subImageItemIv);
            /*ViewGroup.LayoutParams lp = mBinding.getRoot().getLayoutParams();
            lp.height = imageInfo.getHeight();
            mBinding.getRoot().setLayoutParams(lp);*/
            /*Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/gobold_regular.ttf");
            mBinding.scheduleTime.setTypeface(typeface);
            mBinding.schedulePlace.setTypeface(typeface);*/
            Glide.with(mContext).load(videoInfo.getSrc()).into(mBinding.subVideoItemIv);
            mBinding.executePendingBindings();
            //mBinding.setItemclick(itemClick);
        }

        public void setBinding(ItemVideosBinding binding) {
            mBinding = binding;
        }
    }

    public interface ItemClick {
        void onClickItem(NewsInfo newsInfo);
    }
}
