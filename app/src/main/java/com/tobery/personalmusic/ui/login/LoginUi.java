package com.tobery.personalmusic.ui.login;

import androidx.databinding.ObservableField;

import java.io.Serializable;

public class LoginUi implements Serializable {

    public ObservableField<String> userName;
    public ObservableField<String> password;

    public LoginUi(ObservableField<String> userName, ObservableField<String> password) {
        this.password = password;
        this.userName = userName;
    }
}
