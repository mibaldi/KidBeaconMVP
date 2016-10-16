package com.mibaldi.kidbeaconmvp.features.ListBeaconsRastreator;

import android.util.Log;

import com.kelvinapps.rxfirebase.RxFirebaseDatabase;
import com.mibaldi.kidbeaconmvp.base.BasePresenter;
import com.mibaldi.kidbeaconmvp.navigation.Navigator;
import com.mibaldi.kidbeaconmvp.repositories.GroupsRepository;
import com.mibaldi.kidbeaconmvp.services.firebase.FirebaseDataSource;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.ui.Views.ListBeaconsRastreatorView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

@PerActivity
public class ListBeaconsRastreatorPresenter extends BasePresenter<ListBeaconsRastreatorView> {
    Navigator navigator;
    OwnGroup ownGroup;
    List<OwnBeacon> items = new ArrayList<>();
    @Inject
    GroupsRepository groupsRepository;
    @Inject
    public ListBeaconsRastreatorPresenter(Navigator navigator) {
        this.navigator= navigator;
    }
    public void init() {
        this.ownGroup = groupsRepository.getCurrentOwnGroup();
        getView().showTitle(ownGroup.name);
        loadBeaconsService();

    }
    public void loadBeaconsService() {

        getView().showDialog(true);
        RxFirebaseDatabase.observeValuesList(FirebaseDataSource.refGroups.child(ownGroup.id + "/beacons"), String.class)
                .subscribe(groupBeacons -> {
                    for (String key : groupBeacons) {
                        RxFirebaseDatabase.observeSingleValue(FirebaseDataSource.refBeacons.child(key), OwnBeacon.class)
                                .subscribe(ownBeacon -> {
                                    items.add(ownBeacon);
                                    getView().showOwnBeaconList(items);
                                    getView().showDialog(false);
                                }, throwable -> {
                                    Timber.e("ListBeaconsFragment", throwable.toString());
                                });
                    }

                }, throwable -> {
                    Log.e("RxFirebaseSample", throwable.toString());
                });
    }



}
