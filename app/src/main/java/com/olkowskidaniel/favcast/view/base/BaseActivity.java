package com.olkowskidaniel.favcast.view.base;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import android.os.Bundle;
import com.olkowskidaniel.favcast.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
    }
}
