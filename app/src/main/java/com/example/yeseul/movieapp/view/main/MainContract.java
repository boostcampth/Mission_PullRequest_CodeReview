package com.example.yeseul.movieapp.view.main;

import com.example.yeseul.movieapp.data.model.MovieSortStrategy;
import com.example.yeseul.movieapp.pojo.Movie;
import com.example.yeseul.movieapp.view.BasePresenter;
import com.example.yeseul.movieapp.view.BaseView;
import com.example.yeseul.movieapp.view.adapter.AdapterContract;

/**
 * View - Presenter 는 1:1 관계
 * 각 객체의 공통 기능 설정
 */
public interface MainContract {

    interface View extends BaseView {

        /*
         * 검색 결과가 없을 때*/
        void onSearchResultEmpty(String searchKey);

        /*
         * 영화를 선택했을 때 다음 화면으로 넘어가는 기능 */
        void startMovieDetailPage(String linkUrl);
    }

    interface Presenter extends BasePresenter {

        /* View 에서 발생하는 이벤트 처리는 Presenter 에 위임*/
        void onSearchButtonClicked(String searchKey);

        /* Presenter 에서 Adapter 를 가지고 View 갱신함 */
        void setAdapterView(AdapterContract.View adapterView);

        /* Presenter 에서 Adapter 의 데이터를 조작함 */
        void setAdapterModel(AdapterContract.Model<Movie> adapterModel);

        /* Network 상태를 Presenter 의 Observer 를 이용하여 관찰 */
        void setNetworkStatus(boolean currentStatus);

        /* 정렬 상태가 변경되었을 경우 */
        void onOrderSelectionChanged(MovieSortStrategy sortMethod);
    }
}
