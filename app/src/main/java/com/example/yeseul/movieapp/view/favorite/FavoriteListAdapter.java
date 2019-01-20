package com.example.yeseul.movieapp.view.favorite;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.databinding.HolderFavoriteItemBinding;
import com.example.yeseul.movieapp.db.FavoriteEntity;
import com.example.yeseul.movieapp.view.adapter.BaseRecyclerAdapter;

public class FavoriteListAdapter extends BaseRecyclerAdapter<FavoriteEntity, FavoriteListAdapter.ViewHolder> {

    public FavoriteListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(ViewHolder holder, int position) {
        holder.binding.setItem(itemList.get(position));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_favorite_item, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        HolderFavoriteItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}
