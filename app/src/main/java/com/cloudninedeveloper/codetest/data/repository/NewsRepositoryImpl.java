package com.cloudninedeveloper.codetest.data.repository;


import android.app.Application;

import com.cloudninedeveloper.codetest.data.RetrofitClient;
import com.cloudninedeveloper.codetest.data.api.NewsService;
import com.cloudninedeveloper.codetest.data.db.NewsDao;
import com.cloudninedeveloper.codetest.data.db.NewsDatabase;
import com.cloudninedeveloper.codetest.data.vos.NewsVo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @Author myothiha
 * Created 15/01/2023 at 9:08 AM.
 **/


public class NewsRepositoryImpl implements NewsRepository {

    private NewsDao newsDao;
    private Retrofit retrofit;

    public NewsRepositoryImpl(Application application) {
        NewsDatabase newsDatabase = NewsDatabase.getInstance(application);
        newsDao = newsDatabase.newsDao();
        retrofit = RetrofitClient.getRetrofit();
    }

    @Override
    public Observable<List<NewsVo>> fetArticles(String query, String fromDate, String toDate, String sortBy, String apiKey) {
        return retrofit.create(NewsService.class).fetchArticles(query, fromDate, toDate, sortBy, apiKey)
                .subscribeOn(Schedulers.io())
                .map(newsResponse -> {
                    newsDao.insertAllNews(newsResponse.getArticles());
                    return newsDao.fetchAllNews();
                }).onErrorReturn(throwable -> newsDao.fetchAllNews());


    }
}


