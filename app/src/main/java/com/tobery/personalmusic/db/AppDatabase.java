package com.tobery.personalmusic.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1,entities = {CurrentInfo.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract CurrentInfoDao currentInfoDao();

    private volatile static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if (instance == null){
            synchronized (AppDatabase.class){
                if (instance == null){
                    instance = buildDatabase(context);
                }
            }
        }
        return instance;
    }

    private static AppDatabase buildDatabase(Context context){
        return Room.databaseBuilder(context,AppDatabase.class,"music_room")
                .allowMainThreadQueries()
                .build();
    }
}
