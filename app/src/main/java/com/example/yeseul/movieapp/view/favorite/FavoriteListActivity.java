package com.example.yeseul.movieapp.view.favorite;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.databinding.ActivityFavoriteListBinding;
import com.example.yeseul.movieapp.view.BaseActivity;

public class FavoriteListActivity extends BaseActivity<ActivityFavoriteListBinding, FavoritePresenter> implements FavoriteContract.View {

    private FavoriteListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new FavoriteListAdapter(this);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        initView();

        presenter.loadItems(false);
    }

    private void initView() {
        binding.recyclerMovie.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerMovie.setAdapter(adapter);
        binding.recyclerMovie.setNestedScrollingEnabled(false);
        binding.recyclerMovie.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_favorite_list;
    }

    @Override
    protected FavoritePresenter getPresenter() {
        return new FavoritePresenter(this);
    }

    /**
     * 영화 상세 정보 URL 로 연결 */
    @Override
    public void startMovieDetailPage(String linkUrl) {

        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                .setToolbarColor(getResources().getColor(R.color.colorPrimary))
                .build();

        customTabsIntent.launchUrl(this, Uri.parse(linkUrl));
    }

    /**
     * 영화 상세 정보 URL 공유 */
    @Override
    public void shareMovieDetail(String title, String linkUrl) {

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, linkUrl);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_message)));
    }

    @Override
    public Context getContext() {
        return this;
    }
}
