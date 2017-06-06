package com.audacity.ridemate.Model.LocalModel;

import com.audacity.ridemate.Model.ClientData;
import com.audacity.ridemate.Model.DataFetchedListener;
import com.audacity.ridemate.Model.DataSource;
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
            remoteDataSource.getData(new DataFetchedListener<List<com.audacity.ridemate.Model.RemoteModel.Client>>() {
                @Override
                public void onDtaFetched(List<Client> data) {
                    localDataSource.save(data);
                    fetchFromLocalDataSource(dataReadyListener);
                }

            });
        }


    }

    private void fetchFromLocalDataSource(final DataReadyListener dataReadyListener) {
        localDataSource.getData(new DataFetchedListener<List<ClientData>>() {
            @Override
            public void onDtaFetched(List<ClientData> data) {
                dataReadyListener.onDataReady(data);
            }

        });
    }

    public interface DataReadyListener{
        void onDataReady(List<ClientData> clientDataList);
    }

}
