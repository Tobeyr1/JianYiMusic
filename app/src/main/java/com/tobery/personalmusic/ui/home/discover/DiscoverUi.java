package com.tobery.personalmusic.ui.home.discover;

import androidx.databinding.ObservableField;

import java.io.Serializable;

public class DiscoverUi implements Serializable {

    public ObservableField<String> userName;
    public ObservableField<String> password;

    public DiscoverUi(ObservableField<String> userName, ObservableField<String> password) {
        this.password = password;
        this.userName = userName;
    }
}
