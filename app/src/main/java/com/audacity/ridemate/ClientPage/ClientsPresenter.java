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
        repository.getClients(new ClientRepository.DataReadyListener() {
            @Override
            public void onDataReady(List<ClientData> clientDataList) {
                view.showClients(clientDataList);
            }
        });
    }

    @Override
    public void getClients() {
        getClientsFromRepo();
    }
}
