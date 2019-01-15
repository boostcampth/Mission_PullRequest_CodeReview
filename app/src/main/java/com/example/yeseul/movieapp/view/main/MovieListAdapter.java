package com.example.yeseul.movieapp.view.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.databinding.HolderMovieItemBinding;
import com.example.yeseul.movieapp.pojo.Movie;
import com.example.yeseul.movieapp.view.adapter.BaseRecyclerAdapter;

public class MovieListAdapter extends BaseRecyclerAdapter<Movie, MovieListAdapter.ViewHolder> {

    public MovieListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(ViewHolder holder, int position) {
        holder.binding.setItem(itemList.get(position));

        // 아이템 확장
        holder.binding.miniContent.setOnClickListener(v -> {
            Animation animation = new AlphaAnimation(0, 1);
            animation.setDuration(500);
            holder.binding.mainContent.setAnimation(animation);
            itemList.get(position).noExpand.set(!itemList.get(position).noExpand.get());
        });
        // 아이템 축소
        holder.binding.fold.setOnClickListener(v ->
                itemList.get(position).noExpand.set(!itemList.get(position).noExpand.get()));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_movie_item, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        HolderMovieItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
