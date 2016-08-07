package com.mibaldi.kidbeaconmvp.features.BeaconSettings;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.kidbeaconmvp.Base.BasePresenter;
import com.mibaldi.kidbeaconmvp.Navigation.Navigator;
import com.mibaldi.kidbeaconmvp.Services.Firebase.FirebaseDataSource;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;
import com.mibaldi.kidbeaconmvp.ui.Views.BeaconSettingsView;
import com.mibaldi.kidbeaconmvp.ui.Views.GroupSettingsView;

import java.util.Date;

import javax.inject.Inject;

@PerActivity
public class BeaconSettingsPresenter extends BasePresenter<BeaconSettingsView> {
    Navigator navigator;
    Context context;
    public OwnBeacon ownBeacon;
    public OwnGroup ownGroup;
    boolean edit = false;

    @Inject
    public BeaconSettingsPresenter(Navigator navigator) {
        this.navigator= navigator;
    }
    public void init(Context context, OwnBeacon ownBeacon,OwnGroup ownGroup) {

        this.context = context;
        this.ownGroup = ownGroup;
        if (ownBeacon != null){
            edit = true;
            this.ownBeacon = ownBeacon;
            repaint();
        }else{
            edit = false;
            this.ownBeacon = new OwnBeacon();
        }

    }

    private void repaint() {
        getView().showBeaconName(ownBeacon.name);
        getView().showBeaconUUID(ownBeacon.uuid);
        getView().showBeaconMajor(ownBeacon.major);
        getView().showBeaconMinor(ownBeacon.minor);
    }

    public void addBeacon(String name, String uuid, String major, String minor) {
        if (name != "" && uuid != "" && major != "" && minor != "") {
            ownBeacon.name = name;
            ownBeacon.uuid = uuid;
            ownBeacon.major = major;
            ownBeacon.minor = minor;
            if (edit) {
                //FirebaseManager.editGroup(ownGroup);
            } else {
                FirebaseDataSource.generateBeacon(ownBeacon,ownGroup.id);
                navigator.finishActivity(context);
            }
        }
    }
}
