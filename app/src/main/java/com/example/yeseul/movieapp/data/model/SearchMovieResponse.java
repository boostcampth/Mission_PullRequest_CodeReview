package com.example.yeseul.movieapp.data.model;

import com.example.yeseul.movieapp.pojo.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchMovieResponse {
    @SerializedName("total")
    @Expose
    private Integer total;

    @SerializedName("start")
    @Expose
    private Integer start;

    @SerializedName("display")
    @Expose
    private Integer display;

    @SerializedName("items")
    @Expose
    private List<Movie> movieList = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
