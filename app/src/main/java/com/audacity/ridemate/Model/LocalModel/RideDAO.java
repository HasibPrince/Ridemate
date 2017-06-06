package com.audacity.ridemate.Model.LocalModel;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prince on 6/3/17.
 */

public class RideDAO {

    private List<Ride> rides = new ArrayList<>();

    private static RideDAO instance = new RideDAO();

    public static RideDAO GetInstance(){
        return instance;
    }

    private RideDAO(){
        rides.add(new Ride("10 min", "1.0x","4 People"));
        rides.add(new Ride("12 min", "1.0x","8 People"));
        rides.add(new Ride("6 min", "2.1x","6 People"));
        rides.add(new Ride("12 min", "1.5x","2 People"));
        rides.add(new Ride("10 min", "2.5x","5 People"));
    }

    @Nullable
    public Ride getRideById(int id){
        if(id > rides.size()-1){
            return null;
        }
        return rides.get(id);
    }
}
