package com.example.yeseul.movieapp.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public abstract class BaseViewHolder<B extends ViewDataBinding, D> extends RecyclerView.ViewHolder {
    protected B binding;
    private Context context;

    public BaseViewHolder(ViewGroup parent, @LayoutRes int layoutResId) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false));
        this.binding = DataBindingUtil.bind(itemView);
        context = itemView.getContext();
    }

    protected abstract void bind(D data);

    protected void recycled() {
        // no-op
    }

    protected Context getContext() {
        return context;
    }
}