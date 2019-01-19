package com.example.yeseul.movieapp.data.source.movie;

import com.example.yeseul.movieapp.data.model.SearchMovieResponse;
import com.example.yeseul.movieapp.pojo.Movie;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

/*
 * Repository 에서 사용할 기능 선언 */
public interface MovieDataSource {

    /* API 가 유일한 데이터를 반환하는 경우 Single 이 유용하다.(한 번만 데이터를 발행) */
    Single<SearchMovieResponse> searchMovies(Map<String, String> request);
}
