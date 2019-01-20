package com.example.yeseul.movieapp.view.image;

import com.example.yeseul.movieapp.data.source.movie.MovieRepository;
import com.example.yeseul.movieapp.pojo.Movie;
import com.example.yeseul.movieapp.view.adapter.AdapterContract;

public class ImagePresenter implements ImageContract.Presenter {

    private ImageContract.View view;
    private MovieRepository repository;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<Movie> adapterModel;

    public ImagePresenter(ImageContract.View view, MovieRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void setAdapterView(AdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(position -> {
            this.view.startMovieDetailPage(this.adapterModel.getItem(position).getLinkUrl());
        });
    }

    @Override
    public void setAdapterModel(AdapterContract.Model<Movie> adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void onViewCreated() {

        loadItems(true);
    }

    @Override
    public void loadItems(boolean isRefresh) {

        adapterModel.addItems(repository.getSavedMovieList());
    }
}
