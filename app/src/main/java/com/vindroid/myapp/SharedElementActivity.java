package com.vindroid.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vindroid.myapp.ui.sharedelement.SharedElementFirstFragment;

public class SharedElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_element_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SharedElementFirstFragment.newInstance())
                    .commitNow();
        }
    }
}
