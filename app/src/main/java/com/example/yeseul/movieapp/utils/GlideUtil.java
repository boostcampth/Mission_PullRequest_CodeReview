package com.example.yeseul.movieapp.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.yeseul.movieapp.R;

public class GlideUtil {

    @BindingAdapter({"image"})
    public static void loadImage(ImageView imageView, String url){

        if (url == null) return;

        GlideApp.with(imageView).load(url).into(imageView);
    }

    @BindingAdapter({"imageCenterCrop"})
    public static void loadImageCenterCrop(ImageView imageView, String url){

        if (url == null) return;

        GlideApp.with(imageView).load(url).placeholder(R.drawable.img_boostcamp).centerCrop().into(imageView);
    }

}
