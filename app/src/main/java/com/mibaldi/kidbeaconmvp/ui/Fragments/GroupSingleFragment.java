package com.mibaldi.kidbeaconmvp.ui.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mibaldi.kidbeaconmvp.base.BaseMVPFragment;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.features.GroupSingle.GroupSingleComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSingle.GroupSinglePresenter;
import com.mibaldi.kidbeaconmvp.ui.Views.GroupSingleView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GroupSingleFragment extends BaseMVPFragment<GroupSinglePresenter,GroupSingleView>  implements GroupSingleView {
    private GroupSingleComponent component;
    private Unbinder unbind;
    @Inject
    public GroupSingleFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static GroupSingleFragment newInstance(OwnGroup ownGroup) {
        GroupSingleFragment fragment = new GroupSingleFragment();
        Bundle args = new Bundle();
        args.putParcelable("ownGroup", ownGroup);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OwnGroup ownGroup = getArguments().getParcelable("ownGroup");
        presenter.init();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        component = getComponent(GroupSingleComponent.class);
        component.inject(this);
        View view = inflater.inflate(R.layout.fragment_group_single,container,false);
        unbind = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }

    @Override
    public GroupSinglePresenter createPresenter() {
        return component.presenter();
    }

    @Override
    public void showGroupName(String name) {
        //groupName.setText(name);
    }
    @OnClick(R.id.beaconList)
    public void goToBeaconList(){
        presenter.goToListBeacon();
    }
    @OnClick(R.id.beaconRastreator)
    public void goToBeaconRastreator(){
        presenter.goToListBeaconsRastreator();
    }

}
