package com.example.yeseul.movieapp;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yeseul.movieapp.data.model.RecentSearchWord;

import java.util.ArrayList;

public class RecentSearchWordVM extends BaseObservable {
    private RecentSearchWord recentSearchWord;

    public RecentSearchWordVM(RecentSearchWord recentSearchWord) {
        this.recentSearchWord = recentSearchWord;
    }

    public RecentSearchWord getRecentSearchWord() {
        return recentSearchWord;
    }

    @BindingAdapter(value ="date")
    static public void setDateText(TextView textView, String date){
        Log.d("onFocus",date);
        textView.setText(date);
    }
}
