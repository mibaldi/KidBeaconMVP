package com.mibaldi.kidbeaconmvp.ui.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mibaldi.kidbeaconmvp.Base.BaseMVPFragment;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.features.ListGroups.ListGroupsComponent;
import com.mibaldi.kidbeaconmvp.features.ListGroups.ListGroupsPresenter;
import com.mibaldi.kidbeaconmvp.ui.Views.ListGroupsView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListGroupsFragment extends BaseMVPFragment<ListGroupsPresenter,ListGroupsView>  implements ListGroupsView {
    private ListGroupsComponent component;
    private Unbinder unbind;

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

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        component = getComponent(ListGroupsComponent.class);
        component.inject(this);
        View view = inflater.inflate(R.layout.fragment_group_list,container,false);
        unbind = ButterKnife.bind(this,view);
        return view;
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
}
