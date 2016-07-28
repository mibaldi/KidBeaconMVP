package com.mibaldi.kidbeaconmvp.features.Main;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.kidbeaconmvp.Base.BasePresenter;
import com.mibaldi.kidbeaconmvp.Navigation.Navigator;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;
import com.mibaldi.kidbeaconmvp.ui.Views.MainView;

import javax.inject.Inject;

@PerActivity
public class MainPresenter extends BasePresenter<MainView> {
    Navigator navigator;
    @Inject
    ApiClientRepository apiClientRepository;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Inject
    public MainPresenter(Navigator navigator) {
        this.navigator= navigator;
    }

}
