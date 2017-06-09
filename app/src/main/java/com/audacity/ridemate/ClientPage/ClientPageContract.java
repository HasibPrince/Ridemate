package com.audacity.ridemate.ClientPage;

import com.audacity.ridemate.BasePresenter;
import com.audacity.ridemate.BaseView;
import com.audacity.ridemate.ConfirmationPage.MapFragmentContract;
import com.audacity.ridemate.Model.ClientData;

import java.util.List;

/**
 * Created by Prince on 6/5/17.
 */

public interface ClientPageContract {
    public interface View extends BaseView<Presenter>{
        void showLoader();
        void hideLoader();
        void showClients(List<ClientData> clientDataList);
        void showErrorMessage(String message);
    }

    public interface Presenter extends BasePresenter{
        void getClients();
    }
}
