package com.example.nhungnth1.sleepingapp.presenter;

import com.example.nhungnth1.sleepingapp.view.mvpview.SignInView;

public class SignInPresenter {
    SignInView mSignInView;
    public SignInPresenter(SignInView signInView) {
        mSignInView = signInView;
    }

//    public boolean checkEmail(String email) {
//
//    }
//
//    public boolean checkPass(String pass) {
//
//    }

    public void signIn() {
        mSignInView.signIn();
    }
}
