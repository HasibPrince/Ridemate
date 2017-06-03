package com.audacity.ridemate.ConfirmationPage;

import android.support.annotation.NonNull;

import com.audacity.ridemate.Model.Ride;
import com.audacity.ridemate.Model.RideDAO;

/**
 * Created by Prince on 6/1/17.
 */

public class MapPresenter implements MapFragmentContract.Presenter {


    MapFragmentContract.View mapView;
    private RideDAO dao;

    public MapPresenter(@NonNull MapFragmentContract.View mapView){
        this.mapView = mapView;
        mapView.setPresenter(this);
    }

    @Override
    public void start() {
        dao = RideDAO.GetInstance();
    }

    @Override
    public void getRideDataById(int id) {
        Ride ride = dao.getRideById(id);
        mapView.showRideInfo(ride);
    }
}
