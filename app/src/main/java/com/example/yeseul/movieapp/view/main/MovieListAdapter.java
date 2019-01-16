package com.example.yeseul.movieapp.view.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
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

        // 항목 펼치기
        holder.binding.miniContent.setOnClickListener(v -> {
            // 애니메이션 적용
            Animation animation = new AlphaAnimation(0, 1);
            animation.setDuration(500);
            holder.binding.mainContent.setAnimation(animation);

            ObservableBoolean noExpand = itemList.get(position).getNoExpand();
            noExpand.set(!noExpand.get());
        });
        // 항목 접기
        holder.binding.fold.setOnClickListener(v -> {

            ObservableBoolean noExpand = itemList.get(position).getNoExpand();
            noExpand.set(!noExpand.get());
        });
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
