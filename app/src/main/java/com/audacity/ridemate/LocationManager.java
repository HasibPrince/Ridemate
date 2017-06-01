package com.audacity.ridemate;

import android.*;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Prince on 6/1/17.
 */

public class LocationManager {

    private Context context;
    private android.location.LocationManager locationManager;

    public LocationManager(Context context){
        this.context = context;
        locationManager = (android.location.LocationManager) context.getSystemService(context.LOCATION_SERVICE);
    }

    public Location findCurrentLocation(){
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return null;
        }

        Location location = locationManager.getLastKnownLocation(bestProvider);
        Log.d(this.getClass().getName(), "Location: " + location.getProvider() + "==>>lat: " + location.getLatitude() + " lon: " + location.getLongitude());
        return location;
    }

    public String getAddress(Location location){
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(addresses!=null) {
            return addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        }
        return "";
    }
}
