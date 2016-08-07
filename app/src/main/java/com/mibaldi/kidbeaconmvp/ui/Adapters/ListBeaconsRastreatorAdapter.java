package com.mibaldi.kidbeaconmvp.ui.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;

import java.util.List;

import javax.inject.Inject;

public class ListBeaconsRastreatorAdapter extends RecyclerView.Adapter<ListBeaconsRastreatorAdapter.ListBeaconsRastreatorHolder>{

    private List<OwnBeacon> listItem;

    @Inject
    public ListBeaconsRastreatorAdapter() {

    }

    @Override
    public ListBeaconsRastreatorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beaconlist_rastreator,parent,false);
        return new ListBeaconsRastreatorHolder(view);
    }

    @Override
    public void onBindViewHolder(ListBeaconsRastreatorHolder holder, int position) {
        holder.bindItem(listItem.get(position));
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void setDataAndListener(List<OwnBeacon> ownBeaconRastreatorList) {
        this.listItem = ownBeaconRastreatorList;
        this.notifyDataSetChanged();
    }

    public class ListBeaconsRastreatorHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView distance;
        public Context context;

        public ListBeaconsRastreatorHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = (TextView) itemView.findViewById(R.id.beaconName);
            distance = (TextView) itemView.findViewById(R.id.beaconDistance);
        }

        public void bindItem(final OwnBeacon ownBeacon) {
            name.setText(ownBeacon.name);
            distance.setText(String.valueOf(ownBeacon.distance));
        }

    }
}
