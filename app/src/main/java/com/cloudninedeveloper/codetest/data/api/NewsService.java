package com.cloudninedeveloper.codetest.data.api;


import com.cloudninedeveloper.codetest.BuildConfig;
import com.cloudninedeveloper.codetest.data.response.NewsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author myothiha
 * Created 15/01/2023 at 10:18 AM.
 **/
public interface NewsService {
    @GET(BuildConfig.EVERYTHING)
    Observable<NewsResponse> fetchArticles(
            @Query("q") String q,
            @Query("from") String from,
            @Query("to") String to,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );
}
