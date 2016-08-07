package com.mibaldi.kidbeaconmvp.features.GroupSettings;

import android.support.v4.app.FragmentActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.kidbeaconmvp.Base.BasePresenter;
import com.mibaldi.kidbeaconmvp.Navigation.Navigator;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;
import com.mibaldi.kidbeaconmvp.ui.Views.GroupSettingsView;
import com.mibaldi.kidbeaconmvp.ui.Views.GroupSingleView;

import javax.inject.Inject;

@PerActivity
public class GroupSettingsPresenter extends BasePresenter<GroupSettingsView> {
    Navigator navigator;
    @Inject
    ApiClientRepository apiClientRepository;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private FragmentActivity fragmentActivity;

    @Inject
    public GroupSettingsPresenter(Navigator navigator) {
        this.navigator= navigator;
    }
    public void init(FragmentActivity fragmentActivity) {

        this.fragmentActivity = fragmentActivity;

    }

}
