package com.example.yeseul.movieapp.utils;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import okhttp3.Interceptor;

public class BindingUtil {

    @BindingConversion
    public static int setVisibility(boolean isVisible){

        return isVisible ? View.VISIBLE : View.GONE;
    }

    @BindingAdapter({"rating"})
    public static void setRating(RatingBar ratingBar, String userRating){

        // 소수점 버리기
        int rating = (int) Float.parseFloat(userRating);

        ratingBar.setRating((float) rating / 2);
    }

    @BindingAdapter({"boldText"})
    public static void setBoldText(TextView textView, String text){

        textView.setText(Html.fromHtml(text));
    }

    @BindingAdapter({"check"})
    public static void setCheck(ImageView imageView, Boolean check) {
        imageView.setSelected(check);
    }
}
