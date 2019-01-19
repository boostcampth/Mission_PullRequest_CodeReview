package com.example.yeseul.movieapp.view;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/*
* Activity 들이 가지는 공통의 설정
* DataBinding 을 이용한 View-Presenter 사이의 의존성을 약화
* MVP 패턴을 사용하므로 Presenter 도 동일한 설정 필요
 */
public abstract class BaseActivity<B extends ViewDataBinding, P extends BasePresenter> extends AppCompatActivity {

    // Log 확인을 위한 TAG 설정. 기본적으로 클래스 이름으로 구분
    protected final String TAG = getClass().getSimpleName();

    protected B binding;
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 화면 세로 고정
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // View 와 바인딩하기 위한 객체
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        // Presenter 를 이용한 이벤트 위임
        presenter = getPresenter();
    }

    public void makeToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    protected abstract int getLayoutId();
    protected abstract P getPresenter();
}