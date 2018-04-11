package com.example.hp.mccfirebase;

/**
 * Created by Pradnya Borkar on 11-03-2018.
 */



        import android.support.v4.app.Fragment;

        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.storage.FirebaseStorage;
        import com.google.firebase.storage.StorageReference;

public class Hotel extends Fragment{
    private FirebaseAuth auth;

private FirebaseStorage mFirebaseStorage;
private StorageReference mFirebaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hotel, container, false);

        return rootView;

        // Write a message to the database

        



    }
    public void Details(){

    }

    public interface OnFragmentInteractionListner {
    }
}
