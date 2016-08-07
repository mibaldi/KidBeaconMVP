package com.mibaldi.kidbeaconmvp.features.ListGroups;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;
import com.mibaldi.kidbeaconmvp.Base.BasePresenter;
import com.mibaldi.kidbeaconmvp.Navigation.Navigator;
import com.mibaldi.kidbeaconmvp.Services.Firebase.FirebaseDataSource;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;
import com.mibaldi.kidbeaconmvp.ui.Views.ListGroupsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import timber.log.Timber;

@PerActivity
public class ListGroupsPresenter extends BasePresenter<ListGroupsView> {
    Navigator navigator;
    @Inject
    ApiClientRepository apiClientRepository;

    private Context context;
    List<OwnGroup> items = new ArrayList<>();
    private Subscription subscribe;

    @Inject
    public ListGroupsPresenter(Navigator navigator) {
        this.navigator= navigator;
    }

    public void init(FragmentActivity fragmentActivity, Context context){
        apiClientRepository.init(fragmentActivity, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Timber.e("error");
            }
        });
        this.context = context;
        loadGroupsService();
    }
    public void loadOwnGroup(OwnGroup ownGroup) {
        navigator.goToGroupSingle(ownGroup);
    }

    public void loadGroupsService() {
        items.clear();
        getView().swipeRefresh(true);
        getView().showDialog(true);
        String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
         subscribe = RxFirebaseDatabase.observeValuesList(FirebaseDataSource.refUsers.child(uid).child("groups"), String.class)
                .subscribe(userGroups -> {
                    for (String key : userGroups) {
                        RxFirebaseDatabase.observeSingleValue(FirebaseDataSource.refGroups.child(String.valueOf(key)), OwnGroup.class)
                                .subscribe(ownGroup1 -> {
                                    items.add(ownGroup1);
                                    getView().showOwnGroupsList(items);
                                    getView().swipeRefresh(false);
                                    getView().showDialog(false);
                                    //  dialog.hide();
                                    //adapter.notifyDataSetChanged();
                                    //mSwipeRefreshLayout.setRefreshing(false);
                                }, throwable -> {
                                    Timber.e("ListGroupsFragment", throwable.toString());
                                });
                    }


                }, throwable -> {
                    Log.e("RxFirebaseSample", throwable.toString());
                });

        if (items.isEmpty()){
            getView().showDialog(false);
        }
    }
    public void logOut(){
        if (!subscribe.isUnsubscribed()){
            subscribe.unsubscribe();
        }
        apiClientRepository.signOut(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                FirebaseAuth.getInstance().signOut();

                navigator.goToLogin();
                navigator.finishActivity(context);
            }
        });
    }
    public void goToGroupSettings(OwnGroup ownGroup){
        navigator.goToGroupSettings(ownGroup);
    }
}
