package com.example.yeseul.movieapp.data.model;

import android.os.Build;

import com.example.yeseul.movieapp.pojo.Movie;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieSortByLatestPubDate implements MovieSortStrategy {
    @Override
    public List<Movie> sort(List<Movie> list) {
        if(Build.VERSION_CODES.N <= Build.VERSION.SDK_INT) {
            list = list.stream().sorted(Comparator.comparing(Movie::getPubDate).reversed()).collect(Collectors.toList());
        } else {
            Collections.sort(list, (o1, o2) -> (o2.getPubDate().compareTo(o1.getPubDate())));
        }
        return list;
    }
}
