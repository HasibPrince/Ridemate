package com.audacity.ridemate.ClientPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.audacity.ridemate.Model.ClientData;
import com.audacity.ridemate.R;
import com.audacity.ridemate.Utils.FcsImageLoader;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Prince on 6/5/17.
 */

public class ClientAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ClientData> clientDataList;
    FcsImageLoader fcsImageLoader;

    public ClientAdapter(Context context, List<ClientData> clientDataList){
        this.context = context;
        this.clientDataList = clientDataList;
        fcsImageLoader = new FcsImageLoader();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.client_item_layout, null);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ClientViewHolder viewHolder = (ClientViewHolder) holder;
            ClientData clientData = clientDataList.get(position);
            fcsImageLoader.displayImage(clientData.getClient().getLogo(),viewHolder.logo);
            viewHolder.name.setText(clientData.getClient().getName());
            viewHolder.company.setText(clientData.getClient().getCompany());
            viewHolder.country.setText(clientData.getClient().getCountry());
    }

    @Override
    public int getItemCount() {
        return clientDataList.size();
    }

    public void setClientDataList(List<ClientData> clientDataList){
        this.clientDataList = clientDataList;
        notifyDataSetChanged();

    }

    public class ClientViewHolder extends RecyclerView.ViewHolder {
        private ImageView logo;
        private TextView name, company, country;
        public ClientViewHolder(View itemView) {
            super(itemView);
            logo = (ImageView) itemView.findViewById(R.id.logo);
            name = (TextView) itemView.findViewById(R.id.name);
            company = (TextView) itemView.findViewById(R.id.company);
            country = (TextView) itemView.findViewById(R.id.country);
        }
    }
}
