package com.audacity.ridemate.Model.RemoteModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mdhasib on 6/4/17.
 */

public class ClientResponse {
    @SerializedName("client")
    private List<Client> clients;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
