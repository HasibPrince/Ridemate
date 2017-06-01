package com.audacity.ridemate;

/**
 * Created by Prince on 5/31/17.
 */

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
