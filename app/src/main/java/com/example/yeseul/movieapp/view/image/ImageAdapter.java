package com.example.yeseul.movieapp.view.image;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.databinding.HolderMovieImageBinding;
import com.example.yeseul.movieapp.pojo.Movie;
import com.example.yeseul.movieapp.view.adapter.BaseRecyclerAdapter;

import java.util.List;

public class ImageAdapter extends BaseRecyclerAdapter<Movie, ImageAdapter.ViewHolder> {

    public ImageAdapter(Context context, List<Movie> itemList) {
        super(context, itemList);
    }

    @Override
    protected void onBindView(ViewHolder holder, int position) {
        holder.binding.setItem(itemList.get(position));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_movie_image, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        HolderMovieImageBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
