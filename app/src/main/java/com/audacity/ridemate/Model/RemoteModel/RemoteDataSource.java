package com.audacity.ridemate.Model.RemoteModel;

import android.util.Log;

import com.audacity.ridemate.Model.DataFetchedListener;
import com.audacity.ridemate.Model.DataSource;
import com.audacity.ridemate.Rest.ApiClient;
import com.audacity.ridemate.Rest.ApiInterface;
import com.google.firebase.crash.FirebaseCrash;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Prince on 6/4/17.
 */

public class RemoteDataSource implements DataSource<List<Client>>{
    private static final String TAG = RemoteDataSource.class.getName();
    private static RemoteDataSource instance;

    public static RemoteDataSource getInstance() {
        if(instance == null){
            instance = new RemoteDataSource();
        }
        return instance;
    }

    private RemoteDataSource(){

    }

    @Override
    public void getData(final DataFetchedListener<List<Client>> dataFetchedListener) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ClientResponse> call = apiService.getClients("32DFCFD@#&DSFDSFSDF!L@?hh7@32DF");

        call.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                dataFetchedListener.onDtaFetched(response.body().getClients());
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                Log.d(TAG, t.toString());
                FirebaseCrash.report(new Exception(t.getMessage()));
            }
        });

    }
}
