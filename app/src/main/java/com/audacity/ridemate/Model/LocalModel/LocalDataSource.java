package com.audacity.ridemate.Model.LocalModel;

import com.audacity.ridemate.Model.ClientData;
import com.audacity.ridemate.Model.DataFetchedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prince on 6/4/17.
 */

public class LocalDataSource implements ILocalDataSource<List<com.audacity.ridemate.Model.RemoteModel.Client>> {

    private static LocalDataSource instance;

    public static LocalDataSource getInstance(){
        if(instance == null){
            instance = new LocalDataSource();
        }
        return instance;
    }

    private LocalDataSource(){

    }

    @Override
    public void getData(DataFetchedListener<List<ClientData>> dataFetchedListener) {
        List<ClientData> clientDataList =  createClientDataListFromClients();
        dataFetchedListener.onDtaFetched(clientDataList);
    }

    private List<ClientData> createClientDataListFromClients() {
        List<ClientData> clientDataList = new ArrayList<>();

        for(Client client : Client.getClients()){
            List<Tag> tags = Tag.getTagByCompany(client.getCompany());
            ClientData clientData = new ClientData(client, tags);
            clientDataList.add(clientData);
        }

        return clientDataList;
    }

    @Override
    public boolean doesDataExist() {
        return Client.getClients().size() > 0;
    }


    @Override
    public void save(List<com.audacity.ridemate.Model.RemoteModel.Client> clientList) {
        Client.clear();
        Tag.clear();

        for(com.audacity.ridemate.Model.RemoteModel.Client remoteClient : clientList) {
            saveClient(remoteClient);

            for (com.audacity.ridemate.Model.RemoteModel.Tag tag : remoteClient.getTags()) {
                saveTag(remoteClient, tag);
            }
        }

    }

    private void saveTag(com.audacity.ridemate.Model.RemoteModel.Client remoteClient, com.audacity.ridemate.Model.RemoteModel.Tag tag) {
        Tag tagDB = new Tag();
        tagDB.setCompany(remoteClient.getCompany());
        tagDB.setTag(tag.getTag());
        tagDB.setUrl(tag.getUrl());
        tagDB.save();
    }

    private void saveClient(com.audacity.ridemate.Model.RemoteModel.Client clientData) {
        Client client = new Client();
        client.setName(clientData.getName());
        client.setCompany(clientData.getCompany());
        client.setLogo(clientData.getLogo());
        client.setCountry(clientData.getCountry());
        client.save();
    }
}

