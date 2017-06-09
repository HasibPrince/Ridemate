package com.audacity.ridemate.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.audacity.ridemate.RidemateApplication;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Prince on 6/5/17.
 */

public class Utils {
    public static boolean isFromLocalStorage(String filePath) { ///storage/emulated/0/FcDrawing1454406644872.jpg

        if (filePath == null)
            return false;

        return filePath.contains("storage");
    }

    public static void CopyStream(InputStream inputStream, OutputStream outputStream) {
        final int bufferSize = 1024;

        try {
            byte[] bytes = new byte[bufferSize];
            for (; ; ) {
                //Read byte from input stream
                int count = inputStream.read(bytes, 0, bufferSize);
                if (count == -1)
                    break;

                //Write byte from output stream
                outputStream.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }

    public static int getScreenWidthInPixels(Context context) {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        return screenWidth;
    }

    public static void checkLocationPermission(Activity context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            askForPermission(context);
        }
    }

    private static void askForPermission(Activity context) {
        ActivityCompat.requestPermissions( context, new String[] {  Manifest.permission.ACCESS_COARSE_LOCATION  },
                101 );
    }

    public static void logFirebaseAnalytics(String id, String name, String type){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, type);
        RidemateApplication.getmFirebaseAnalytics().logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}
