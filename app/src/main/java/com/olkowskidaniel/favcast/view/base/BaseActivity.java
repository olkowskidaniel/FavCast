package com.olkowskidaniel.favcast.view.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.olkowskidaniel.favcast.R;
import com.olkowskidaniel.favcast.view.base.community.CommunityFragment;
import com.olkowskidaniel.favcast.view.base.discover.DiscoverFragment;
import com.olkowskidaniel.favcast.view.base.library.LibraryFragment;
import com.olkowskidaniel.favcast.view.base.personal.PersonalFragment;
import com.olkowskidaniel.favcast.viewmodel.BaseViewModel;

public class BaseActivity extends AppCompatActivity {

    @BindView(R.id.base_bottomNav)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        BaseViewModel baseViewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
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
}
