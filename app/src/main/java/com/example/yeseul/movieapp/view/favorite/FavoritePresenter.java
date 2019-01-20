package com.example.yeseul.movieapp.view.favorite;

import com.example.yeseul.movieapp.db.AppDatabase;
import com.example.yeseul.movieapp.db.FavoriteEntity;
import com.example.yeseul.movieapp.view.adapter.AdapterContract;
import com.example.yeseul.movieapp.view.adapter.OnItemClickListener;
import com.example.yeseul.movieapp.view.asynctask.FavoriteSelectAsyncTask;

public class FavoritePresenter implements FavoriteContract.Presenter {
    private FavoriteContract.View view;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<FavoriteEntity> adapterModel;

    public FavoritePresenter(FavoriteContract.View view) {
        this.view = view;
    }

    @Override
    public void setAdapterView(AdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                view.startMovieDetailPage(adapterModel.getItem(position).getLinkUrl());
            }

            @Override
            public void onItemLongClick(int position) {
                // Do noting
            }

            @Override
            public void onFavoriteClick(int position, boolean isExist) {
                // Do noting
            }
        });
    }

    @Override
    public void setAdapterModel(AdapterContract.Model<FavoriteEntity> adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void onViewCreated() {
        // Do noting
    }

    @Override
    public void loadItems(boolean isRefresh) {
        new FavoriteSelectAsyncTask(AppDatabase.getInstance(view.getContext()), list -> adapterModel.addItems(list)).execute();
    }
}
