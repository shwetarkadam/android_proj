package com.example.hp.mccfirebase;

import android.app.Service;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by HP on 11-04-2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String fcm_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + fcm_token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

    }

}
