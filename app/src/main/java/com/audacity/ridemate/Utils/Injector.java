package com.audacity.ridemate.Utils;

import com.audacity.ridemate.Model.DataSource;
import com.audacity.ridemate.Model.ClientRepository;
import com.audacity.ridemate.Model.LocalModel.ILocalDataSource;
import com.audacity.ridemate.Model.LocalModel.LocalDataSource;
import com.audacity.ridemate.Model.RemoteModel.RemoteDataSource;

/**
 * Created by Prince on 6/5/17.
 */

public class Injector {

    public static LocalDataSource getLocalDataSource(){
        return LocalDataSource.getInstance();
    }

    public static RemoteDataSource getRemoteDataSource(){
        return RemoteDataSource.getInstance();
    }

    public static ClientRepository getClientRepository(ILocalDataSource localDataSource, DataSource remoteDataSource){
        return new ClientRepository(localDataSource,remoteDataSource);
    }
}
