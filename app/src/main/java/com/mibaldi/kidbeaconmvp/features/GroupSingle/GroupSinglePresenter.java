package com.mibaldi.kidbeaconmvp.features.GroupSingle;

import com.mibaldi.kidbeaconmvp.base.BasePresenter;
import com.mibaldi.kidbeaconmvp.navigation.Navigator;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.repositories.GroupsRepository;
import com.mibaldi.kidbeaconmvp.ui.Views.GroupSingleView;

import javax.inject.Inject;

@PerActivity
public class GroupSinglePresenter extends BasePresenter<GroupSingleView> {
    Navigator navigator;
    OwnGroup ownGroup;
    @Inject
    GroupsRepository groupsRepository;
    @Inject
    public GroupSinglePresenter(Navigator navigator) {
        this.navigator= navigator;
    }


    public void init() {
        this.ownGroup = groupsRepository.getCurrentOwnGroup();
        getView().showGroupName(ownGroup.name);
    }
    public void goToListBeacon(){
        navigator.goToListBeacons(ownGroup);
    }
    public void goToListBeaconsRastreator(){
        navigator.goToListBeaconsRastreator(ownGroup);
    }


}
