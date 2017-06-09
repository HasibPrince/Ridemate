package com.audacity.ridemate.ClientPage;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.audacity.ridemate.Model.ClientData;
import com.audacity.ridemate.Model.LocalModel.Client;
import com.audacity.ridemate.R;

import java.util.ArrayList;
import java.util.List;

public class ClientsFragment extends Fragment implements ClientPageContract.View{

    private RecyclerView clientsRecyclerView;
    private ClientAdapter clientAdapter;
    private ClientPageContract.Presenter presenter;
    private ProgressDialog dialog;
    private FrameLayout parentLayout;

    public ClientsFragment() {
    }

    public static ClientsFragment newInstance() {
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
        View view = inflater.inflate(R.layout.fragment_clients, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        parentLayout = (FrameLayout) view.findViewById(R.id.parent);

        clientAdapter = new ClientAdapter(getContext(), new ArrayList<ClientData>());

        clientsRecyclerView = (RecyclerView) view.findViewById(R.id.clientsView);
        clientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        clientsRecyclerView.setAdapter(clientAdapter);

        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(ClientPageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoader() {
        dialog.show();
    }

    @Override
    public void hideLoader() {
        dialog.hide();
    }

    @Override
    public void showClients(List<ClientData> clientDataList) {
        clientAdapter.setClientDataList(clientDataList);
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(parentLayout,message,Snackbar.LENGTH_LONG).show();
    }
}
