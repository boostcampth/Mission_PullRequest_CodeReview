package com.example.yeseul.movieapp.view.image;

import com.example.yeseul.movieapp.pojo.Movie;
import com.example.yeseul.movieapp.view.BasePresenter;
import com.example.yeseul.movieapp.view.BaseView;
import com.example.yeseul.movieapp.view.adapter.AdapterContract;

public interface ImageContract {

    interface View extends BaseView {

        void startMovieDetailPage(String linkUrl);
    }

    interface Presenter extends BasePresenter {

        void setAdapterView(AdapterContract.View adapterView);

        void setAdapterModel(AdapterContract.Model<Movie> adapterModel);
    }
}
