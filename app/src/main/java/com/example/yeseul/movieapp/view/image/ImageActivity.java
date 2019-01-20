package com.example.yeseul.movieapp.view.image;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.data.source.movie.MovieRepository;
import com.example.yeseul.movieapp.databinding.ActivityImageBinding;
import com.example.yeseul.movieapp.view.BaseActivity;

public class ImageActivity extends BaseActivity<ActivityImageBinding, ImagePresenter> implements ImageContract.View {

    public static String EXTRA_IMAGE_POSITION = "EXTRA_IMAGE_POSITION";

    private ImageAdapter adapter;

    private int position;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image;
    }

    @Override
    protected ImagePresenter getPresenter() {
        return new ImagePresenter(this, MovieRepository.getInstance());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getIntent().getIntExtra(EXTRA_IMAGE_POSITION, 0);

        // 팝업창 레이아웃 크기 변경
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);

        // 어댑터 생성
        adapter = new ImageAdapter(this, null);

        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        initView();

        presenter.onViewCreated();
    }

    private void initView() {

        // 리사이클러뷰 생성
        binding.recyclerImage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerImage.setAdapter(adapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerImage);

        // 터치 발생한 곳 position 으로 넘기기
        binding.recyclerImage.scrollToPosition(position);

        // 닫기 버튼 리스너 등록
        binding.btnClose.setOnClickListener(v -> finish());
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

}