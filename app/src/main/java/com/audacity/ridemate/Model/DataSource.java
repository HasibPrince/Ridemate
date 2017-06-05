package com.audacity.ridemate.Model;

/**
 * Created by Prince on 6/4/17.
 */

public interface DataSource<T> {
    void getData(DataFetchedListener<T> dataFetchedListener);
}
