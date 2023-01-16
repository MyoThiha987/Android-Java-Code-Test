package com.cloudninedeveloper.codetest.data.response;

import com.cloudninedeveloper.codetest.data.vos.NewsVo;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @Author myothiha
 * Created 15/01/2023 at 10:08 AM.
 **/

public class NewsResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("articles")
    ArrayList<NewsVo> articles = new ArrayList <NewsVo> ();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setArticles(ArrayList<NewsVo> articles){
        this.articles = articles;
    }

    public ArrayList<NewsVo> getArticles(){
        return articles;
    }
}

