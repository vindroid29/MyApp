package com.vindroid.myapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 2084;

    // its working good
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        Button btnClick = findViewById(R.id.tvSampleText);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    startService(new Intent(MainActivity.this, FloatingViewService.class));
                    finish();
                } else if (Settings.canDrawOverlays(MainActivity.this)) {
                    startService(new Intent(MainActivity.this, FloatingViewService.class));
                    //finish();
                } else {
                    askPermission();
                    Toast.makeText(MainActivity.this, "You need System Alert Window Permission to do this", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            askPermission();
        }
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(MainActivity.this, FloatingViewService.class));
        super.onDestroy();
    }

    private void askPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, SYSTEM_ALERT_WINDOW_PERMISSION);
    }
}
