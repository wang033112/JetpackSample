package com.jgo.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.jgo.video.R;
import com.jgo.video.data.bean.ImageInfo;
import com.jgo.video.data.bean.NewsInfo;
import com.jgo.video.databinding.ItemImagesBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ke-oh on 2020-04-18.
 */
public class SubImagesAdapter extends RecyclerView.Adapter<SubImagesAdapter.SubImageViewHolder> {

    private Context mContext;
    private List<ImageInfo> mImagesList;

    private ItemClick mItemClick;

    public SubImagesAdapter(Context context, List<ImageInfo> imageList, ItemClick itemClick) {
        this.mContext = context;
        this.mImagesList = imageList;
        this.mItemClick = itemClick;
    }

    @NonNull
    @Override
    public SubImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemImagesBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_images, parent, false);
        SubImageViewHolder newsViewHolder = new SubImageViewHolder(binding.getRoot());
        newsViewHolder.setBinding(binding);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubImagesAdapter.SubImageViewHolder holder, int position) {
        ImageInfo imageInfo = mImagesList.get(position);
        //ImageInfo.setPosition(position);
        holder.bind(imageInfo, mItemClick);
    }

    @Override
    public int getItemCount() {
        return mImagesList.size();
    }

    public class SubImageViewHolder extends RecyclerView.ViewHolder {

        private ItemImagesBinding mBinding;

        public SubImageViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(@NonNull ImageInfo imageInfo,ItemClick itemClick) {
            Glide.with(mContext).load(imageInfo.getSrc()).into(mBinding.subImageItemIv);
            ViewGroup.LayoutParams lp = mBinding.getRoot().getLayoutParams();
            lp.height = imageInfo.getHeight();
            mBinding.getRoot().setLayoutParams(lp);
            mBinding.executePendingBindings();
            //mBinding.setItemclick(itemClick);
        }

        public void setBinding(ItemImagesBinding binding) {
            mBinding = binding;
        }
    }

    public interface ItemClick {
        void onClickItem(NewsInfo newsInfo);
    }
}
