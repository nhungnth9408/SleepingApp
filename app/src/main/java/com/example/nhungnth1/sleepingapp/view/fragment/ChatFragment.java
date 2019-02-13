package com.example.nhungnth1.sleepingapp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.nhungnth1.sleepingapp.R;
import com.example.nhungnth1.sleepingapp.model.MessageUser;
import com.example.nhungnth1.sleepingapp.presenter.ChatPresenter;
import com.example.nhungnth1.sleepingapp.utilities.DateUtils;
import com.example.nhungnth1.sleepingapp.view.adapter.ChatAdapter;
import com.example.nhungnth1.sleepingapp.view.mvpview.ChatView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatFragment extends Fragment implements ChatView {
    @BindView(R.id.rv_message)
    RecyclerView mRvMessage;
    @BindView(R.id.edt_message)
    EditText mEdtMessage;

    private ChatPresenter mChatPresenter;
    private ChatAdapter mChatAdapter;
    private List<MessageUser> mListMessage;
    private String uid;
    public ChatFragment() {
        mListMessage = new ArrayList<>();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle bundle = getArguments();
//        uid = bundle.get("UID").toString();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mChatPresenter = new ChatPresenter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRvMessage.setLayoutManager(layoutManager);
        mChatAdapter = new ChatAdapter(getContext(), mListMessage);
        mRvMessage.setAdapter(mChatAdapter);
    }

    @OnClick(R.id.ib_send)
    public void onClick(){
        mChatPresenter.addMessageToAdapter();
    }

    @Override
    public void send() {
        MessageUser messageUser = new MessageUser();
        String messageInput = mEdtMessage.getText().toString();
        messageUser.setDate(new Date());
        messageUser.setText(messageInput);
        messageUser.setDateString(DateUtils.mHourFormat.format(new Date()));
        mListMessage.add(messageUser);
        mChatAdapter.notifyItemChanged(mListMessage.indexOf(messageUser));
        mChatPresenter.getProfile();
        mChatPresenter.sendToServer(messageUser);
    }
}
