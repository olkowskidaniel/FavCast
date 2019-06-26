package com.olkowskidaniel.favcast.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.olkowskidaniel.favcast.R;
import com.olkowskidaniel.favcast.util.Constants;

public class DiscoverViewModel extends ViewModel {

    private MutableLiveData<Integer> navEventDiscoverTab;

    public MutableLiveData<Integer> getNavEventDiscoverTab() {
        if (navEventDiscoverTab == null) {
            navEventDiscoverTab = new MutableLiveData<>();
        }
        return navEventDiscoverTab;
    }

    public void navButtonPressed(int itemId) {
        switch (itemId) {
            case R.id.discoverTopMenu_categories:
                getNavEventDiscoverTab().setValue(Constants.OPENED_DISCOVER_CATEGORIES);
                break;
            case R.id.discoverTopMenu_audiobooks:
                getNavEventDiscoverTab().setValue(Constants.OPENED_DISCOVER_AUDIOBOOKS);
                break;
            case R.id.discoverTopmenu_radio:
                getNavEventDiscoverTab().setValue(Constants.OPENED_DISCOVER_RADIO);
                break;
        }
    }
}
