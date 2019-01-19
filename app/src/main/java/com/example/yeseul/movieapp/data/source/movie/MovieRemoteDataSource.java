package com.example.yeseul.movieapp.data.source.movie;

import com.example.yeseul.movieapp.data.model.SearchMovieResponse;
import com.example.yeseul.movieapp.data.remote.MovieApi;
import com.example.yeseul.movieapp.data.remote.RetrofitClient;
import com.example.yeseul.movieapp.pojo.Movie;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class MovieRemoteDataSource implements MovieDataSource{

    private static MovieRemoteDataSource movieRemoteDataSource = null;

    private MovieRemoteDataSource(){}

    public static MovieRemoteDataSource getInstance(){
        if(movieRemoteDataSource == null){
            movieRemoteDataSource = new MovieRemoteDataSource();
        }
        return movieRemoteDataSource;
    }

    @Override
    public Single<SearchMovieResponse> searchMovies(Map<String, String> request) {

        return RetrofitClient.getClient().create(MovieApi.class)
                .searchMovies(request)
                .subscribeOn(Schedulers.io());
    }

}
