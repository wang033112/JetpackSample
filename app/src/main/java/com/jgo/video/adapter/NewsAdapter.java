package com.jgo.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgo.video.R;
import com.jgo.video.data.bean.NewsInfo;
import com.jgo.video.databinding.ItemNewsBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ke-oh on 2020-03-20.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context mContext;
    private List<NewsInfo> mNewsList;

    private ItemClick mItemClick;

    public NewsAdapter(Context context, List<NewsInfo> newsList, ItemClick itemClick) {
        this.mContext = context;
        this.mNewsList = newsList;
        this.mItemClick = itemClick;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemNewsBinding binding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext())
                        ,R.layout.item_news,
                        parent,
                        false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(binding.getRoot());
        newsViewHolder.setBinding(binding);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        NewsInfo newsInfo = mNewsList.get(position);
        newsInfo.setPosition(position);
        holder.bind(newsInfo, mItemClick);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        private ItemNewsBinding mBinding;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(@NonNull NewsInfo newsInfo,ItemClick itemClick) {
            mBinding.setBean(newsInfo);
            mBinding.executePendingBindings();
            mBinding.setItemclick(itemClick);
        }

        public void setBinding(ItemNewsBinding binding) {
            mBinding = binding;
        }
    }

    public interface ItemClick {
        void onClickItem(NewsInfo newsInfo);
    }
}
