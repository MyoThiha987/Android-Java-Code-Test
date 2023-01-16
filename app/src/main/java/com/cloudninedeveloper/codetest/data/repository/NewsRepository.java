package com.cloudninedeveloper.codetest.data.repository;

import com.cloudninedeveloper.codetest.data.vos.NewsVo;

import java.util.List;

import io.reactivex.Observable;

/**
 * @Author myothiha
 * Created 15/01/2023 at 9:07 AM.
 **/

interface NewsRepository {
    //fetch ArticleList from api
    Observable<List<NewsVo>> fetArticles(String query, String fromDate, String toDate, String sortBy, String apiKey);
}

