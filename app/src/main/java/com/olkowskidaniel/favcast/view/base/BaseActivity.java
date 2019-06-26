package com.olkowskidaniel.favcast.view.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.olkowskidaniel.favcast.R;
import com.olkowskidaniel.favcast.util.Constants;
import com.olkowskidaniel.favcast.view.base.community.CommunityFragment;
import com.olkowskidaniel.favcast.view.base.discover.DiscoverFragment;
import com.olkowskidaniel.favcast.view.base.library.LibraryFragment;
import com.olkowskidaniel.favcast.view.base.personal.PersonalFragment;
import com.olkowskidaniel.favcast.view.main.MainActivity;
import com.olkowskidaniel.favcast.viewmodel.BaseViewModel;

public class BaseActivity extends AppCompatActivity {

    @BindView(R.id.base_bottomNav)
    BottomNavigationView bottomNavigationView;

    BaseViewModel baseViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        baseViewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.base_fragment_container, new DiscoverFragment()).commit();

        baseViewModel.getNavEvent().observe(this, navEventObserver);
        baseViewModel.getAppExitTrigger().observe(this, appExitTriggerObserver);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            baseViewModel.navButtonPressed(item.getItemId());
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        baseViewModel.backButtonPressed();
    }

    final Observer<Integer> navEventObserver = new Observer<Integer>() {
        @Override
        public void onChanged(Integer integer) {
            switch (integer) {
                case Constants.OPENED_DISCOVER:
                    getSupportFragmentManager().beginTransaction().replace(R.id.base_fragment_container, new DiscoverFragment()).commit();
                    break;
                case Constants.OPENED_COMMUNITY:
                    getSupportFragmentManager().beginTransaction().replace(R.id.base_fragment_container, new CommunityFragment()).commit();
                    break;
                case Constants.OPENED_LIBRARY:
                    getSupportFragmentManager().beginTransaction().replace(R.id.base_fragment_container, new LibraryFragment()).commit();
                    break;
                case Constants.OPENED_PERSONAL:
                    getSupportFragmentManager().beginTransaction().replace(R.id.base_fragment_container, new PersonalFragment()).commit();
                    break;
            }
        }
    };

    final Observer<Boolean> appExitTriggerObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean aBoolean) {
            if (aBoolean) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        }
    };

}
