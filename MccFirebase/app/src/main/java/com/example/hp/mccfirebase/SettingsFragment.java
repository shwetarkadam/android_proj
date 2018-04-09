package com.example.hp.mccfirebase;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HP on 08-04-2018.
 */

public class SettingsFragment extends Fragment {
    @Nullable

    View myview;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.activity_settings,container,false);
        return myview;
    }
}
