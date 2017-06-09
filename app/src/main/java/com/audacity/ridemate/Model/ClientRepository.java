package com.audacity.ridemate.Model;

import com.audacity.ridemate.Model.ClientData;
import com.audacity.ridemate.Model.DataFetchedListener;
import com.audacity.ridemate.Model.DataSource;
import com.audacity.ridemate.Model.LocalModel.ILocalDataSource;
import com.audacity.ridemate.Model.RemoteModel.*;
import com.audacity.ridemate.Model.RemoteModel.Client;

import java.util.List;

/**
 * Created by Prince on 6/4/17.
 */

public class ClientRepository {
    private ILocalDataSource localDataSource;
    private DataSource remoteDataSource;

    public ClientRepository(ILocalDataSource localDataSource, DataSource remoteDataSource){
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public void getClients(final DataReadyListener dataReadyListener){
        fetchFromLocalDataSource(dataReadyListener);

         if(!localDataSource.doesDataExist()){
             fetchFromRemoteDataSource(dataReadyListener);
         }
    }

    private void fetchFromRemoteDataSource(final DataReadyListener dataReadyListener) {
        remoteDataSource.getData(new DataFetchedListener<List<Client>>() {
            @Override
            public void onDtaFetched(List<Client> data) {
                localDataSource.save(data);
                fetchFromLocalDataSource(dataReadyListener);
            }

            @Override
            public void onError(String message) {
                dataReadyListener.onDataError(message);
            }

        });
    }

    private void fetchFromLocalDataSource(final DataReadyListener dataReadyListener) {
        localDataSource.getData(new DataFetchedListener<List<ClientData>>() {
            @Override
            public void onDtaFetched(List<ClientData> data) {
                dataReadyListener.onDataReady(data);
            }

            @Override
            public void onError(String message) {
                dataReadyListener.onDataError(message);
            }

        });
    }

    public interface DataReadyListener{
        void onDataReady(List<ClientData> clientDataList);
        void onDataError(String message);
    }

}
