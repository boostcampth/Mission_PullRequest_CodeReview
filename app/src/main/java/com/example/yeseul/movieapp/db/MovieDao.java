package com.example.yeseul.movieapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteMovie(FavoriteEntity... movie);

    @Delete
    void deleteFavoriteMovie(FavoriteEntity movie);

    @Query("SELECT * FROM `favorite-movie`")
    List<FavoriteEntity> selectFavoriteMovies();
}
