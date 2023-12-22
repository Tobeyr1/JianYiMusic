package com.tobery.personalmusic.ui.home.menu;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableLong;

import java.io.Serializable;

public class UserInfoUi implements Serializable {

    public ObservableField<String> imageUrl;
    public ObservableField<String> nickname;//昵称
    public ObservableLong userId;
    public ObservableField<String> signature;//个签
    public ObservableField<String> follows;//关注
    public ObservableField<String> followeds;//粉丝
    public ObservableField<String> level;//用户等级

    public UserInfoUi(ObservableField<String> imageUrl, ObservableField<String> nickname, ObservableLong userId,ObservableField<String> signature, ObservableField<String> follows, ObservableField<String> followeds,ObservableField<String> level) {
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.signature = signature;
        this.follows = follows;
        this.followeds = followeds;
        this.level = level;
    }
}
