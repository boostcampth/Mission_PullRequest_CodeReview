package com.example.yeseul.movieapp.view.bookmark;

import com.example.yeseul.movieapp.pojo.Movie;
import com.example.yeseul.movieapp.view.BasePresenter;
import com.example.yeseul.movieapp.view.BaseView;
import com.example.yeseul.movieapp.view.adapter.AdapterContract;

public interface BookMarkContract {

    interface View extends BaseView{

        void onSearchResultEmpty();

        void startMovieDetailPage(String linkUrl);

        void showBookMarkDialog(int position);
    }

    interface Presenter extends BasePresenter{

        void onDialogItemClicked(int position);

        void setAdapterView(AdapterContract.View adapterView);

        void setAdapterModel(AdapterContract.Model<Movie> adapterModel);
    }
}

