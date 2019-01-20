package com.example.yeseul.movieapp.view;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public abstract class BaseActivity<B extends ViewDataBinding, P extends BasePresenter> extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();

    protected B binding;
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 화면 세로 고정
        try {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }

        binding = DataBindingUtil.setContentView(this, getLayoutId());
        presenter = getPresenter();
    }

    public void makeToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    protected abstract int getLayoutId();
    protected abstract P getPresenter();
}