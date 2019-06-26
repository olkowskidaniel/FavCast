package com.olkowskidaniel.favcast.view.base.discover;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.olkowskidaniel.favcast.R;
import com.olkowskidaniel.favcast.view.base.discover.audiobooks.AudiobooksFragment;
import com.olkowskidaniel.favcast.view.base.discover.categories.CategoriesFragment;
import com.olkowskidaniel.favcast.view.base.discover.radio.RadioFragment;
import com.olkowskidaniel.favcast.viewmodel.DiscoverViewModel;

public class DiscoverFragment extends Fragment {

    @BindView(R.id.discover_nav)
    BottomNavigationView discoverNav;
    private DiscoverViewModel discoverViewModel;

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_fragment, container, false);
        ButterKnife.bind(this, view);
        discoverNav.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        discoverViewModel = ViewModelProviders.of(this).get(DiscoverViewModel.class);
        // TODO: Use the ViewModel
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.categories_discoverTopMenu:
                    getFragmentManager().beginTransaction().replace(R.id.discover_fragment_container, new CategoriesFragment()).commit();
                    break;
                case R.id.audiobooks_discoverTopMenu:
                    getFragmentManager().beginTransaction().replace(R.id.discover_fragment_container, new AudiobooksFragment()).commit();
                    break;
                case R.id.radio_discoverTopMenu:
                    getFragmentManager().beginTransaction().replace(R.id.discover_fragment_container, new RadioFragment()).commit();
            }
            return true;
        }
    };

}
