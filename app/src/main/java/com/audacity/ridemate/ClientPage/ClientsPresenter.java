package com.audacity.ridemate.ClientPage;

import com.audacity.ridemate.Model.ClientData;
import com.audacity.ridemate.Model.ClientRepository;

import java.util.List;

/**
 * Created by Prince on 6/5/17.
 */

public class ClientsPresenter implements ClientPageContract.Presenter {
    private ClientPageContract.View view;
    private ClientRepository repository;

    public ClientsPresenter(ClientPageContract.View view, ClientRepository repository){
        this.view = view;
        this.repository = repository;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        getClientsFromRepo();
    }

    private void getClientsFromRepo() {
        view.showLoader();

        repository.getClients(new ClientRepository.DataReadyListener() {
            @Override
            public void onDataReady(List<ClientData> clientDataList) {
                if(!clientDataList.isEmpty()) {
                    view.hideLoader();
                    view.showClients(clientDataList);
                }
            }

            @Override
            public void onDataError(String message) {
                view.showErrorMessage(message);
            }
        });
    }

    @Override
    public void getClients() {
        getClientsFromRepo();
    }
}
