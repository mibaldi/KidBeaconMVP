package com.mibaldi.kidbeaconmvp.features.GroupSingle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.kidbeaconmvp.Base.BasePresenter;
import com.mibaldi.kidbeaconmvp.Navigation.Navigator;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;
import com.mibaldi.kidbeaconmvp.ui.Views.GroupSingleView;
import com.mibaldi.kidbeaconmvp.ui.Views.ListBeaconsView;

import javax.inject.Inject;

@PerActivity
public class GroupSinglePresenter extends BasePresenter<GroupSingleView> {
    Navigator navigator;
    OwnGroup ownGroup;
    @Inject
    public GroupSinglePresenter(Navigator navigator) {
        this.navigator= navigator;
    }


    public void init(OwnGroup ownGroup) {
        this.ownGroup = ownGroup;
        getView().showGroupName(ownGroup.name);
    }
    public void goToListBeacon(){
        navigator.goToListBeacons(ownGroup);
    }
    public void goToListBeaconsRastreator(){
        navigator.goToListBeaconsRastreator(ownGroup);
    }


}
