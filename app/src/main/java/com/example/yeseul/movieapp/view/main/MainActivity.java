package com.example.yeseul.movieapp.view.main;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.example.yeseul.movieapp.BR;
import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.data.model.RecentSearchWord;
import com.example.yeseul.movieapp.data.source.movie.MovieRepository;
import com.example.yeseul.movieapp.databinding.ActivityMovieBinding;
import com.example.yeseul.movieapp.utils.KeyboardUtil;
import com.example.yeseul.movieapp.view.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Stack;

import io.reactivex.Observable;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends BaseActivity<ActivityMovieBinding, MainPresenter> implements MainContract.View {

    private MovieListAdapter adapter;
    private RecentSearchAdapter recentSearchAdapter;
    private ArrayList<RecentSearchWord> recentSearchWords = new ArrayList<>();
    private MutableLiveData<Boolean> searchBoxFlagLiveData = new MutableLiveData<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this, MovieRepository.getInstance());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new MovieListAdapter(this);

        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        binding.setPresenter(presenter);

        recentSearchAdapter = new RecentSearchAdapter(recentSearchWords, this, binding.searchBox.etSearch);
        binding.searchBox.setVariable(BR.recent_search_adapter, recentSearchAdapter);

        initView();

        presenter.onViewCreated();

        searchBoxFlagLiveData.observe(this, flag -> {
            if (flag) {
                binding.searchBox.rvRecent.setVisibility(VISIBLE);
                binding.viewTranslate.setVisibility(VISIBLE);
            } else {
                binding.searchBox.rvRecent.setVisibility(GONE);
                binding.viewTranslate.setVisibility(GONE);
            }
        });
    }

    private void initView() {
        // recyclerView 생성
        binding.recyclerMovie.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerMovie.setAdapter(adapter);
        binding.recyclerMovie.setEmptyView(binding.emptyView);
        binding.recyclerMovie.setNestedScrollingEnabled(false);
        binding.recyclerMovie.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // 최하단 스크롤 감지
        binding.recyclerMovie.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!binding.recyclerMovie.canScrollVertically(1)) {
                    presenter.loadItems(false);
                }
            }
        });

        // 키보드 검색 버튼 리스너 등록
        binding.searchBox.etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onSearchButtonClicked();
                return true;
            }
            return false;
        });
        binding.searchBox.etSearch.setOnClickListener(v -> searchBoxFlagLiveData.setValue(true));
        binding.viewTranslate.setOnClickListener(v -> searchBoxFlagLiveData.setValue(false));
        // 검색 버튼 리스너 등록
        binding.searchBox.btnSubmit.setOnClickListener(v -> onSearchButtonClicked());
    }

    /**
     * 키보드 검색 혹은 검색 버튼 눌렀을 때 호출
     */
    private void onSearchButtonClicked() {

        // 입력값이 존재 하는지 체크
        String searchKey = binding.searchBox.etSearch.getText().toString();

        if (!TextUtils.isEmpty(searchKey)) {
            presenter.onSearchButtonClicked(searchKey);
            binding.recyclerMovie.scrollToPosition(0);
            binding.emptyView.setText("");
            searchBoxFlagLiveData.setValue(false);
            makeSearchInfo(searchKey);
            KeyboardUtil.closeKeyboard(this, binding.searchBox.etSearch);
        } else {
            makeToast(getString(R.string.search_hint));
        }
    }

    private void makeSearchInfo(String searchKey) {
        Date cDate = new Date();
        String MMdd = new SimpleDateFormat("MM-dd").format(cDate);
        RecentSearchWord recentSearchWord = new RecentSearchWord(searchKey, MMdd);
        boolean compareFlag = false;
        int comparePosition = 0;
        for (int i=0; i<recentSearchWords.size(); i++){
            if(recentSearchWords.get(i).getMovieName().equals(searchKey)){
                compareFlag = true;
                comparePosition = i;
                break;
            }
        }
        if (compareFlag) {
            recentSearchWords.remove(comparePosition);
            recentSearchWords.add(0, recentSearchWord);
        }else{
            recentSearchWords.add(0, recentSearchWord);
        }
        if (recentSearchWords.size() < 5){
            if(compareFlag)
                recentSearchAdapter.notifyItemRangeChanged(0, recentSearchWords.size());
            else
                recentSearchAdapter.notifyItemInserted(0);
        }
        else
            recentSearchAdapter.notifyItemRangeChanged(0, 5);
    }

    /**
     * 검색 결과가 없는 경우 presenter 에 의해 호출됨
     */
    @Override
    public void onSearchResultEmpty(String searchKey) {

        String displayString = "<b>'" + searchKey + "'</b>" + getString(R.string.search_result_empty);

        binding.emptyView.setText(Html.fromHtml(displayString));
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
}
