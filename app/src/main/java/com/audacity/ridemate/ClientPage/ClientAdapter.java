package com.audacity.ridemate.ClientPage;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.audacity.ridemate.Model.ClientData;
import com.audacity.ridemate.Model.LocalModel.Tag;
import com.audacity.ridemate.R;
import com.audacity.ridemate.Utils.FcsImageLoader;
import com.audacity.ridemate.Utils.ViewUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

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

    public ClientAdapter(Context context, List<ClientData> clientDataList) {
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
        final ClientViewHolder viewHolder = (ClientViewHolder) holder;
        ClientData clientData = clientDataList.get(position);

        Glide.with(context)
                .load(clientData.getClient().getLogo())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.logo);

        viewHolder.name.setText(clientData.getClient().getName());
        viewHolder.company.setText(clientData.getClient().getCompany());
        viewHolder.country.setText(clientData.getClient().getCountry());

        viewHolder.tagContainer.removeAllViewsInLayout();
        bindTags(viewHolder, clientData);

        setupExpandableLayout(viewHolder);

        viewHolder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                viewHolder.expandableLayout.toggle();
            }
        });
    }

    private void setupExpandableLayout(final ClientViewHolder viewHolder) {
        viewHolder.expandableLayout.setInRecyclerView(true);
        viewHolder.expandableLayout.setInterpolator(new FastOutLinearInInterpolator());

        viewHolder.expandableLayout.setListener(new ExpandableLayoutListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {
                setButtonLayoutRotation(viewHolder);
            }

            @Override
            public void onPreOpen() {

            }

            @Override
            public void onPreClose() {

            }

            @Override
            public void onOpened() {

            }

            @Override
            public void onClosed() {

            }
        });
    }

    private void bindTags(ClientViewHolder viewHolder, ClientData clientData) {
        for (Tag tag : clientData.getTag()) {
            TextView textView = new TextView(context);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) 100, (int) 50);
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.leftMargin = ViewUtils.dpToPx(context, 5);
            int padding = ViewUtils.dpToPx(context, 5);
            textView.setPadding(padding, padding, padding, padding);

            textView.setLayoutParams(layoutParams);
            textView.setText(tag.getTag());
            textView.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.rounded_shape, null));
            viewHolder.tagContainer.addView(textView);

        }
    }

    private void setButtonLayoutRotation(ClientViewHolder viewHolder) {
        boolean isExpaned = viewHolder.expandableLayout.isExpanded();
        viewHolder.buttonLayout.setRotation(isExpaned ? 180f : 0f);
    }

    @Override
    public int getItemCount() {
        return clientDataList.size();
    }

    public void setClientDataList(List<ClientData> clientDataList) {
        this.clientDataList = clientDataList;
        notifyDataSetChanged();

    }

    public class ClientViewHolder extends RecyclerView.ViewHolder {
        private ImageView logo;
        private TextView name, company, country;
        public RelativeLayout buttonLayout;
        public ExpandableLinearLayout expandableLayout;
        private LinearLayout tagContainer;

        public ClientViewHolder(View itemView) {
            super(itemView);
            logo = (ImageView) itemView.findViewById(R.id.logo);
            name = (TextView) itemView.findViewById(R.id.name);
            company = (TextView) itemView.findViewById(R.id.company);
            country = (TextView) itemView.findViewById(R.id.country);
            buttonLayout = (RelativeLayout) itemView.findViewById(R.id.button);
            expandableLayout = (ExpandableLinearLayout) itemView.findViewById(R.id.expandableLayout);
            tagContainer = (LinearLayout) itemView.findViewById(R.id.tagContainer);
        }
    }
}
