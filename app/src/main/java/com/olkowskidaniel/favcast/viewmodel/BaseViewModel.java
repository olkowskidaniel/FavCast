package com.olkowskidaniel.favcast.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.olkowskidaniel.favcast.R;
import com.olkowskidaniel.favcast.util.Constants;
import com.olkowskidaniel.favcast.view.base.community.CommunityFragment;
import com.olkowskidaniel.favcast.view.base.discover.DiscoverFragment;
import com.olkowskidaniel.favcast.view.base.library.LibraryFragment;
import com.olkowskidaniel.favcast.view.base.personal.PersonalFragment;
import com.olkowskidaniel.favcast.view.main.MainActivity;

public class BaseViewModel extends AndroidViewModel {

    private boolean doubleBackToExitPressedOnce;

    public BaseViewModel(Application application) {
        super(application);
    }

    private MutableLiveData<Integer> navEvent;

    public MutableLiveData<Integer> getNavEvent() {
        if (navEvent == null) {
            navEvent = new MutableLiveData<Integer>();
        }
        return navEvent;
    }

    public void backButtonPressed(Context context) {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            context.startActivity(intent);
        }

        this.doubleBackToExitPressedOnce = true;
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
