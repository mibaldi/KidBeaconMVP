package com.mibaldi.kidbeaconmvp.ui.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mibaldi.kidbeaconmvp.Base.BaseMVPFragment;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.features.ListBeacons.ListBeaconsComponent;
import com.mibaldi.kidbeaconmvp.features.ListBeacons.ListBeaconsPresenter;
import com.mibaldi.kidbeaconmvp.features.ListGroups.ListGroupsComponent;
import com.mibaldi.kidbeaconmvp.features.ListGroups.ListGroupsPresenter;
import com.mibaldi.kidbeaconmvp.ui.Views.ListBeaconsView;
import com.mibaldi.kidbeaconmvp.ui.Views.ListGroupsView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListBeaconsFragment extends BaseMVPFragment<ListBeaconsPresenter,ListBeaconsView>  implements ListBeaconsView {
    private ListBeaconsComponent component;
    private Unbinder unbind;

    @Inject
    public ListBeaconsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        component = getComponent(ListBeaconsComponent.class);
        component.inject(this);
        View view = inflater.inflate(R.layout.fragment_beacon_list,container,false);
        unbind = ButterKnife.bind(this,view);
        return view;
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
}
