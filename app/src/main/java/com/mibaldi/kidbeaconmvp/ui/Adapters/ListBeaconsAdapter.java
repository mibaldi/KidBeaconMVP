package com.mibaldi.kidbeaconmvp.ui.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;

import java.util.List;

import javax.inject.Inject;

public class ListBeaconsAdapter extends RecyclerView.Adapter<ListBeaconsAdapter.ListBeaconsHolder>{

    private List<OwnBeacon> listItem;
    private  OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, OwnBeacon item);
    }

    @Inject
    public ListBeaconsAdapter() {

    }

    @Override
    public ListBeaconsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beaconlist,parent,false);
        return new ListBeaconsHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(ListBeaconsHolder holder, int position) {
        holder.bindItem(listItem.get(position));
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void setDataAndListener(List<OwnBeacon> ownBeaconList, OnItemClickListener listener) {
        this.listItem = ownBeaconList;
        this.listener = listener;
        this.notifyDataSetChanged();
    }

    public class ListBeaconsHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public Context context;
        public ListBeaconsAdapter.OnItemClickListener listener;
        public ListBeaconsHolder(View itemView, ListBeaconsAdapter.OnItemClickListener listener) {
            super(itemView);
            context = itemView.getContext();
            name = (TextView) itemView.findViewById(R.id.groupName);
            this.listener = listener;
        }

        public void bindItem(final OwnBeacon ownBeacon) {
            name.setText(ownBeacon.name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(itemView, ownBeacon);
                }
            });
        }

    }
}
