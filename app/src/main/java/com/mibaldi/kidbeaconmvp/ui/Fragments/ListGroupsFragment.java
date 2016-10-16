package com.mibaldi.kidbeaconmvp.ui.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mibaldi.kidbeaconmvp.base.BaseMVPFragment;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.features.ListGroups.ListGroupsComponent;
import com.mibaldi.kidbeaconmvp.features.ListGroups.ListGroupsPresenter;
import com.mibaldi.kidbeaconmvp.ui.Adapters.GroupsListAdapter;
import com.mibaldi.kidbeaconmvp.ui.Views.ListGroupsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;

public class ListGroupsFragment extends BaseMVPFragment<ListGroupsPresenter, ListGroupsView> implements ListGroupsView {
    private ListGroupsComponent component;
    private Unbinder unbind;
    private List<OwnGroup> ownGroupsList = new ArrayList<>();
    @BindView(R.id.groupsList)
    RecyclerView recipe_recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Inject
    GroupsListAdapter groupsListAdapter;
    private SpotsDialog dialog;

    @Inject
    public ListGroupsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadSwipeRefreshLayout();
        createLoadingDialog();
        presenter.init(getActivity(),getActivity());
        //loadSwipeRefreshLayout();
    }

    private void createLoadingDialog() {
        dialog = new SpotsDialog(getActivity(),"Cargando grupos");

    }
    public void showDialog(Boolean b) {
        if (b){
            dialog.show();
        }else{
            dialog.hide();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        component = getComponent(ListGroupsComponent.class);
        component.inject(this);
        View view = inflater.inflate(R.layout.fragment_group_list, container, false);
        unbind = ButterKnife.bind(this, view);
        recipe_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }

    private void loadSwipeRefreshLayout() {

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ownGroupsList.clear();
                createLoadingDialog();
                presenter.loadGroupsService();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }

    @Override
    public ListGroupsPresenter createPresenter() {
        return component.presenter();
    }

    @Override
    public void showOwnGroupsList(List<OwnGroup> ownGroupList) {
        this.ownGroupsList = ownGroupList;
        groupsListAdapter.setDataAndListener(ownGroupsList, new GroupsListAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, OwnGroup item) {
                presenter.loadOwnGroup(item);
            }
        });
        recipe_recyclerView.setAdapter(groupsListAdapter);
    }

    @Override
    public void swipeRefresh(Boolean b) {
        mSwipeRefreshLayout.setRefreshing(b);
    }

    @OnClick(R.id.addGroup)
    public void addGroup(){
        presenter.goToGroupSettings(null);
    }
}
