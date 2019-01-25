package com.example.nhungnth1.sleepingapp.view.viewholder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.nhungnth1.sleepingapp.R;
import com.example.nhungnth1.sleepingapp.model.MessageUser;
import com.example.nhungnth1.sleepingapp.view.base.baseviewholder.BaseItemViewHolder;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemMessage extends BaseItemViewHolder<MessageUser> {
    @BindView(R.id.text_time)
    TextView mTxtTime;
    @BindView(R.id.img_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.img_server)
    ImageView mIvServer;
    @BindView(R.id.cv_message)
    CardView mCvMessage;
    @BindView(R.id.tv_message)
    CardView mTvMessage;
    @BindView(R.id.rl_message)
    RelativeLayout mRlMessage;

    public static FirebaseAuth sFirebaseAuth = FirebaseAuth.getInstance();

    private Context mContext;
    public ItemMessage(@NonNull View itemView, Context context) {
        super(itemView);
        this.mContext = context;
    }

    @Override
    public void bind(MessageUser messageUser, int position) {
        mTxtTime.setText(messageUser.getDateString());
        //check xem nội dung của tin nhắn có phải là ảnh được lưu trên server không(ảnh được lưu trên server có có link dạng firebasestorage.googleapis.com/)
        if(messageUser.getText().contains("firebasestorage.googleapis.com")) {
            mIvServer.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
            mTvMessage.setVisibility(View.GONE);
            Glide.with(mContext)
                    .load(messageUser.getText())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            mProgressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    });
        } else {
            mIvServer.setVisibility(View.GONE);
            mTvMessage.setVisibility(View.VISIBLE);
        }

        if(sFirebaseAuth.getCurrentUser() != null && messageUser.getSender() != null) {
            mIvAvatar.setVisibility(View.VISIBLE);
            mRlMessage.setGravity(Gravity.RIGHT);
        } else {
            mIvAvatar.setVisibility(View.GONE);
            mRlMessage.setGravity(Gravity.LEFT);
        }
    }
}
