package com.audacity.ridemate.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.annotation.NonNull;

/**
 * Created by Prince on 5/29/17.
 */

public class ViewUtils {
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        //checkNotNull(fragmentManager);
        //checkNotNull(fragment);
        fragmentManager.beginTransaction()
                .replace(frameId,fragment,null)
                //.add(frameId,fragment)
                .addToBackStack(null)
                .commit();

    }
}
