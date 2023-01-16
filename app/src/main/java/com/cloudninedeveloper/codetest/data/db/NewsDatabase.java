package com.cloudninedeveloper.codetest.data.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cloudninedeveloper.codetest.data.vos.NewsVo;

/**
 * @Author myothiha
 * Created 15/01/2023 at 9:08 AM.
 **/

@Database(entities = {NewsVo.class}, version = 1,exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    private static NewsDatabase instance;

    public abstract NewsDao newsDao();

    public static synchronized NewsDatabase getInstance(Context context){
        if(instance==null){
            //If instance is null that's mean database is not created and create new database
            instance = Room.databaseBuilder(context.getApplicationContext(),NewsDatabase.class,"news_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}

