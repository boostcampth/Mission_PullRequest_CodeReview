package com.example.yeseul.movieapp.view.review;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import com.example.yeseul.movieapp.databinding.ActivityReviewBinding;
import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.view.BaseActivity;

public class ReviewActivity extends BaseActivity<ActivityReviewBinding, ReviewPresenter> implements ReviewContract.View {

    private ReviewListAdapter adapter;
    private static Context context;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_review;
    }

    @Override
    protected ReviewPresenter getPresenter() {
        return new ReviewPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();

        adapter = new ReviewListAdapter(this);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        binding.setPresenter(presenter);
        initView();
        presenter.onViewCreated();
    }

    @Override
    public void onReviewListEmpty() {
        String displayString = getString(R.string.review_empty);
        binding.emptyView.setText(Html.fromHtml(displayString));
    }

    private void initView(){

        binding.recyclerReview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.recyclerReview.setAdapter(adapter);
        binding.recyclerReview.setEmptyView(binding.emptyView);
        binding.recyclerReview.setNestedScrollingEnabled(false);
        binding.recyclerReview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        presenter.loadItems(true);
    }

    public static Context getContext() {
        return context;
    }
}
