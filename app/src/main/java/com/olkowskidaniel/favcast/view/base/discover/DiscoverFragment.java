package com.olkowskidaniel.favcast.view.base.discover;

import androidx.lifecycle.Observer;
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
import com.olkowskidaniel.favcast.util.Constants;
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
        getFragmentManager().beginTransaction().replace(R.id.discover_fragment_container, new CategoriesFragment()).commit();
        discoverViewModel.getNavEventDiscoverTab().observe(this, discoverNavObserver);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            discoverViewModel.navButtonPressed(item.getItemId());
            return true;
        }
    };

    final Observer<Integer> discoverNavObserver = new Observer<Integer>() {
        @Override
        public void onChanged(Integer integer) {
            switch (integer) {
                case Constants.OPENED_DISCOVER_CATEGORIES:
                    getFragmentManager().beginTransaction().replace(R.id.discover_fragment_container, new CategoriesFragment()).commit();
                    break;
                case Constants.OPENED_DISCOVER_AUDIOBOOKS:
                    getFragmentManager().beginTransaction().replace(R.id.discover_fragment_container, new AudiobooksFragment()).commit();
                    break;
                case Constants.OPENED_DISCOVER_RADIO:
                    getFragmentManager().beginTransaction().replace(R.id.discover_fragment_container, new RadioFragment()).commit();
                    break;
            }
        }
    };
}
