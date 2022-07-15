package com.tobery.personalmusic.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface CurrentInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insert(CurrentInfo currentInfo);

    @Delete
    public Completable delete(CurrentInfo currentInfo);

    @Query("DELETE FROM current_info WHERE infoType =:query")
    public Completable deleteByType(String query);

    @Query("SELECT * FROM current_info")
    public Single<List<CurrentInfo>> getInfo();

    @Query("SELECT * FROM current_info WHERE infoType = :query")
    public Single<CurrentInfo> getInfoByType(String query);
}
