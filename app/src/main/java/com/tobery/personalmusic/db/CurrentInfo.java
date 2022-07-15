package com.tobery.personalmusic.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "current_info")
public class CurrentInfo {
    @PrimaryKey
    @ColumnInfo(name = "infoType", collate = ColumnInfo.NOCASE)
    public @NonNull String infoType;

    public String info;

    public Long timeStamp;
}
