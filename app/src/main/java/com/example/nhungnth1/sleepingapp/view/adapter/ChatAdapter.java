package com.example.nhungnth1.sleepingapp.view.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhungnth1.sleepingapp.R;
import com.example.nhungnth1.sleepingapp.model.MessageUser;
import com.example.nhungnth1.sleepingapp.view.base.baseadapter.BaseRecyclerAdapter;
import com.example.nhungnth1.sleepingapp.view.viewholder.ItemMessage;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends BaseRecyclerAdapter<MessageUser, ItemMessage> {
    private Context mContext;
    private List<MessageUser> listMessage;
    private ProgressDialog mProgressDialog;
    public static FirebaseAuth sFirebaseAuth = FirebaseAuth.getInstance();

    public ChatAdapter(Context context) {
        super(context);
        listMessage = new ArrayList<>();
    }

    public ChatAdapter(Context context, List<MessageUser> datas) {
        super(context, datas);
        this.mContext = context;
        this.listMessage = datas;
    }

    @NonNull
    @Override
    public ItemMessage onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message, viewGroup, false);
        return new ItemMessage(view, this.mContext);
    }

    @Override
    public int getItemCount() {
        return listMessage.size();
    }
}
