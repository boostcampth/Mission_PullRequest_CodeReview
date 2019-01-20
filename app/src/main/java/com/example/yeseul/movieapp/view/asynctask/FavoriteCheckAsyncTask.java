package com.example.yeseul.movieapp.view.asynctask;

import android.os.AsyncTask;

import com.example.yeseul.movieapp.db.AppDatabase;
import com.example.yeseul.movieapp.db.FavoriteEntity;
import com.example.yeseul.movieapp.pojo.Movie;

import java.util.List;

/**
 * DB 내용과 비교 후 check 작업 수행
 */
public class FavoriteCheckAsyncTask extends AsyncTask<List<Movie>, Void, List<Movie>> {
    private AppDatabase db;
    private ReceiveDataCallback callback;

    public interface ReceiveDataCallback {
        void onReceiveData(List<Movie> list);
    }

    public FavoriteCheckAsyncTask(AppDatabase db, ReceiveDataCallback callback) {
        this.db = db;
        this.callback = callback;
    }

    @SafeVarargs
    @Override
    protected final List<Movie> doInBackground(List<Movie>... movieList) {
        List<FavoriteEntity> list = db.movieDao().selectFavoriteMovies();

        for (Movie movie : movieList[0]) {
            for (FavoriteEntity data : list) {
                if (android.text.Html.fromHtml(movie.getTitle()).toString().equals(data.getTitle())
                        && movie.getDirector().equals(data.getDirector())) {
                    movie.setChecked(true);
                }
            }
        }

        return movieList[0];
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);

        callback.onReceiveData(movies);
    }
}
