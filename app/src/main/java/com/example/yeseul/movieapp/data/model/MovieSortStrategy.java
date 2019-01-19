package com.example.yeseul.movieapp.data.model;

import android.annotation.TargetApi;
import android.os.Build;

import com.example.yeseul.movieapp.pojo.Movie;

import java.util.List;

public interface MovieSortStrategy {

    public static final int DICTIONARY_ORDER = 0;
    public static final int LATEST_ORDER = 1;
    public static final int OLDEST_ORDER = 2;
    public static final int HIGHEST_RATING_ORDER = 3;
    public static final int LOWEST_RATING_ORDER = 4;

    List<Movie> sort(List<Movie> list);
}
