package com.mibaldi.kidbeaconmvp.features.ListBeaconsRastreator;

import android.support.v4.app.FragmentActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.kidbeaconmvp.Base.BasePresenter;
import com.mibaldi.kidbeaconmvp.Navigation.Navigator;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;
import com.mibaldi.kidbeaconmvp.ui.Views.ListBeaconsRastreatorView;
import com.mibaldi.kidbeaconmvp.ui.Views.NfcView;

import javax.inject.Inject;

@PerActivity
public class ListBeaconsRastreatorPresenter extends BasePresenter<ListBeaconsRastreatorView> {
    Navigator navigator;
    @Inject
    ApiClientRepository apiClientRepository;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private FragmentActivity fragmentActivity;

    @Inject
    public ListBeaconsRastreatorPresenter(Navigator navigator) {
        this.navigator= navigator;
    }
    public void init(FragmentActivity fragmentActivity) {

        this.fragmentActivity = fragmentActivity;

    }

}
