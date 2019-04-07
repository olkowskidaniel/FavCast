package com.olkowskidaniel.favcast.view.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.olkowskidaniel.favcast.R;
import com.olkowskidaniel.favcast.view.base.community.CommunityFragment;
import com.olkowskidaniel.favcast.view.base.discover.DiscoverFragment;
import com.olkowskidaniel.favcast.view.base.library.LibraryFragment;
import com.olkowskidaniel.favcast.view.base.personal.PersonalFragment;
import com.olkowskidaniel.favcast.view.main.MainActivity;
import com.olkowskidaniel.favcast.viewmodel.BaseViewModel;

public class BaseActivity extends AppCompatActivity {

    @BindView(R.id.base_bottomNav)
    BottomNavigationView bottomNavigationView;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        BaseViewModel baseViewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportFragmentManager().beginTransaction().replace(R.id.base_fragment_container, new DiscoverFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bottomNav_discover:
                    getSupportFragmentManager().beginTransaction().replace(R.id.base_fragment_container, new DiscoverFragment()).commit();
                    break;
                case R.id.bottomNav_community:
                    getSupportFragmentManager().beginTransaction().replace(R.id.base_fragment_container, new CommunityFragment()).commit();
                    break;
                case R.id.bottomNav_library:
                    getSupportFragmentManager().beginTransaction().replace(R.id.base_fragment_container, new LibraryFragment()).commit();
                    break;
                case R.id.bottomNav_personal:
                    getSupportFragmentManager().beginTransaction().replace(R.id.base_fragment_container, new PersonalFragment()).commit();
                    break;
            }
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
