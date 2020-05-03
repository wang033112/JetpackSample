package com.jgo.video.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.jgo.video.R;
import com.jgo.video.data.bean.ImageInfo;
import com.jgo.video.data.bean.NewsInfo;
import com.jgo.video.data.bean.ScheduleInfo;
import com.jgo.video.databinding.ItemImagesBinding;
import com.jgo.video.databinding.ItemScheduleBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ke-oh on 2020-04-26.
 */
public class SubScheduleAdapter extends RecyclerView.Adapter<SubScheduleAdapter.SubScheduleViewHolder> {

    private Context mContext;
    private List<ScheduleInfo> mImagesList;

    private ItemClick mItemClick;

    public SubScheduleAdapter(Context context, List<ScheduleInfo> imageList, ItemClick itemClick) {
        this.mContext = context;
        this.mImagesList = imageList;
        this.mItemClick = itemClick;
    }

    @NonNull
    @Override
    public SubScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemScheduleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_schedule, parent, false);
        SubScheduleViewHolder scheduleViewHolder = new SubScheduleViewHolder(binding.getRoot());
        scheduleViewHolder.setBinding(binding);
        return scheduleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubScheduleViewHolder holder, int position) {
        ScheduleInfo imageInfo = mImagesList.get(position);
        //ImageInfo.setPosition(position);
        holder.bind(imageInfo, mItemClick);
    }

    @Override
    public int getItemCount() {
        return mImagesList.size();
    }

    public class SubScheduleViewHolder extends RecyclerView.ViewHolder {

        private ItemScheduleBinding mBinding;

        public SubScheduleViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(@NonNull ScheduleInfo imageInfo,ItemClick itemClick) {
            //Glide.with(mContext).load(imageInfo.getSrc()).into(mBinding.subImageItemIv);
            /*ViewGroup.LayoutParams lp = mBinding.getRoot().getLayoutParams();
            lp.height = imageInfo.getHeight();
            mBinding.getRoot().setLayoutParams(lp);*/
            /*Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/gobold_regular.ttf");
            mBinding.scheduleTime.setTypeface(typeface);
            mBinding.schedulePlace.setTypeface(typeface);*/

            mBinding.executePendingBindings();
            //mBinding.setItemclick(itemClick);
        }

        public void setBinding(ItemScheduleBinding binding) {
            mBinding = binding;
        }
    }

    public interface ItemClick {
        void onClickItem(NewsInfo newsInfo);
    }
}
