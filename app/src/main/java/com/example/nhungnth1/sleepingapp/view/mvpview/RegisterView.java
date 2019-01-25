package com.example.nhungnth1.sleepingapp.view.mvpview;

public interface RegisterView {
    void register(String email, String pass);
    void passNotMatch();
    void invalid();
}
