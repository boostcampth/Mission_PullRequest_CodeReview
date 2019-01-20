package com.example.yeseul.movieapp.data.model;

import android.annotation.TargetApi;
import android.os.Build;

import com.example.yeseul.movieapp.pojo.Movie;

import java.util.List;

public interface MovieSortStrategy {

    List<Movie> sort(List<Movie> list);
}
