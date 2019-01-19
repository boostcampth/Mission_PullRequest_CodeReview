package com.example.yeseul.movieapp.view.bookmark;

import com.example.yeseul.movieapp.data.source.movie.MovieRepository;
import com.example.yeseul.movieapp.pojo.Movie;
import com.example.yeseul.movieapp.view.adapter.AdapterContract;

import java.util.List;

public class BookMarkPresenter implements BookMarkContract.Presenter {

    private BookMarkContract.VIew view;
    private MovieRepository repository;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<Movie> adapterModel;


    public BookMarkPresenter(BookMarkContract.VIew view, MovieRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void onDialogItemClicked(int position) {
        repository.deleteMoive(this.adapterModel.getItem(position).getTitle());
        adapterModel.removeItem(position);

        //즐겨찾기한 목록이 없는 경우
        if(adapterModel.getItemCount()==0){
            view.onSearchResultEmpty();
        }
    }

    @Override
    public void setAdapterView(AdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(position -> view.startMovieDetailPage(this.adapterModel.getItem(position).getLinkUrl()));
        this.adapterView.setOnItemLongClickListener(position -> {
            view.showBookMarkDialog(position);
        });
    }

    @Override
    public void setAdapterModel(AdapterContract.Model<Movie> adapterModel) {
        this.adapterModel=adapterModel;
    }

    @Override
    public void onViewCreated() {
        List<Movie> movieList= repository.getMovieList();

        if(movieList.size()==0){ //저장 된 목록이 없는경우
            view.onSearchResultEmpty();
        }

        //어댑터에 리스트 추가
        adapterModel.addItems(movieList);
    }

    @Override
    public void loadItems(boolean isRefresh) {

    }
}
