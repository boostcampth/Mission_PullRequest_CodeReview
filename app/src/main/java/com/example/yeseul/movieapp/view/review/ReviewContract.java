package com.example.yeseul.movieapp.view.review;

import com.example.yeseul.movieapp.pojo.Review;
import com.example.yeseul.movieapp.view.BasePresenter;
import com.example.yeseul.movieapp.view.BaseView;
import com.example.yeseul.movieapp.view.adapter.AdapterContract;

public interface ReviewContract {
    interface View extends BaseView {

        void onReviewListEmpty();

        void notifyDataSetChanged();
    }

    interface Presenter extends BasePresenter {

        void setAdapterView(AdapterContract.View adapterView);

        void setAdapterModel(AdapterContract.Model<Review> adapterModel);
    }
}
