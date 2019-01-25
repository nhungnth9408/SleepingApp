package com.example.nhungnth1.sleepingapp.view.base.baseadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.nhungnth1.sleepingapp.view.base.baseviewholder.BaseItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<Data, Holder extends BaseItemViewHolder> extends RecyclerView.Adapter<Holder> {
    private Context mContext;
    private List<Data> mDatas;
    public BaseRecyclerAdapter(Context context) {
        this.mContext = context;
        this.mDatas = new ArrayList<>();
    }
    public BaseRecyclerAdapter(Context context, List<Data> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public abstract Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i);

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        try {
            return mDatas.size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
