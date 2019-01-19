package com.example.yeseul.movieapp.data.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yeseul.movieapp.pojo.Movie;

import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper {
    private static final String DB_NAME = "movie";
    private static final int DB_VERSION = 1;
    public static MyDataBase myDataBase = null;

    private MyDataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public static MyDataBase getInstance(Context context) {
        if (myDataBase == null) {
            myDataBase = new MyDataBase(context);
        }
        return myDataBase;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS movie(id INTEGER PRIMARY KEY," +
                " title TEXT not null," +
                " linkUrl TEXT ," +
                " imageUrl TEXT ," +
                " subtitle TEXT ," +
                " pubDate TEXT ," +
                " director TEXT ," +
                " actor TEXT ," +
                " userRating TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertMovie(Movie movie) {
        //읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT OR IGNORE INTO movie VALUES(null,?,?,?,?,?,?,?,?)",
                new String[]{movie.getTitle(), movie.getLinkUrl(), movie.getImageUrl(), movie.getSubtitle(),
                        movie.getPubDate(), movie.getDirector(), movie.getActor(), movie.getUserRating()});
        db.close();
    }

    public void deleteMoive(String title){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM movie WHERE title=?",new String[]{title});
        db.close();
    }

    public boolean findMovie(String title) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT title FROM movie WHERE title=?", new String[]{title});
        int rowNum = cursor.getCount();
        if (rowNum != 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Movie> getMovieList() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM movie", null);
        ArrayList<Movie> moives = new ArrayList<>();
        while (cursor.moveToNext()) {
            moives.add(new Movie(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8)));
        }
        return moives;
    }
}
