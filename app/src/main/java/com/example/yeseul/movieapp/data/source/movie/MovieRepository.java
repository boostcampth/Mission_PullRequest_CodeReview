package com.example.yeseul.movieapp.data.source.movie;

import com.example.yeseul.movieapp.data.model.SearchMovieResponse;
import com.example.yeseul.movieapp.pojo.Movie;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

/*
 * Presenter 에서 Repository 에 접근할 때 사용하는 ref */
public class MovieRepository implements MovieDataSource {

    private static MovieRepository movieRepository = null;

    private MovieRemoteDataSource remoteDataSource;

    private MovieRepository(){
        remoteDataSource = MovieRemoteDataSource.getInstance();
    }

    public static MovieRepository getInstance(){
        if(movieRepository == null){
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    @Override
    public Single<SearchMovieResponse> searchMovies(Map<String, String> request) {

        return remoteDataSource.searchMovies(request);
    }
}
