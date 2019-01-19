package com.example.yeseul.movieapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    /*
     * SerializedName  : Gson 을 이용해 Json 에서 Object 로 객체를 변경시킬 때 Json 에 등록된 이름과 연결지어 준다.
     * Expose          : Expose Annotation 이 존재하면 Gson 을 이용할 때 해당 변수는 노출시키지 않게 됨
     *
     * */

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("link")
    @Expose
    private String linkUrl;

    @SerializedName("image")
    @Expose
    private String imageUrl;

    @SerializedName("subtitle")
    @Expose
    private String subtitle;

    @SerializedName("pubDate")
    @Expose
    private String pubDate;

    @SerializedName("director")
    @Expose
    private String director;

    @SerializedName("actor")
    @Expose
    private String actor;

    @SerializedName("userRating")
    @Expose
    private String userRating;

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
