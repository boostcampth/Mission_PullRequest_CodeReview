package com.example.yeseul.movieapp.view.review;

import com.example.yeseul.movieapp.data.sqlite.DatabaseHelper;
import com.example.yeseul.movieapp.pojo.Review;
import com.example.yeseul.movieapp.view.adapter.AdapterContract;
import com.example.yeseul.movieapp.view.main.MainContract;

import java.util.List;

public class ReviewPresenter implements ReviewContract.Presenter {
    private ReviewContract.View view;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<Review> adapterModel;
    private DatabaseHelper db;

    public ReviewPresenter(ReviewContract.View view) {
        this.view = view;
    }
    @Override
    public void setAdapterView(AdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setAdapterModel(AdapterContract.Model<Review> adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void onViewCreated() {
        // do nothing
    }

    @Override
    public void loadItems(boolean isRefresh) {
        db = new DatabaseHelper(ReviewActivity.getContext());
        List<Review> reviewList = db.getAllReviews();
        if (reviewList.size() == 0)
            view.onReviewListEmpty();

        adapterModel.addItems(reviewList);
    }
}
