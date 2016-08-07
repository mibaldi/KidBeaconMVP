package com.mibaldi.kidbeaconmvp.ui.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;

import java.util.List;

import javax.inject.Inject;

public class GroupsListAdapter extends RecyclerView.Adapter<GroupsListAdapter.GroupsListHolder>{

    private List<OwnGroup> listItem;
    private  OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, OwnGroup item);
    }


    @Inject
    public GroupsListAdapter() {

    }

    @Override
    public GroupsListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grouplist,parent,false);
        return new GroupsListHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(GroupsListHolder holder, int position) {
        holder.bindItem(listItem.get(position));
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void setDataAndListener(List<OwnGroup> ownGroupList, OnItemClickListener listener) {
        this.listItem = ownGroupList;
        this.listener = listener;
        this.notifyDataSetChanged();
    }


    public class GroupsListHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public Context context;
        public GroupsListAdapter.OnItemClickListener listener;
        public GroupsListHolder(View itemView, GroupsListAdapter.OnItemClickListener listener) {
            super(itemView);
            context = itemView.getContext();
            name = (TextView) itemView.findViewById(R.id.groupName);
            this.listener = listener;
        }

        public void bindItem(final OwnGroup ownGroup) {
            name.setText(ownGroup.name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(itemView, ownGroup);
                }
            });
        }

    }
}
