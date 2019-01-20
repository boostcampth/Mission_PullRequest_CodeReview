package com.example.yeseul.movieapp.view;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

public abstract class BaseActivity<B extends ViewDataBinding, P extends BasePresenter> extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();

    protected B binding;
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 화면 세로 고정
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        binding = DataBindingUtil.setContentView(this, getLayoutId());
        presenter = getPresenter();
    }

    public void makeToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void showDialog(String linkUrl) {
        WebView webView = new WebView(this);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUserAgentString("AndroidWebView");
        webView.clearCache(true);
        webView.loadUrl(linkUrl);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setView(webView);
        dialog.setCancelable(false);
        dialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialog.show();
    }

    protected abstract int getLayoutId();
    protected abstract P getPresenter();
}