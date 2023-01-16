package com.cloudninedeveloper.codetest.ui.newlist;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.cloudninedeveloper.codetest.BuildConfig;
import com.cloudninedeveloper.codetest.data.repository.NewsRepositoryImpl;
import com.cloudninedeveloper.codetest.data.vos.NewsVo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Author myothiha
 * Created 15/01/2023 at 2:56 PM.
 **/


class NewsViewModel extends AndroidViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private NewsRepositoryImpl newsRepository;
    MutableLiveData<List<NewsVo>> articlesList;

    public NewsViewModel(Application application) {
        super(application);
        newsRepository = new NewsRepositoryImpl(application);
        articlesList = new MutableLiveData<>();
    }

    @SuppressLint("CheckResult")
    public void fetchArticleList() {
        Disposable disposable =
                newsRepository.fetArticles("apple", "2023-01-12", "2023-01-12", "popularity", BuildConfig.API_KEY)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(newsVoArrayList -> articlesList.postValue(newsVoArrayList), throwable -> {
                            new Exception("No Internet Connection");
                        });
        compositeDisposable.add(disposable);


    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}

