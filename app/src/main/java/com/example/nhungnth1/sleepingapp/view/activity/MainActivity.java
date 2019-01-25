package com.example.nhungnth1.sleepingapp.view.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

//import com.example.nhungnth1.randomapp.R;

import com.example.nhungnth1.sleepingapp.R;
import com.example.nhungnth1.sleepingapp.view.fragment.SignUpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rl_main)
    RelativeLayout rlMain;
    @BindView(R.id.btn_signin)
    Button btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "You ",
                        Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Tag", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Tag", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Tag", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Tag", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Tag", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Tag", "onRestart");
    }

    @OnClick(R.id.btn_signin)
    public void onButtonClick(View view) {
//        Toast.makeText(getApplicationContext(), "You have ",
//                Toast.LENGTH_LONG).show();
        Log.i("onButtonClick", "onButtonClick: ");
        gotoSignUp();
    }

    private void gotoSignUp() {
        Toast.makeText(getApplicationContext(), "You ",
                Toast.LENGTH_LONG).show();
//        SignUpFragment signUpFragment = new SignUpFragment();
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.rl_main, signUpFragment);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        ft.commit();
    }
}
