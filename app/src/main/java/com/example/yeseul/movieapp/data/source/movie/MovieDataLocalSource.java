package com.example.yeseul.movieapp.data.source.movie;

import com.example.yeseul.movieapp.pojo.Movie;

import java.util.ArrayList;

public interface MovieDataLocalSource {

    void insertMovie(Movie movie);

    void deleteMoive(String title);

    Boolean findMovie(String title);

    ArrayList<Movie> getMovieList();
}
