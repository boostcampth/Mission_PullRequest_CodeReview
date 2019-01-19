package com.example.yeseul.movieapp.view;

/*
 * 모든 Presenter 가 공통으로 가져야하는 함수들 선언 */
public interface BasePresenter {

    void onViewAttached();

    void onViewDetached();

    void loadItems(boolean isRefresh);
}
