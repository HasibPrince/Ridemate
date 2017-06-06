package com.audacity.ridemate;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Prince on 6/5/17.
 */

public class RidemateApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
