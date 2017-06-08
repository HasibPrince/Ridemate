package com.audacity.ridemate;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by Prince on 6/5/17.
 */

public class RidemateApplication extends Application {

    private static FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    public static FirebaseAnalytics getmFirebaseAnalytics() {
        return mFirebaseAnalytics;
    }
}
