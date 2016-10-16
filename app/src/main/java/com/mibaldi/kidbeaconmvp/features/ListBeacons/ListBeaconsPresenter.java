package com.mibaldi.kidbeaconmvp.features.ListBeacons;

import android.content.Context;
import android.util.Log;

import com.kelvinapps.rxfirebase.RxFirebaseDatabase;
import com.mibaldi.kidbeaconmvp.base.BasePresenter;
import com.mibaldi.kidbeaconmvp.navigation.Navigator;
import com.mibaldi.kidbeaconmvp.repositories.GroupsRepository;
import com.mibaldi.kidbeaconmvp.services.firebase.FirebaseDataSource;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.ui.Views.ListBeaconsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import timber.log.Timber;

@PerActivity
public class ListBeaconsPresenter extends BasePresenter<ListBeaconsView> {
    Navigator navigator;

    Context context;
    OwnGroup ownGroup;
    List<OwnBeacon> items = new ArrayList<>();
    @Inject
    GroupsRepository groupsRepository;

    @Inject
    public ListBeaconsPresenter(Navigator navigator) {
        this.navigator= navigator;
    }

    public void init( Context context) {
        this.context = context;
        this.ownGroup = groupsRepository.getCurrentOwnGroup();
        loadBeaconsService();
    }


    public void loadOwnBeacon(OwnBeacon item) {
        navigator.goToBeaconSettings(item,ownGroup);

    }

    public void loadBeaconsService() {
        getView().swipeRefresh(true);
        getView().showDialog(true);
        Observable<List<String>> listObservable = RxFirebaseDatabase.observeValuesList(FirebaseDataSource.refGroups.child(ownGroup.id + "/beacons"), String.class);

        listObservable.subscribe(groupBeacons -> {
            for (String key : groupBeacons) {
                RxFirebaseDatabase.observeSingleValue(FirebaseDataSource.refBeacons.child(key), OwnBeacon.class)
                        .subscribe(ownBeacon -> {
                            items.add(ownBeacon);
                            getView().showOwnBeaconList(items);
                            getView().swipeRefresh(false);
                            getView().showDialog(false);
                        }, throwable -> {
                            Timber.e("ListBeaconsFragment", throwable.toString());
                        });
            }
        }, throwable -> {
            Log.e("RxFirebaseSample", throwable.toString());
        });
        if (items.isEmpty()){
            getView().swipeRefresh(false);
            getView().showDialog(false);
        }
    }

    public void goToBeaconSettings() {
        navigator.goToBeaconSettings(null,ownGroup);
    }

    public void goToNfc() {
        navigator.goToNfc(ownGroup);
    }
}
