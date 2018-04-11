package com.example.hp.mccfirebase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import static com.google.firebase.database.FirebaseDatabase.*;

public class Details extends AppCompatActivity {
private FirebaseDatabase mdatabase,vdatabase;
private DatabaseReference mreference,vreference;
    private FirebaseAuth mAuth;
    User user=new User();
    String username=user.getUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }

    public void addReview(View view) {

        EditText editText= (EditText)findViewById(R.id.edit_query);
        mdatabase= FirebaseDatabase.getInstance();
        mreference= mdatabase.getReference("reviews").child(username).push();
        String name=editText.getText().toString().trim();
        mreference.setValue(name);

    }


}
