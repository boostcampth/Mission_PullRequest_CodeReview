package com.example.yeseul.movieapp.data.model;

import android.os.Build;

import com.example.yeseul.movieapp.pojo.Movie;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieSortByHighestRating implements MovieSortStrategy {
    @Override
    public List<Movie> sort(List<Movie> list) {
        if(Build.VERSION_CODES.N <= Build.VERSION.SDK_INT) {
            list = list.stream().
                    sorted((o1, o2) -> Double.compare(Double.parseDouble(o2.getUserRating()), Double.parseDouble(o1.getUserRating()))).
                    collect(Collectors.toList());
        } else {
            Collections.sort(list, (o1, o2) -> (Double.compare(Double.parseDouble(o2.getUserRating()), Double.parseDouble(o1.getUserRating()))));
        }
        return list;
    }
}
