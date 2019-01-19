package com.example.yeseul.movieapp.view.bookmark;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.data.source.movie.MovieRepository;
import com.example.yeseul.movieapp.databinding.ActivityBookmarkBinding;
import com.example.yeseul.movieapp.view.BaseActivity;
import com.example.yeseul.movieapp.view.main.MovieListAdapter;

public class BookMarkActivity extends BaseActivity<ActivityBookmarkBinding, BookMarkPresenter> implements BookMarkContract.View {

    private MovieListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bookmark;
    }

    @Override
    protected BookMarkPresenter getPresenter() {
        return new BookMarkPresenter(this, MovieRepository.getInstance(this));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new MovieListAdapter(this);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        binding.setPresenter(presenter);
        initView();
        presenter.onViewCreated();
    }

    private void initView(){
        // ActionBar title 설정
        getSupportActionBar().setTitle(R.string.bookmark);

        // recyclerView 생성 및 설정
        binding.recyclerMovie.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.recyclerMovie.setAdapter(adapter);
        binding.recyclerMovie.setEmptyView(binding.emptyView);
        binding.recyclerMovie.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }

    /**
     * 검색 결과가 없는 경우 presenter 에 의해 호출됨
     */
    @Override
    public void onSearchResultEmpty() {
        binding.emptyView.setText(getString(R.string.bookmark_empty));
    }

    /**
     * 영화 상세 정보 URL 로 연결
     */
    @Override
    public void startMovieDetailPage(String linkUrl) {
        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                .setToolbarColor(getResources().getColor(R.color.colorPrimary))
                .build();

        customTabsIntent.launchUrl(this, Uri.parse(linkUrl));
    }

    /**
     * 북마크 다이얼로그를 띄움
     */
    @Override
    public void showBookMarkDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.bookmark));
        builder.setMessage(R.string.bookmark_checked);
        builder.setPositiveButton(R.string.positive_button, (dialogInterface, i) -> {
            presenter.onDialogItemClicked(position);
            Toast.makeText(this, R.string.bookmark_change, Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton(R.string.negative_button, null);
        builder.show();
    }
}
