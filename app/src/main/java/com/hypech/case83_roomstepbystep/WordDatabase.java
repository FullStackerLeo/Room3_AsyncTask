package com.hypech.case83_roomstepbystep;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Entry类-->Dao-->EntryRoomDatabase(单例模式)-->Repository(从EntryRoomDatabase获得Dao)-->
// 在Repository中实现异步-->AndroidViewModel通过Repository访问数据库CRUD
@Database(entities={Word.class}, version =1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;
    static synchronized WordDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, "word_database")
                    .build();
        }
        return INSTANCE;
    }
    public abstract WordDao getWordDao();
}