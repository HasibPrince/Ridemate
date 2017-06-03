package com.audacity.ridemate.Model;

/**
 * Created by Prince on 6/3/17.
 */

public class Ride {
    String time;
    String multiplier;
    String capacity;

    public Ride(String time, String multiplier, String capacity){
        this.time = time;
        this.multiplier = multiplier;
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(String multiplier) {
        this.multiplier = multiplier;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
