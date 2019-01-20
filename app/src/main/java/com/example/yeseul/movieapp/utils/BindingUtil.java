package com.example.yeseul.movieapp.utils;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import okhttp3.Interceptor;

public class BindingUtil {

    @BindingConversion
    public static int setVisibility(boolean isVisible){

        return isVisible ? View.VISIBLE : View.GONE;
    }

    /*
     * holder_movie_item.xml 에서 rating 과 결합. item 이 변경되면 그에 맞게 data change*/
    @BindingAdapter({"rating"})
    public static void setRating(RatingBar ratingBar, String userRating){

        // 소수점 버리기
        int rating = (int) Float.parseFloat(userRating);

        ratingBar.setRating((float) rating / 2);
    }

    /*
     * holder_movie_item.xml 의 HTML Text 들과 결합. */
    @BindingAdapter({"boldText"})
    public static void setBoldText(TextView textView, String text){

        textView.setText(Html.fromHtml(text));
    }

}
