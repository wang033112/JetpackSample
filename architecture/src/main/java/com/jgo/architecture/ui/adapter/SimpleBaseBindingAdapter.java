package com.jgo.architecture.ui.adapter;

import android.content.Context;

import androidx.annotation.LayoutRes;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class SimpleBaseBindingAdapter<M, B extends ViewDataBinding> extends BaseBindingAdapter {

    private int layout;

    public SimpleBaseBindingAdapter(Context context, int layout) {
        super(context);
        this.layout = layout;
    }

    protected @LayoutRes
    int getLayoutResId(int viewType) {
        return this.layout;
    }

    protected abstract void onSimpleBindItem(B binding, M item, RecyclerView.ViewHolder holder);

    @Override
    protected void onBindItem(ViewDataBinding binding, Object item, RecyclerView.ViewHolder holder) {
        onSimpleBindItem((B) binding, (M) item, holder);
    }
}