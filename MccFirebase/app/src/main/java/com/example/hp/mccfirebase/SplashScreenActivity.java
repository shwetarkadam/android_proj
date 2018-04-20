package com.example.hp.mccfirebase;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.messaging.FirebaseMessaging;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(i);
                MyFirebaseInstanceIDService id =new MyFirebaseInstanceIDService();
                id.onTokenRefresh();
                FirebaseMessaging.getInstance().subscribeToTopic("NEWS");
                finish();
            }
        },2500);
    }
}
