package com.mibaldi.kidbeaconmvp.features.ListBeaconsRastreator;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;
import com.mibaldi.kidbeaconmvp.Base.BasePresenter;
import com.mibaldi.kidbeaconmvp.Navigation.Navigator;
import com.mibaldi.kidbeaconmvp.Services.Firebase.FirebaseDataSource;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;
import com.mibaldi.kidbeaconmvp.ui.Views.ListBeaconsRastreatorView;
import com.mibaldi.kidbeaconmvp.ui.Views.NfcView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import timber.log.Timber;

@PerActivity
public class ListBeaconsRastreatorPresenter extends BasePresenter<ListBeaconsRastreatorView> {
    Navigator navigator;
    OwnGroup ownGroup;
    List<OwnBeacon> items = new ArrayList<>();
    @Inject
    public ListBeaconsRastreatorPresenter(Navigator navigator) {
        this.navigator= navigator;
    }
    public void init(OwnGroup ownGroup) {
        this.ownGroup = ownGroup;
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
