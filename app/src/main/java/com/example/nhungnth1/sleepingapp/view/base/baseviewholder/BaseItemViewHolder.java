package com.example.nhungnth1.sleepingapp.view.base.baseviewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Date;

import butterknife.ButterKnife;

public abstract class BaseItemViewHolder<Data> extends RecyclerView.ViewHolder  {
    public BaseItemViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bind(Data data, int position);
}
