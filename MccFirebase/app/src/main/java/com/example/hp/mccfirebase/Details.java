package com.example.hp.mccfirebase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Vector;

import static com.google.firebase.database.FirebaseDatabase.*;

public class Details extends AppCompatActivity {
    private FirebaseDatabase mdatabase,vdatabase;
    private DatabaseReference mreference,vreference;
    private ArrayList<String> arrayList =new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private Vector vector = new Vector();
    private FirebaseAuth mAuth;
    private Button but;
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

        EditText editText = (EditText) findViewById(R.id.edit_query);
        mdatabase = FirebaseDatabase.getInstance();
        mreference = mdatabase.getReference("reviews").child(username).push();
        String name = editText.getText().toString().trim();
        mreference.setValue(name);
        arrayList.add(name);
        adapter = new ArrayAdapter<String>(this, R.layout.activity_details, arrayList);
       ListView txt = (ListView) findViewById(R.id.view_reviews);
      /* txt = (ListView) findViewById(R.id.view_reviews); */
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arrayList);
        txt.setAdapter(adapter);

        mreference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                arrayList.add(dataSnapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                arrayList.remove(dataSnapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        editText.setText("");
    }
}