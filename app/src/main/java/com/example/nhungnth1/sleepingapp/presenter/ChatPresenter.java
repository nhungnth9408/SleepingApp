package com.example.nhungnth1.sleepingapp.presenter;

import android.net.Uri;
import android.util.Log;

import com.example.nhungnth1.sleepingapp.model.MessageUser;
import com.example.nhungnth1.sleepingapp.utilities.DateUtils;
import com.example.nhungnth1.sleepingapp.view.mvpview.ChatView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ChatPresenter {
    private ChatView mChatView;
//    public static Firebase sRef = new Firebase()
    public static String COLUMN_TEXT = "text";
    public static String COLUMN_SENDER = "sender";
    protected FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private String mUid;
    public ChatPresenter(ChatView chatView) {
        this.mChatView = chatView;
    }

    public void sendMessage() {
//        getProfile();
//        sendToServer();
    }

    public void readDataFromFirebase() {

    }
    public void getProfile(){
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        Log.i("UID", user.getUid());
        mUid = user.getUid();
        if(user != null) {
            for(UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                // email.com ???
                String provideId = profile.getProviderId();
//                 UID specific to the provider
                // The user's ID, unique to the Firebase project. Do NOT use this value to
                // authenticate with your backend server, if you have one. Use
                // FirebaseUser.getIdToken() instead.
//                String mUid = profile.getUid();

                String name = profile.getDisplayName();
                String email = profile.getEmail();
                Uri photoUrl = profile.getPhotoUrl();
            }

        }
    }
    // Write a message to the database
    public void sendToServer(MessageUser messageUser) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        String key = DateUtils.mHourFormat.format(messageUser.getDate());
        HashMap<String, String> obj = new HashMap<>();
        obj.put(COLUMN_TEXT, messageUser.getText());
        obj.put(COLUMN_SENDER, messageUser.getSender());

        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userid/
        String userId = reference.push().getKey();
        // pushing user to 'users' node using the userId
//        reference.child(userId).setValue(obj);
        reference.child(mUid).child("user").setValue(obj);
    }

    public void addMessageToAdapter() {
        mChatView.send();
    }
}

