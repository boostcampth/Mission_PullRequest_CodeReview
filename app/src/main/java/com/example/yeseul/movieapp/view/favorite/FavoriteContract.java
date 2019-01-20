package com.example.yeseul.movieapp.view.favorite;

import com.example.yeseul.movieapp.db.FavoriteEntity;
import com.example.yeseul.movieapp.view.BasePresenter;
import com.example.yeseul.movieapp.view.BaseView;
import com.example.yeseul.movieapp.view.adapter.AdapterContract;

public interface FavoriteContract {
    interface View extends BaseView {
        void startMovieDetailPage(String linkUrl);
        void shareMovieDetail(String title, String linkUrl);
    }

    // 제네릭 타입 제한때문에 쓸데없는 것을 넣어야한다.
    // 수정 필요
    interface Presenter extends BasePresenter {
        void setAdapterView(AdapterContract.View adapterView);
        void setAdapterModel(AdapterContract.Model<FavoriteEntity> adapterModel);
    }
}
