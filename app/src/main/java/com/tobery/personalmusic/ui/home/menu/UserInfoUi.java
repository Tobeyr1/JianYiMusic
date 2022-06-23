package com.tobery.personalmusic.ui.home.menu;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import java.io.Serializable;

public class UserInfoUi implements Serializable {

    public ObservableField<String> imageUrl;
    public ObservableField<String> nickname;
    public ObservableInt userId;

    public UserInfoUi(ObservableField<String> imageUrl, ObservableField<String> nickname, ObservableInt userId) {
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.userId = userId;
    }
}
