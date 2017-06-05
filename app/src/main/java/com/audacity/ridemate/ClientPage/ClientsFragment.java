package com.audacity.ridemate.ClientPage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.audacity.ridemate.Model.ClientData;
import com.audacity.ridemate.Model.LocalModel.Client;
import com.audacity.ridemate.R;

import java.util.ArrayList;
import java.util.List;

public class ClientsFragment extends Fragment implements ClientPageContract.View{

    private RecyclerView clientsRecyclerView;
    private ClientAdapter clientAdapter;
    private ClientPageContract.Presenter presenter;
    public ClientsFragment() {
        // Required empty public constructor
    }

    public static ClientsFragment newInstance(String param1, String param2) {
        ClientsFragment fragment = new ClientsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clients, container, false);
        clientsRecyclerView = (RecyclerView) view.findViewById(R.id.clientsView);
        clientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        clientAdapter = new ClientAdapter(getContext(), new ArrayList<ClientData>());
        clientsRecyclerView.setAdapter(clientAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setPresenter(ClientPageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoader() {

    }

    @Override
    public void hideLoader() {

    }

    @Override
    public void showClients(List<ClientData> clientDataList) {
        clientAdapter.setClientDataList(clientDataList);
    }
}
