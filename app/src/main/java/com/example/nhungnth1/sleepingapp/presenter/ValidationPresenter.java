package com.example.nhungnth1.sleepingapp.presenter;

import android.util.Log;
import com.example.nhungnth1.sleepingapp.view.mvpview.RegisterView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationPresenter {
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static final String PASS_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static Pattern pattern;
    private Matcher matcher;
    private RegisterView mRegisterView;

    public ValidationPresenter(RegisterView registerView) {
        mRegisterView = registerView;
    }

    public boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Log.i("VerifyEmail", "verifyEmail: " + pattern);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePass(String pass) {
        pattern = Pattern.compile(PASS_REGEX, Pattern.CASE_INSENSITIVE);
        Log.i("VerifyPass", "VerifyPass: " + pattern);
        matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    public void register(String email, String pass, String rePass) {
        if(email == null || email.isEmpty()) {
            mRegisterView.invalid();
            return;
        }
        if(!pass.equals(rePass)) {
            mRegisterView.passNotMatch();
            return;
        }
        mRegisterView.register(email, pass);
    }
}
