package com.cloudninedeveloper.codetest.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cloudninedeveloper.codetest.data.vos.NewsVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author myothiha
 * Created 15/01/2023 at 9:10 AM.
 **/


@Dao
public interface NewsDao {

    //insert newlist
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insertAllNews(List<NewsVo> data );

    //Delete all news from table;
    @Query("DELETE FROM news_table")
    void deleteAllNews();

    //Fetch News list
    @Query("SELECT * FROM news_table")
    List<NewsVo> fetchAllNews();
}
