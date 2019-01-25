
package com.example.nhungnth1.sleepingapp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.nhungnth1.sleepingapp.R;
import com.example.nhungnth1.sleepingapp.presenter.ValidationPresenter;
import com.example.nhungnth1.sleepingapp.view.mvpview.RegisterView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpFragment extends Fragment implements RegisterView {
    @BindView(R.id.edt_username)
    EditText mEdtUsername;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.re_password)
    EditText mRePassword;
    private ValidationPresenter mValidationPresenter;
    protected FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Lifecycle", "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Lifecycle", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        Log.i("Lifecycle", "onCreateView" + view);
        ButterKnife.bind(this, view);
        mValidationPresenter = new ValidationPresenter(this);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Lifecycle", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Lifecycle", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Lifecycle", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Lifecycle", "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Lifecycle", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Lifecycle", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Lifecycle", "onDetach");
//        mListener = null;
    }

    @OnClick(R.id.btn_signup)
    public void onBtnClick() {
        Log.i("Register", "onClick ");
        String username = mEdtUsername.getText().toString();
        String pass = mPassword.getText().toString();
        String rePass = mRePassword.getText().toString();
        Log.i("Register", "onClick " + username + " " + pass);
        mValidationPresenter.register(username, pass, rePass);
    }

    @Override
    public void register(String email, String password) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            String uid = user.getUid();

                        } else {
                            Log.e("Signup Error", "onCancelled", task.getException());
                            try {
                                throw task.getException();
                            } catch(FirebaseAuthWeakPasswordException e) {
                                Log.e("Exception", e.getMessage());
                            } catch(FirebaseAuthInvalidCredentialsException e) {
                                Log.e("Exception", e.getMessage());
                            } catch(FirebaseAuthUserCollisionException e) {
                                Log.e("Exception", e.getMessage());
                            } catch(Exception e) {
                                Log.e("Exception", e.getMessage());
                            }
                        }
                    }
                });
    }

    @Override
    public void passNotMatch() {
        Log.i("RegisterFail", getString(R.string.warning_match_pass));
    }

    @Override
    public void invalid() {
        Log.i("RegisterFail", getString(R.string.email_empty));
    }
}
