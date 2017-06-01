package com.audacity.ridemate.ConfirmationPage;

import com.audacity.ridemate.BasePresenter;
import com.audacity.ridemate.BaseView;

/**
 * Created by Prince on 5/31/17.
 */

public interface MapFragmentContract {

     interface View extends BaseView<Presenter>{
            void showALocationInMap(double lat, double lon, String address);
         
     }

     interface Presenter extends BasePresenter{
            void findLocation();
     }
}
