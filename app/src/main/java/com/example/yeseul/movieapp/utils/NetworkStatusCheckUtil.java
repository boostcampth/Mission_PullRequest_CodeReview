package com.example.yeseul.movieapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatusCheckUtil {

    public static final String NETWORK_STATUS_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    public static boolean checkCurrentNetworkStatus(final Context context) {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkInfo currentNetworkStatus = connectivityManager.getActiveNetworkInfo();

        // 연결 상태 반환
        return currentNetworkStatus != null && currentNetworkStatus.isConnected();
    }
}
