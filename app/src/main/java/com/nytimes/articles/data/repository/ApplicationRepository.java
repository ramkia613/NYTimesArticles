package com.nytimes.articles.data.repository;

import android.arch.lifecycle.MutableLiveData;

import com.nytimes.articles.BuildConfig;
import com.nytimes.articles.data.model.Response;
import com.nytimes.articles.data.model.Result;

import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationRepository {
    private final int CONNECTION_TIMEOUT = 60;
    private final String API_KEY = "6d793f51de7b4713810808e0622fe518";
    private ApplicationAPI applicationAPI;
    private static ApplicationRepository repository;

    private ApplicationRepository() {
        // Service log will be add for debug mode
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApplicationAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();
        applicationAPI = retrofit.create(ApplicationAPI.class);
    }

    public synchronized static ApplicationRepository getInstance() {
        if (repository == null) {
            repository = new ApplicationRepository();
        }
        return repository;
    }

    public MutableLiveData<Response> getArticles(String section, String period) {
        final MutableLiveData<Response> articleMutableLiveData = new MutableLiveData<>();
        applicationAPI.articles(section, period, API_KEY).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, retrofit2.Response<Result> response) {
                if (response.isSuccessful()) {
                    articleMutableLiveData.postValue(Response.success(response.body()));
                } else {
                    if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                        articleMutableLiveData.postValue(Response.error(null, new Throwable("Un authorized, please contact support team")));
                    } else {
                        articleMutableLiveData.postValue(Response.error(null, new Throwable("Oops something went wrong!")));
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                articleMutableLiveData.postValue(Response.error(null, new Throwable("Please check your internet connection!")));
            }
        });
        return articleMutableLiveData;
    }
}
