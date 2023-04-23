package com.kerbabian.timero.ui.Login;

import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private String UnameText;
    private String UpassText;


    public void setText(String sn, String sp) {
        UnameText = sn;
        UpassText = sp;
    }

    public String getUsernameText() {
        return UnameText;
    }
    public String getUserPassText() {
        return UpassText;
    }
}