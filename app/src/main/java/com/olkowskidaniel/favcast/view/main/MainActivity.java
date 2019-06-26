package com.olkowskidaniel.favcast.view.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.olkowskidaniel.favcast.R;
import com.olkowskidaniel.favcast.view.base.BaseActivity;

public class MainActivity extends AppCompatActivity {

    private Intent baseIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        baseIntent = new Intent(MainActivity.this, BaseActivity.class);
        startActivity(baseIntent);
    }
}
