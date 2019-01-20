package com.example.yeseul.movieapp.view.custom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.databinding.ActivityReviewBinding;
import com.example.yeseul.movieapp.databinding.DialogWriteReviewBinding;
import com.example.yeseul.movieapp.pojo.Review;
import com.example.yeseul.movieapp.view.main.MainContract;

public class ReviewDialog extends DialogFragment  {
    private MainContract.Presenter listener;
    private Review review;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        FragmentActivity activity = getActivity();

        review = new Review();
        Bundle rArgs = getArguments();
        review.setLinkUrl(rArgs.getString("link"));
        review.setImageUrl(rArgs.getString("imgUrl"));
        review.setTitle(rArgs.getString("title"));

        DialogWriteReviewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_write_review, null, false);
        binding.setDialog(this);

        //글자수 제한
        binding.tvComment.setFilters(new InputFilter[]{new InputFilter.LengthFilter(60)});

        return new AlertDialog.Builder(activity)
                .setView(binding.getRoot())
                .setPositiveButton("확인", (dialog, which) -> {
                    if(binding.ratingReview.getRating() != 0.0 && !binding.tvComment.getText().toString().isEmpty()) {
                        review.setMyRating(binding.ratingReview.getRating());
                        review.setMyComment(binding.tvComment.getText().toString());
                        listener.onReviewOKButtonClick(review);
                    }
                    else
                        Toast.makeText(activity, "별점과 코멘트를 남겨주세요!", Toast.LENGTH_SHORT).show();
                    })
                .setNegativeButton("취소",
                        (dialog, which) -> dialog.cancel())
                .create();
    }

    public MainContract.Presenter getListener(){
        return listener;
    }
    public void setListener(MainContract.Presenter listener){
        this.listener=listener;
    }
}
