package com.example.yeseul.movieapp.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yeseul.movieapp.pojo.Review;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movie";

    public static final String TABLE_NAME = "REVIEW";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IMAGEURL = "image_url";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_MYRATING = "my_rating";
    public static final String COLUMN_MYCOMMENT = "my_comment";
    public static final String COLUMN_LINK = "link";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_IMAGEURL + " TEXT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_MYRATING + " REAL,"
                + COLUMN_MYCOMMENT + " TEXT,"
                + COLUMN_LINK + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertReview(Review review) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGEURL, review.getImageUrl());
        values.put(COLUMN_TITLE, review.getTitle());
        values.put(COLUMN_MYRATING, review.getMyRating());
        values.put(COLUMN_MYCOMMENT, review.getMyComment());
        values.put(COLUMN_LINK, review.getLinkUrl());

        db.insert(TABLE_NAME, null, values);

        db.close();
    }


    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Review review = new Review();
                review.setImageUrl(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEURL)));
                review.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                review.setMyRating(cursor.getFloat(cursor.getColumnIndex(COLUMN_MYRATING)));
                review.setMyComment(cursor.getString(cursor.getColumnIndex(COLUMN_MYCOMMENT)));
                review.setLinkUrl(cursor.getString(cursor.getColumnIndex(COLUMN_LINK)));

                reviews.add(review);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return reviews;
    }


    public void updateReview(Review review) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MYRATING, review.getMyRating());
        values.put(COLUMN_MYCOMMENT, review.getMyComment());

        db.update(TABLE_NAME, values, COLUMN_LINK + " = ?",
                new String[]{String.valueOf(review.getLinkUrl())});
    }

    public void deleteReview(String link) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_LINK + " = ?",
                new String[]{String.valueOf(link)});
        db.close();
    }

    public boolean isReviewed(String link) {
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_LINK + "=?" ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{link});
        int count = cursor.getCount();
        cursor.close();

        return count > 0 ? true : false;
    }
}