package com.example.yeseul.movieapp.view.main;

import android.content.Context;

import com.example.yeseul.movieapp.pojo.Movie;
import com.example.yeseul.movieapp.pojo.Review;
import com.example.yeseul.movieapp.view.BasePresenter;
import com.example.yeseul.movieapp.view.BaseView;
import com.example.yeseul.movieapp.view.adapter.AdapterContract;

public interface MainContract {

    interface View extends BaseView {

        void onSearchResultEmpty(String searchKey);

        void startMovieDetailPage(String linkUrl);

        void startReviewPage(Movie movie);

    }

    interface Presenter extends BasePresenter {

        void onSearchButtonClicked(String searchKey);

        void setAdapterView(AdapterContract.View adapterView);

        void setAdapterModel(AdapterContract.Model<Movie> adapterModel);

        void onReviewOKButtonClick(Review review);
    }
}
