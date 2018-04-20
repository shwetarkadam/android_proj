package com.example.hp.mccfirebase;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class Movie extends Fragment  {
        Activity context;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            context=getActivity();
            View rootView = inflater.inflate(R.layout.movie_layout, container, false);

            return rootView;
        }
        public void Detail(View view){

            Intent intent=new Intent(this.context,Details.class);
            startActivity(intent);
        }



    }


