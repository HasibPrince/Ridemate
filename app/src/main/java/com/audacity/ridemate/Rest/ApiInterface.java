package com.audacity.ridemate.Rest;

import com.audacity.ridemate.Model.RemoteModel.ClientResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("profile/api/v2/client")
    Call<ClientResponse> getClients(@Header("authorization") String auth);
}
