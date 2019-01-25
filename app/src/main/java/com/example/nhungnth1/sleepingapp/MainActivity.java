package com.example.nhungnth1.sleepingapp;
/**
 * Allow users to sign in.
 * Sync data using the Firebase Realtime Database.
 * Receive background messages with Firebase Notifications.
 * Configure an application with Firebase Remote Config.
 * Track application usage flows with Google Analytics for Firebase.
 * Allow users to send invitations to install with Firebase Invites.
 * Display ads with AdMob.
 * Report crashes with Firebase Crash Reporting.
 * Test your app with Firebase Test Lab.
 */

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.nhungnth1.sleepingapp.view.fragment.SignInFragment;
import com.example.nhungnth1.sleepingapp.view.fragment.SignUpFragment;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Lifecycle", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Lifecycle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Lifecycle", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Lifecycle", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Lifecycle", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Lifecycle", "onRestart");
    }

    @OnClick(R.id.btn_signup)
    public void onClickToSignUp(View view) {
        gotoSignUp();
    }

    private void gotoSignUp() {
        SignUpFragment signUpFragment = new SignUpFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.rl_main, signUpFragment);
        ft.add(R.id.rl_main, signUpFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    @OnClick(R.id.btn_signin)
    public void onClickToSignIn(View view) {
        gotoSignIn();
    }

    private void gotoSignIn() {
        SignInFragment signInFragment = new SignInFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.rl_main, signInFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }
}
