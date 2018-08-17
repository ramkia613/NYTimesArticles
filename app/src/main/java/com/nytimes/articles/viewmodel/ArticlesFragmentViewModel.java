package com.nytimes.articles.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.nytimes.articles.data.model.Response;
import com.nytimes.articles.data.repository.ApplicationRepository;

public class ArticlesFragmentViewModel extends ViewModel {
    private ApplicationRepository repository;
    private MutableLiveData<Response> resultLiveData;

    public ArticlesFragmentViewModel() {
        repository = ApplicationRepository.getInstance();
        if (resultLiveData == null) {
            resultLiveData = new MutableLiveData<>();
        }
    }

    @VisibleForTesting
    public MutableLiveData<Response> getArticles(String section, String period) {
        repository.getArticles(section, period).observeForever(new Observer<Response>() {
            @Override
            public void onChanged(@Nullable Response response) {
                resultLiveData.postValue(response);
            }
        });
        return resultLiveData;
    }

}
