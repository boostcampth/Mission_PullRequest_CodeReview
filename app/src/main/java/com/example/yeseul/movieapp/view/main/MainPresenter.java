package com.example.yeseul.movieapp.view.main;

import android.annotation.SuppressLint;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.example.yeseul.movieapp.data.source.movie.MovieRepository;
import com.example.yeseul.movieapp.mapper.MovieMapper;
import com.example.yeseul.movieapp.pojo.Movie;
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

    public MainPresenter(MainContract.View view, MovieRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void onViewCreated() {
        // do nothing
    }

    @Override
    public void loadItems(boolean isRefresh) {

        // refresh true 의 경우 초기화
        if (isRefresh){
            currentPage = 0;
            isEndOfPage = false;
            adapterModel.clearItems();
        }

        // 마지막 페이지가 아니고 로딩중 아닌 경우 getMovieList 호출
        if(!isLoading.get() && !isEndOfPage) {
            getMovieList();
        }
    }

    @Override
    public void onSearchButtonClicked(String searchKey) {

        // 이전에 검색한 Key와 다시 요청한 Key값이 경우
        if(this.searchKey.equals(searchKey)){
            view.onSameSearchKeyRequest();
            return;
        }

        this.searchKey = searchKey;
        loadItems(true);
    }

    @Override
    public void setAdapterView(AdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(position ->
                view.startMovieDetailPage(this.adapterModel.getItem(position).getLinkUrl()));
    }

    @Override
    public void setAdapterModel(AdapterContract.Model<Movie> adapterModel) {
        this.adapterModel = adapterModel;
    }

    @SuppressLint("CheckResult")
    private void getMovieList(){

        isLoading.set(true);

        repository.searchMovies(MovieMapper.toRequest(searchKey, PAGE_UNIT, (PAGE_UNIT * currentPage++) + 1))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {

                    // 로딩 flag OFF
                    isLoading.set(false);

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
