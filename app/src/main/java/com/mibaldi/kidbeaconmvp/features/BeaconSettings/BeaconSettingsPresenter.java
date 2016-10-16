package com.mibaldi.kidbeaconmvp.features.BeaconSettings;

import android.content.Context;

import com.mibaldi.kidbeaconmvp.base.BasePresenter;
import com.mibaldi.kidbeaconmvp.navigation.Navigator;
import com.mibaldi.kidbeaconmvp.repositories.GroupsRepository;
import com.mibaldi.kidbeaconmvp.services.firebase.FirebaseDataSource;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.ui.Views.BeaconSettingsView;

import javax.inject.Inject;

@PerActivity
public class BeaconSettingsPresenter extends BasePresenter<BeaconSettingsView> {
    Navigator navigator;
    Context context;
    public OwnBeacon ownBeacon;
    public OwnGroup ownGroup;
    boolean edit = false;

    @Inject
    GroupsRepository groupsRepository;

    @Inject
    public BeaconSettingsPresenter(Navigator navigator) {
        this.navigator= navigator;
    }
    public void init(Context context, OwnBeacon ownBeacon) {

        this.context = context;
        this.ownGroup = groupsRepository.getCurrentOwnGroup();
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
