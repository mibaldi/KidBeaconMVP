package com.mibaldi.kidbeaconmvp.features.GroupSingle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.kidbeaconmvp.Base.BasePresenter;
import com.mibaldi.kidbeaconmvp.Navigation.Navigator;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;
import com.mibaldi.kidbeaconmvp.ui.Views.GroupSingleView;
import com.mibaldi.kidbeaconmvp.ui.Views.ListBeaconsView;

import javax.inject.Inject;

@PerActivity
public class GroupSinglePresenter extends BasePresenter<GroupSingleView> {
    Navigator navigator;
    @Inject
    ApiClientRepository apiClientRepository;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Inject
    public GroupSinglePresenter(Navigator navigator) {
        this.navigator= navigator;
    }

}
