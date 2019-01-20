package com.example.yeseul.movieapp.view.asynctask;

import android.os.AsyncTask;

import com.example.yeseul.movieapp.db.AppDatabase;
import com.example.yeseul.movieapp.db.FavoriteEntity;

import java.util.List;

/**
 * DB 쿼리 작업 수행
 */
public class FavoriteSelectAsyncTask extends AsyncTask<Void, Void, List<FavoriteEntity>> {
    private AppDatabase db;
    private DataCallback callback;

    public interface DataCallback {
        void dataCallback(List<FavoriteEntity> list);
    }

    public FavoriteSelectAsyncTask(AppDatabase db, DataCallback callback) {
        this.db = db;
        this.callback = callback;
    }

    @Override
    protected List<FavoriteEntity> doInBackground(Void... voids) {
        return db.movieDao().selectFavoriteMovies();
    }

    @Override
    protected void onPostExecute(List<FavoriteEntity> favoriteEntities) {
        super.onPostExecute(favoriteEntities);

        callback.dataCallback(favoriteEntities);
    }
}
