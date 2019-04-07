package com.olkowskidaniel.favcast.view.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.olkowskidaniel.favcast.R;
import com.olkowskidaniel.favcast.view.base.BaseActivity;

public class MainActivity extends AppCompatActivity {

    private Intent baseIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseIntent = new Intent(MainActivity.this, BaseActivity.class);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(baseIntent);
    }
}
