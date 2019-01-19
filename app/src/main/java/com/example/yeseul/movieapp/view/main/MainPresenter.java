package com.example.yeseul.movieapp.view.main;

import android.annotation.SuppressLint;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;

import com.example.yeseul.movieapp.data.model.MovieSortStrategy;
import com.example.yeseul.movieapp.data.source.movie.MovieRepository;
import com.example.yeseul.movieapp.mapper.MovieMapper;
import com.example.yeseul.movieapp.pojo.Movie;
import com.example.yeseul.movieapp.utils.NetworkStatusCheckUtil;
import com.example.yeseul.movieapp.view.adapter.AdapterContract;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainPresenter implements MainContract.Presenter {

    public final ObservableBoolean isLoading = new ObservableBoolean(false); // 로딩 중 flag 바인딩

    private MainContract.View view;
    private MovieRepository repository;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<Movie> adapterModel;

    private String searchKey = ""; // 검색 키워드
    private final int PAGE_UNIT = 20; // 한번에 가져올 데이터 개수
    private int currentPage = 0; // 현재 페이지 index
    private boolean isEndOfPage = false; // 페이지 끝 flag

    private boolean isNetworkConnected = false;

    public MainPresenter(MainContract.View view, MovieRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    /* BasePresenter */
    @Override
    public void onViewAttached() {
        // 네트워크 초기 상태 설정
        isNetworkConnected = NetworkStatusCheckUtil.checkCurrentNetworkStatus((MainActivity)view);
    }

    @Override
    public void onViewDetached() {
        // do Something
    }

    @Override
    public void setNetworkStatus(boolean currentStatus) {
        this.isNetworkConnected = currentStatus;
    }

    /* BasePresenter */
    @Override
    public void loadItems(boolean isRefresh) {

        // refresh true 의 경우 초기화
        if (isRefresh){
            currentPage = 0;
            isEndOfPage = false;
            adapterModel.clearItems();
        }

        // 인터넷 연결이 안되어있는 경우
        if(!isNetworkConnected) {
            view.makeToast("인터넷 상태를 확인해 주세요 !");
            return;
        }

        // 마지막 페이지가 아니고 로딩중 아닌 경우 getMovieList 호출
        if(!isLoading.get() && !isEndOfPage) {
            getMovieList();
        }
    }

    /* MainContract.Presenter */
    @Override
    public void onSearchButtonClicked(String searchKey) {
        this.searchKey = searchKey;
        loadItems(true);
    }

    /* MainContract.Presenter */
    @Override
    public void setAdapterView(AdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(position ->
                view.startMovieDetailPage(this.adapterModel.getItem(position).getLinkUrl()));
    }

    /* MainContract.Presenter */
    @Override
    public void setAdapterModel(AdapterContract.Model<Movie> adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void onOrderSelectionChanged(MovieSortStrategy sortStrategy) {
        if(sortStrategy != null) {
            adapterModel.updateItems(sortStrategy.sort(adapterModel.getItemList()));
        }
    }

    @SuppressLint("CheckResult")
    private void getMovieList(){

        if(!isNetworkConnected) {
            view.makeToast("인터넷 상태를 확인해 주세요 !");
            return;
        }

        // RXJava 특성상 Subscribe 하고 있어서
        // activity_movie 의 progressBar Visibility 가 변경된다.
        isLoading.set(true);

        // repository 에 접근하는 객체는 Singleton 으로 받아옴
        // searchMovies 의 return value 가 Single 객체이므로 딱 한번만 데이터를 발행
        // subscribe 에서 발행된 데이터를 Consumer 가 처리하는 구조
        repository.searchMovies(MovieMapper.toRequest(searchKey, PAGE_UNIT, (PAGE_UNIT * currentPage++) + 1))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    // 받아온 SearchMovieResponse 는 어디서 처리하는거지

                    // 로딩 flag OFF (Observing)
                    isLoading.set(false);

                    // 받아온 결과 목록
                    List<Movie> movieList = response.getMovieList();

                    if(movieList.size() == 0){ // 검색 결과가 없는 경우
                        // 페이지 끝 flag ON
                        isEndOfPage = true;
                        // 뷰에 알리기
                        view.onSearchResultEmpty(searchKey);
                        return;
                    }

                    if ((PAGE_UNIT * currentPage) >= response.getTotal() || movieList.size() < PAGE_UNIT) { // 마지막 페이지거나 검색 결과가 10개 미만
                        // 페이지 끝 flag ON
                        isEndOfPage = true;
                    }

                    // 선택한 정렬 옵션으로 정렬

                    // 어댑터에 리스트 추가
                    adapterModel.addItems(movieList);

                }, error -> {

                    // 로딩 flag OFF
                    isLoading.set(false);
                    // 페이지 끝 flag ON
                    isEndOfPage = true;
                    // 뷰에 알리기
                    view.onSearchResultEmpty(searchKey);
                });
    }



}
