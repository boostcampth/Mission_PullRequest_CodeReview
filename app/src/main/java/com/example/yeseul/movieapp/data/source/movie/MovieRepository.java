package com.example.yeseul.movieapp.data.source.movie;

import android.content.Context;

import com.example.yeseul.movieapp.data.model.SearchMovieResponse;
import com.example.yeseul.movieapp.pojo.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;

public class MovieRepository implements MovieDataSource,MovieDataLocalSource {

    private static MovieRepository movieRepository = null;
    private MovieRemoteDataSource remoteDataSource;
    private MovieLocalDataSource localDataSource;

    private MovieRepository(Context context){
        remoteDataSource = MovieRemoteDataSource.getInstance();
        localDataSource=MovieLocalDataSource.getInstance(context);
    }

    public static MovieRepository getInstance(Context context){
        if(movieRepository == null){
            movieRepository = new MovieRepository(context);
        }
        return movieRepository;
    }

    @Override
    public Single<SearchMovieResponse> searchMovies(Map<String, String> request) {

        return remoteDataSource.searchMovies(request);
    }

    @Override
    public void insertMovie(Movie movie) {
        localDataSource.insertMovie(movie);
    }

    @Override
    public void deleteMoive(String title) {
        localDataSource.deleteMoive(title);
    }

    @Override
    public Boolean findMovie(String title) {
        return localDataSource.findMovie(title);
    }

    @Override
    public ArrayList<Movie> getMovieList() {
        return localDataSource.getMovieList();
    }
}
