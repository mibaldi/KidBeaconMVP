package com.mibaldi.kidbeaconmvp.features.BeaconSettings;

import android.support.v4.app.FragmentActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.kidbeaconmvp.Base.BasePresenter;
import com.mibaldi.kidbeaconmvp.Navigation.Navigator;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;
import com.mibaldi.kidbeaconmvp.ui.Views.BeaconSettingsView;
import com.mibaldi.kidbeaconmvp.ui.Views.GroupSettingsView;

import javax.inject.Inject;

@PerActivity
public class BeaconSettingsPresenter extends BasePresenter<BeaconSettingsView> {
    Navigator navigator;
    @Inject
    ApiClientRepository apiClientRepository;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private FragmentActivity fragmentActivity;

    @Inject
    public BeaconSettingsPresenter(Navigator navigator) {
        this.navigator= navigator;
    }
    public void init(FragmentActivity fragmentActivity) {

        this.fragmentActivity = fragmentActivity;

    }

}
