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
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.features.ListBeacons.ListBeaconsComponent;
import com.mibaldi.kidbeaconmvp.features.ListBeacons.ListBeaconsPresenter;
import com.mibaldi.kidbeaconmvp.ui.Adapters.ListBeaconsAdapter;
import com.mibaldi.kidbeaconmvp.ui.Views.ListBeaconsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;

public class ListBeaconsFragment extends BaseMVPFragment<ListBeaconsPresenter,ListBeaconsView>  implements ListBeaconsView {
    private ListBeaconsComponent component;
    private Unbinder unbind;
    private List<OwnBeacon> ownBeaconList = new ArrayList<>();
    @BindView(R.id.beaconsList)
    RecyclerView recipe_recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    ListBeaconsAdapter listBeaconsAdapter;
    private SpotsDialog dialog;
    @Inject
    public ListBeaconsFragment() {
        setRetainInstance(true);
    }

    public static ListBeaconsFragment newInstance(OwnGroup ownGroup) {
        ListBeaconsFragment fragment = new ListBeaconsFragment();
        Bundle args = new Bundle();
        args.putParcelable("ownGroup", ownGroup);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OwnGroup ownGroup = getArguments().getParcelable("ownGroup");
        loadSwipeRefreshLayout();
        createLoadingDialog();
        presenter.init(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        component = getComponent(ListBeaconsComponent.class);
        component.inject(this);
        View view = inflater.inflate(R.layout.fragment_beacon_list,container,false);
        unbind = ButterKnife.bind(this,view);
        recipe_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
    private void loadSwipeRefreshLayout() {

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ownBeaconList.clear();
                createLoadingDialog();
                presenter.loadBeaconsService();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }

    @Override
    public ListBeaconsPresenter createPresenter() {
        return component.presenter();
    }

    private void createLoadingDialog() {
        dialog = new SpotsDialog(getActivity(),"Cargando beacons");
    }

    @Override
    public void showOwnBeaconList(List<OwnBeacon> recipeList) {
        this.ownBeaconList = recipeList;
        listBeaconsAdapter.setDataAndListener(ownBeaconList, new ListBeaconsAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, OwnBeacon item) {
                presenter.loadOwnBeacon(item);
            }
        });
        recipe_recyclerView.setAdapter(listBeaconsAdapter);
    }

    @Override
    public void swipeRefresh(Boolean b) {
        mSwipeRefreshLayout.setRefreshing(b);
    }

    public void showDialog(Boolean b) {
        if (b){
            dialog.show();
        }else{
            dialog.hide();
        }
    }
    @OnClick(R.id.fab2)
    public void addBeacon(){
        presenter.goToBeaconSettings();
    }
    @OnClick(R.id.fab3)
    public void addBeaconNFC(){
        presenter.goToNfc();

    }
}
