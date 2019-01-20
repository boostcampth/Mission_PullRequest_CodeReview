package com.example.yeseul.movieapp.view.main;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;

import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.data.model.MovieSortByDictionary;
import com.example.yeseul.movieapp.data.model.MovieSortStrategy;
import com.example.yeseul.movieapp.data.model.MovieStrategyFactory;
import com.example.yeseul.movieapp.data.source.movie.MovieRepository;
import com.example.yeseul.movieapp.databinding.ActivityMovieBinding;
import com.example.yeseul.movieapp.utils.KeyboardUtil;
import com.example.yeseul.movieapp.utils.NetworkStatusCheckUtil;
import com.example.yeseul.movieapp.view.BaseActivity;


public class MainActivity extends BaseActivity<ActivityMovieBinding, MainPresenter> implements MainContract.View {

    private MovieListAdapter adapter;

    private BroadcastReceiver mNetworkStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Log.e("Test", intent.getAction() + " state : " + NetworkStatusCheckUtil.checkCurrentNetworkStatus(MainActivity.this));
            // 네트워크 상태 변경 Action 이 발생한 경우
            if(intent.getAction().equals(NetworkStatusCheckUtil.NETWORK_STATUS_CHANGE_ACTION)) {
                // 현재 인터넷 상태를 설정
                presenter.setNetworkStatus(NetworkStatusCheckUtil.checkCurrentNetworkStatus(MainActivity.this));
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie;
    }

    @Override
    protected MainPresenter getPresenter() {
        // Presenter 에서 사용할 Repository 객체는 싱글턴으로 작성
        // BaseActivity 에서 Presenter 는 미리 설정
        return new MainPresenter(this, MovieRepository.getInstance());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // RecyclerViewAdapter 설정
        adapter = new MovieListAdapter(this);

        // Presenter 에서 View 를 거치는 과정을 생략하기 위해
        // Adapter 를 Presenter 에 등록
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        // xml 에서 변수로 설정해놓은 presenter 설정
        binding.setPresenter(presenter);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // callback 형태로 View 가 설정된 후 호출
        presenter.onViewAttached();
        registerNetworkReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // view 의 사용이 끝난 후
        presenter.onViewDetached();
        unregisterNetworkReceiver();
    }

    /*
     * receiver 등록하기 */
    private void registerNetworkReceiver() {
        IntentFilter intentFilter = new IntentFilter(NetworkStatusCheckUtil.NETWORK_STATUS_CHANGE_ACTION);
        registerReceiver(mNetworkStatusReceiver, intentFilter);
    }

    /*
     * receiver 제거하기 */
    private void unregisterNetworkReceiver() {
        unregisterReceiver(mNetworkStatusReceiver);
    }

    private void initView() {

        // recyclerView 생성
        binding.recyclerMovie.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerMovie.setAdapter(adapter);
        binding.recyclerMovie.setEmptyView(binding.emptyView);
        binding.recyclerMovie.setNestedScrollingEnabled(false);
        binding.recyclerMovie.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // 최하단 스크롤 감지
        binding.recyclerMovie.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!binding.recyclerMovie.canScrollVertically(1)){
                    presenter.loadItems(false);
                }
            }
        });

        // 키보드 검색 버튼 리스너 등록
        binding.searchBox.etSearch.setOnEditorActionListener( (v, actionId, event) -> {
            // IME_ACTION_SEARCH 와 View 에 Option 을 같게 설정해줘야 함
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                onSearchButtonClicked();
                return true;
            }
            return false;
        });

        // 검색 버튼 리스너 등록
        binding.searchBox.btnSubmit.setOnClickListener(v -> onSearchButtonClicked());

        // 정렬 버튼 리스너 등록
        binding.searchBox.btnOrder.setOnClickListener(v ->onOrderSelectButtonClicked());
    }

    private void onOrderSelectButtonClicked() {

        final String[] items = getResources().getStringArray(R.array.search_order);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(getString(R.string.order_title))
                .setItems(items, (di, i) -> {
                    binding.searchBox.btnOrder.setText(items[i]);
                    presenter.onOrderSelectionChanged(MovieStrategyFactory.create(i));
                    di.dismiss();
                });

        dialogBuilder.show();
    }
    /**
     * 키보드 검색 혹은 검색 버튼 눌렀을 때 호출 */
    private void onSearchButtonClicked(){

        // 입력값이 존재 하는지 체크
        String searchKey = binding.searchBox.etSearch.getText().toString();

        if(!TextUtils.isEmpty(searchKey)) {
            // Event 를 Presenter 에 위임
            presenter.onSearchButtonClicked(searchKey);
            // RecyclerView 위치를 최상단으로 설정
            binding.recyclerMovie.scrollToPosition(0);
            // View 값 설정
            binding.emptyView.setText("");
            // 키보드 닫기
            KeyboardUtil.closeKeyboard(this, binding.searchBox.etSearch);

        } else {
            makeToast(getString(R.string.search_hint));
        }
    }

    /**
     * 검색 결과가 없는 경우 presenter 에 의해 호출됨 */
    @Override
    public void onSearchResultEmpty(String searchKey) {

        String displayString = "<b>'" + searchKey + "'</b>" + getString(R.string.search_result_empty);

        binding.emptyView.setText(Html.fromHtml(displayString));
    }

    /**
     * 영화 상세 정보 URL 로 연결 */
    @Override
    public void startMovieDetailPage(String linkUrl) {

        // 다음 탭으로 넘어가기
        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                .setToolbarColor(getResources().getColor(R.color.colorPrimary))
                .build();

        customTabsIntent.launchUrl(this, Uri.parse(linkUrl));
    }
}
