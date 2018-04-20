package com.example.hp.mccfirebase.pojos;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by HP on 11-04-2018.
 */

public class MySingleton {
    private static MySingleton mySingleton;
    private static Context context;
    private static RequestQueue requestQueue;

    public MySingleton(Context context) {
        this.context=context;
        requestQueue=getRequestQueue();
    }

    public static RequestQueue getRequestQueue() {
        if(requestQueue==null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;
    }
    public static  synchronized MySingleton getInstance(Context context){
        if(mySingleton==null){
            mySingleton=new MySingleton(context);
        }
        return mySingleton;

    }

    public<T> void addRequestQueue(Request<T> request){
       getRequestQueue().add(request);
    }
}
