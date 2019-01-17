package com.example.yeseul.movieapp.utils;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.text.Html;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

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
        if(text != null)
            textView.setText(Html.fromHtml(text));
    }

    @BindingAdapter({"bind:visible"})
    public static void setVisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
