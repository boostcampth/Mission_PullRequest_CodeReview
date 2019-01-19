package com.example.yeseul.movieapp.view.adapter;

public interface OnItemClickListener {

    void onItemClick(int position);
    void onItemLongClick(int position);
    void onFavoriteClick(int position, boolean isExist);
}
