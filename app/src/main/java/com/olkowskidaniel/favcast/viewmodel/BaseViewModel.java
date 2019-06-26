package com.olkowskidaniel.favcast.viewmodel;

import android.app.Application;
import android.os.Handler;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.olkowskidaniel.favcast.R;
import com.olkowskidaniel.favcast.util.Constants;

public class BaseViewModel extends AndroidViewModel {

    private boolean doubleBackToExitPressedOnce = false;

    public BaseViewModel(Application application) {
        super(application);
    }

    private MutableLiveData<Integer> navEvent;
    public MutableLiveData<Integer> getNavEvent() {
        if (navEvent == null) {
            navEvent = new MutableLiveData<>();
        }
        return navEvent;
    }

    private MutableLiveData<Boolean>  appExitTrigger;
    public MutableLiveData<Boolean> getAppExitTrigger () {
        if (appExitTrigger == null) {
            appExitTrigger = new MutableLiveData<>();
        }
        return appExitTrigger;
    }

    public void backButtonPressed() {
        if (doubleBackToExitPressedOnce) {
            getAppExitTrigger().setValue(true);
        }

        this.doubleBackToExitPressedOnce = true;
        //TODO: get rid of this toast. put it inside baseactivity somehow
        Toast.makeText(getApplication(), "Click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void navButtonPressed(int itemId) {
        switch (itemId) {
            case R.id.bottomNav_discover:
                getNavEvent().setValue(Constants.OPENED_DISCOVER);
                break;
            case R.id.bottomNav_community:
                getNavEvent().setValue(Constants.OPENED_COMMUNITY);
                break;
            case R.id.bottomNav_library:
                getNavEvent().setValue(Constants.OPENED_LIBRARY);
                break;
            case R.id.bottomNav_personal:
                getNavEvent().setValue(Constants.OPENED_PERSONAL);
                break;
        }
    }
}
