package com.example.yeseul.movieapp.view.asynctask;

import android.os.AsyncTask;

import com.example.yeseul.movieapp.db.AppDatabase;
import com.example.yeseul.movieapp.db.FavoriteEntity;

public class FavoriteQueryAsyncTask extends AsyncTask<FavoriteEntity, Void, Boolean> {
    private static final int QUERY_INSERT = 0;
    private static final int QUERY_DELETE = 1;

    private FavoriteQueryAsyncTask(AppDatabase db, int option) {
        this.db = db;
        this.option = option;
    }

    public static FavoriteQueryAsyncTask insert(AppDatabase db) {
        return new FavoriteQueryAsyncTask(db, QUERY_INSERT);
    }

    public static FavoriteQueryAsyncTask delete(AppDatabase db) {
        return new FavoriteQueryAsyncTask(db, QUERY_DELETE);
    }

    private AppDatabase db;
    private int option;

    @Override
    protected Boolean doInBackground(FavoriteEntity... movies) {
        if (option == QUERY_INSERT) {
            db.movieDao().insertFavoriteMovie(movies[0]);
        } else if (option == QUERY_DELETE) {
            db.movieDao().deleteFavoriteMovie(movies[0]);
        }
        return true;
    }
}
