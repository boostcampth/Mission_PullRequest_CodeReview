package com.example.yeseul.movieapp.db;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import com.example.yeseul.movieapp.pojo.Movie;

@Entity(tableName = "favorite-movie", primaryKeys = {"title", "director"})
public class FavoriteEntity {
    @NonNull
    private String title;
    private String linkUrl;
    private String imageUrl;
    private String subtitle;
    private String pubDate;
    @NonNull
    private String director;
    private String actor;
    private String userRating;

    public FavoriteEntity(Movie movie) {
        this.title = movie.getTitle();
        this.linkUrl = movie.getLinkUrl();
        this.imageUrl = movie.getImageUrl();
        this.subtitle = movie.getSubtitle();
        this.pubDate = movie.getPubDate();
        this.director = movie.getDirector();
        this.actor = movie.getActor();
        this.userRating = movie.getUserRating();
    }

    public FavoriteEntity(String title, String linkUrl, String imageUrl, String subtitle, String pubDate, String director, String actor, String userRating) {
        this.title = title;
        this.linkUrl = linkUrl;
        this.imageUrl = imageUrl;
        this.subtitle = subtitle;
        this.pubDate = pubDate;
        this.director = director;
        this.actor = actor;
        this.userRating = userRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }
}
