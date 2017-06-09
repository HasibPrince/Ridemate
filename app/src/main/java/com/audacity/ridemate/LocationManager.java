package com.audacity.ridemate;

import android.*;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.audacity.ridemate.ClientPage.ClientPageContract;
import com.audacity.ridemate.Utils.Utils;
import com.google.firebase.crash.FirebaseCrash;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Exchanger;

/**
 * Created by Prince on 6/1/17.
 */

public class LocationManager implements LocationListener{

    private final LocationChangeListener listener;
    private Context context;
    private android.location.LocationManager locationManager;

    public LocationManager(Context context,LocationChangeListener listener){
        this.context = context;
        this.listener = listener;
        locationManager = (android.location.LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        Utils.checkLocationPermission((Activity) context);
        this.locationManager.requestLocationUpdates(getBestProvider(),2000,5,this);

    }

    public Location findCurrentLocation(){
        String bestProvider = getBestProvider();

        Utils.checkLocationPermission((Activity) context);

        Location location = locationManager.getLastKnownLocation(bestProvider);
        if(location!=null)
        Log.d(this.getClass().getName(), "Location: " + location.getProvider() + "==>>lat: " + location.getLatitude() + " lon: " + location.getLongitude());

        return location;
    }

    private String getBestProvider() {
        Criteria criteria = new Criteria();
        return locationManager.getBestProvider(criteria, true);
    }

    public String getAddress(Location location){
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrash.report(new Exception(e.getMessage()));
        }

        if(addresses!=null) {
            return addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        }
        return "";
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location!=null) {
            listener.onLocationChanged(location);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
