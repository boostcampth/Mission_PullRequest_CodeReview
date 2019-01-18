package com.example.yeseul.movieapp.view.main;

import com.example.yeseul.movieapp.pojo.Movie;
import com.example.yeseul.movieapp.view.BasePresenter;
import com.example.yeseul.movieapp.view.BaseView;
import com.example.yeseul.movieapp.view.adapter.AdapterContract;

public interface MainContract {


    interface View extends BaseView {

        void onSearchResultEmpty(String searchKey);

        void startMovieDetailPage(String linkUrl);
    }


    interface Presenter extends BasePresenter {

        // 파라미터 추가
        void onSearchButtonClicked(String searchKey, int searchIndex);

        void setAdapterView(AdapterContract.View adapterView);

        void setAdapterModel(AdapterContract.Model<Movie> adapterModel);
    }
}
