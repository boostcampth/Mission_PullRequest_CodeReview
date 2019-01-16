package com.example.yeseul.movieapp.data.model;

public class RecentSearchWord {
    private String movieName;
    private String date;

    public RecentSearchWord(String movieName, String date) {
        this.movieName = movieName;
        this.date = date;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
