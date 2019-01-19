package com.example.yeseul.movieapp.data.source.movie;

import android.content.Context;

import com.example.yeseul.movieapp.data.local.MyDataBase;
import com.example.yeseul.movieapp.pojo.Movie;

import java.util.ArrayList;

public class MovieLocalDataSource implements MovieDataLocalSource {

    private static MovieLocalDataSource movieLocalDataSource = null;
    private Context context;

    private MovieLocalDataSource(Context context) {
        this.context = context;
    }

    ;

    public static MovieLocalDataSource getInstance(Context context) {
        if (movieLocalDataSource == null) {
            movieLocalDataSource = new MovieLocalDataSource(context);
        }
        return movieLocalDataSource;
    }


    @Override
    public void insertMovie(Movie movie) {
        MyDataBase.getInstance(context).insertMovie(movie);
    }

    @Override
    public void deleteMoive(String title) {
        MyDataBase.getInstance(context).deleteMoive(title);
    }

    @Override
    public Boolean findMovie(String title) {
        return MyDataBase.getInstance(context).findMovie(title);
    }

    @Override
    public ArrayList<Movie> getMovieList() {
        return MyDataBase.getInstance(context).getMovieList();
    }
}
