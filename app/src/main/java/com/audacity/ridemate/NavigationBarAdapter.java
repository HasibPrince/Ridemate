package com.audacity.ridemate;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.transition.Visibility;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by mdhasib on 4/5/17.
 */

public class NavigationBarAdapter extends RecyclerView.Adapter {
    private Context context;

    public NavigationBarAdapter(Context context){
        this.context = context;
    }
    private static int[] icons = new int[]{R.drawable.minivan, R.drawable.sub, R.drawable.micro, R.drawable.bike, R.drawable.nova};

    private static String[] titles = new String[]{"Minivan", "Sub", "Micro", "Bike", "Nova"};
    int selectedPosition = 0;
    private NavigationItemSelectedListener listener;
    public static enum NAVIGATION_ITEMS{
        SCOREBOARD,
        APPRAISALS,
        GOALS,
        CONTACTS,
        MORE
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.bar_item,null);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;

            if(position==selectedPosition){
                viewHolder.setImageIcon(ResourcesCompat.getDrawable(context.getResources(), icons[position], null));
                viewHolder.setTitle(titles[position]);
                //viewHolder.setSelectedTextColor();
                viewHolder.setVisibilityOfIndicator(View.VISIBLE);
            }else {
                viewHolder.setImageIcon(ResourcesCompat.getDrawable(context.getResources(), icons[position], null));
                viewHolder.setTitle(titles[position]);
                //viewHolder.setUnselectedTextColor();
                viewHolder.setVisibilityOfIndicator(View.INVISIBLE);
            }

            viewHolder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedPosition = position;
                    notifyDataSetChanged();

                    notifySelectedListener(position);
                }
            });

    }

    private void notifySelectedListener(int position) {
        if(listener!=null) {
            listener.onItemSelected(position);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public void setSelectedPosition(int position){
        this.selectedPosition = position;
        notifySelectedListener(position);
    }

    public void setOnNavigationItemSelectedListener(NavigationItemSelectedListener listener) {
        this.listener = listener;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{

        private ImageView itemIcon;
        private ImageView indicator;
        private TextView title;
        private RelativeLayout itemRoot;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            itemIcon = (ImageView)itemView.findViewById(R.id.icon);
            indicator = (ImageView) itemView.findViewById(R.id.indicator);
            title = (TextView) itemView.findViewById(R.id.title);
            itemRoot = (RelativeLayout) itemView.findViewById(R.id.item_root);
        }

        private void setImageIcon(Drawable drawable) {
            itemIcon.setImageDrawable(drawable);
        }

        private void setVisibilityOfIndicator(int visibility){
            indicator.setVisibility(visibility);
        }

        private void setTitle(String titleText){
            title.setText(titleText);
        }
//
//        private void setSelectedTextColor(){
//            title.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.wallet_holo_blue_light,null));
//        }
//        private void setUnselectedTextColor(){
//            title.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.darkgray,null));
//        }
    }

    public interface NavigationItemSelectedListener{
        void onItemSelected(int id);
    }
}
