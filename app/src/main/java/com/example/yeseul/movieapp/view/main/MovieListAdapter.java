package com.example.yeseul.movieapp.view.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.databinding.HolderMovieItemBinding;
import com.example.yeseul.movieapp.pojo.Movie;
import com.example.yeseul.movieapp.view.adapter.BaseRecyclerAdapter;
import com.example.yeseul.movieapp.view.adapter.BaseViewHolder;

public class MovieListAdapter extends BaseRecyclerAdapter<Movie, MovieListAdapter.ViewHolder> {

    public MovieListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new ViewHolder(parent);
    }

    public class ViewHolder extends BaseViewHolder<HolderMovieItemBinding, Movie> {

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.holder_movie_item);
        }

        @Override
        protected void bind(Movie movie) {
            binding.setItem(movie);
        }
    }
}
