package com.example.yeseul.movieapp.utils;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class POJODiffUtil<T> extends DiffUtil.Callback {

    private final List<T> mOldList;
    private final List<T> mNewList;

    public POJODiffUtil(List<T> oldList, List<T> newList) {
        this.mOldList = oldList;
        this.mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return mOldList.get(i).equals(mNewList.get(i1));
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        return true;
    }
}
