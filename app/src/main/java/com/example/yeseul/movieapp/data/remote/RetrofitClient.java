package com.example.yeseul.movieapp.data.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://openapi.naver.com/v1/";
    private static final String CLIENT_ID = "uMJDm4Jz6qzLyQPT5KK9";
    private static final String CLIENT_SECRET = "DxWswCSZ20";

    private static Retrofit retrofit = null;

    private RetrofitClient() {}

    public static Retrofit getClient(){

        if(retrofit == null){

            // 로그 찍기 위한 interceptor
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Naver Client Header
            Interceptor interceptor = chain -> {
                final Request.Builder builder = chain.request().newBuilder()
                        .header("X-Naver-Client-Id", CLIENT_ID)
                        .header("X-Naver-Client-Secret", CLIENT_SECRET);

                return chain.proceed(builder.build());
            };

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
