package com.example.yeseul.movieapp.view.review;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.databinding.HolderReviewItemBinding;
import com.example.yeseul.movieapp.pojo.Review;
import com.example.yeseul.movieapp.view.adapter.BaseRecyclerAdapter;

public class ReviewListAdapter extends BaseRecyclerAdapter<Review, ReviewListAdapter.ViewHolder> {

    public ReviewListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(ReviewListAdapter.ViewHolder holder, int position) {
        holder.binding.setReview(itemList.get(position));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_review_item, parent, false);
        return new ReviewListAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        HolderReviewItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
