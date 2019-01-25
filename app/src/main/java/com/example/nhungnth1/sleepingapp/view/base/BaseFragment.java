package com.example.nhungnth1.sleepingapp.view.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhungnth1.sleepingapp.R;
import com.example.nhungnth1.sleepingapp.presenter.ValidationPresenter;

import butterknife.ButterKnife;

public class BaseFragment extends Fragment {
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
        Log.i("Lifecycle", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        Log.i("Lifecycle", "onCreateView" + view);
        ButterKnife.bind(this, view);
        return view;
    }
}
