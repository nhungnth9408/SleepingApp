package com.example.nhungnth1.sleepingapp.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.nhungnth1.sleepingapp.R;
import com.example.nhungnth1.sleepingapp.view.base.BaseFragment;
import com.example.nhungnth1.sleepingapp.presenter.SignInPresenter;
import com.example.nhungnth1.sleepingapp.view.mvpview.SignInView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInFragment extends BaseFragment implements SignInView{
    @BindView(R.id.edt_username)
    EditText mEdtUsername;
    @BindView(R.id.password)
    EditText mPassword;
    protected FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private SignInPresenter mSignInPresenter;
    public SignInFragment() {
        // Required empty public constructor

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Lifecycle", "onAttach");
        if (context instanceof Activity) {
            Log.i("Lifecycle", "onAttach");
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignInPresenter = new SignInPresenter(this);
        Log.i("Lifecycle", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        Log.i("Lifecycle", "onCreateView" + view);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_signin)
    public void onBtnClick() {
        Log.i("Register", "onClick ");
        String username = mEdtUsername.getText().toString();
        String pass = mPassword.getText().toString();
        Log.i("Register", "onClick " + username + " " + pass);
        mSignInPresenter.signIn();
    }


    @Override
    public void signIn() {
        String email = mEdtUsername.getText().toString();
        String pass = mPassword.getText().toString();
        Log.i("signIn", "signIn: " + mFirebaseAuth);
        mFirebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.i("signIn", "onComplete ");
                    if(task.isSuccessful()) {
                        FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    } else {
                        try{
                            throw task.getException();
                        } catch (Exception e) {
                            Log.i("Exception ", e.getMessage());
                        }
                    }
                }
            });
    }

    @Override
    public void invalid() {

    }
}
