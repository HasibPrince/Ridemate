package com.audacity.ridemate.Model;

import com.audacity.ridemate.Model.LocalModel.Client;
import com.audacity.ridemate.Model.LocalModel.Tag;

import java.util.List;

/**
 * Created by Prince on 6/4/17.
 */

public class ClientData {
    private Client client;
    private List<Tag> tag;

    public ClientData(Client client, List<Tag> tag){
        this.client = client;
        this.tag = tag;
    }

    public Client getClient() {
        return client;
    }

    public List<Tag> getTag() {
        return tag;
    }
}
