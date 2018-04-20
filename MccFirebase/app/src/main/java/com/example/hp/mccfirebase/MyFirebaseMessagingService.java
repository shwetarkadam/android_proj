package com.example.hp.mccfirebase;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.hp.mccfirebase.pojos.MySingleton;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by HP on 11-04-2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG ="FirebaseMessagingService" ;
    PendingIntent pendingIntent;
    Bitmap bitmap;
    @SuppressLint("LongLogTag")
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        if(remoteMessage.getData().size()>0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            try {
                JSONObject data = new JSONObject(remoteMessage.getData());
                String jsonMessage = data.getString("extra_information");
                Log.d(TAG, "onMessageReceived: \n" +
                        "Extra Information: " + jsonMessage);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            final String title, imageurl, message;
            title=remoteMessage.getData().get("title");
            message=remoteMessage.getData().get("message");
            imageurl=remoteMessage.getData().get("imageurl");
            String click_action = remoteMessage.getNotification().getClickAction(); //get click_action

           /* Log.d(TAG, "Message Notification Title: " + title);
            Log.d(TAG, "Message Notification Body: " + message);
            Log.d(TAG, "Message Notification click_action: " + click_action);
        */
            //To get a Bitmap image from the URL received
            bitmap = getBitmapfromUrl(imageurl);
            //String click_action=remoteMessage.getNotification().getClickAction();
            //Intent intent=new Intent(click_action);
            Intent intent;
            if(click_action.equals("SHOWIMAGESACTIVITY")){
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }else if(click_action.equals("MAIN2ACTIVITY")){
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            else{
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }

            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pendingIntent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
            //for sound
            Uri sounduri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            final NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
            builder.setContentTitle(title);
            builder.setContentText(message);
            builder.setContentIntent(pendingIntent);

            builder.setSound(sounduri);


            ImageRequest imageRequest=new ImageRequest(imageurl, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    response= getBitmapfromUrl(imageurl);
                    builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(response));
                    NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(0,builder.build());
                }
            }, 0, 0, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            MySingleton.getInstance(this).addRequestQueue(imageRequest);
        }
    }
    /*
    *To get a Bitmap image from the URL received
    * */
    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }
}
