package com.grace.thetraveldiary.ui.viemodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.grace.thetraveldiary.BR;

public class LoginViewModel extends BaseObservable {
    private String email = "";
    private String password = "";

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public void onLoginClicked() {

        if (!email.isEmpty() && !password.isEmpty()) {
            // Successful login
        } else {
            // Failed login
        }
    }

    public void noAccount() {

        if (!email.isEmpty() && !password.isEmpty()) {
            // Successful login
        } else {
            // Failed login
        }
    }
}
