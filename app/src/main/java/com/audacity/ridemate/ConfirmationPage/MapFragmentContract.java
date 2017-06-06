package com.audacity.ridemate.ConfirmationPage;

import com.audacity.ridemate.BasePresenter;
import com.audacity.ridemate.BaseView;
import com.audacity.ridemate.Model.LocalModel.Ride;

/**
 * Created by Prince on 5/31/17.
 */

public interface MapFragmentContract {

     interface View extends BaseView<Presenter>{
            void showRideInfo(Ride ride);
     }

     interface Presenter extends BasePresenter{

         void getRideDataById(int id);
     }
}
