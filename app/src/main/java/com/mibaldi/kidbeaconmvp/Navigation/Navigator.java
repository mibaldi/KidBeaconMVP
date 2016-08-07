package com.mibaldi.kidbeaconmvp.Navigation;

import android.content.Context;
import android.content.Intent;

import com.mibaldi.kidbeaconmvp.Base.BaseActivity;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.ui.Activities.BeaconSettingsActivity;
import com.mibaldi.kidbeaconmvp.ui.Activities.GroupSettingsActivity;
import com.mibaldi.kidbeaconmvp.ui.Activities.GroupSingleActivity;
import com.mibaldi.kidbeaconmvp.ui.Activities.ListBeaconsActivity;
import com.mibaldi.kidbeaconmvp.ui.Activities.ListBeaconsRastreatorActivity;
import com.mibaldi.kidbeaconmvp.ui.Activities.ListGroupsActivity;
import com.mibaldi.kidbeaconmvp.ui.Activities.LoginActivity;
import com.mibaldi.kidbeaconmvp.ui.Activities.NfcActivity;

import javax.inject.Inject;

public class Navigator {
    Context context;
    @Inject
    public Navigator(Context context){
        this.context = context;
    }
    public void goToLogin(){
        if (context != null) {
            Intent intent = LoginActivity.getCallingIntent(context);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
    }
    public void openMain(){
        if (context != null) {
            Intent intent = ListGroupsActivity.getCallingIntent(context);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
    public void goToGroupSingle(OwnGroup ownGroup){
        if (context != null) {
            Intent intent = GroupSingleActivity.getCallingIntent(context);
            intent.putExtra("ownGroup",ownGroup);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
    public void goToGroupSettings(OwnGroup ownGroup){
        if (context != null) {
            Intent intent = GroupSettingsActivity.getCallingIntent(context);
            intent.putExtra("ownGroup",ownGroup);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
    public void goToListBeacons(){
        if (context != null) {
            Intent intent = ListBeaconsActivity.getCallingIntent(context);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
    public void goToBeaconSettings(OwnBeacon ownBeacon,OwnGroup ownGroup){
        if (context != null) {
            Intent intent = BeaconSettingsActivity.getCallingIntent(context);
            intent.putExtra("ownBeacon",ownBeacon);
            intent.putExtra("ownGroup",ownGroup);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
    public void goToNfc(OwnGroup ownGroup){
        if (context != null) {
            Intent intent = NfcActivity.getCallingIntent(context);
            intent.putExtra("ownGroup",ownGroup);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
    public void goToListBeaconsRastreator(OwnGroup ownGroup){
        if (context != null) {
            Intent intent = ListBeaconsRastreatorActivity.getCallingIntent(context);
            intent.putExtra("ownGroup",ownGroup);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }
    public void finishActivity(Context activityContext){
        if (activityContext != null) {
            ((BaseActivity)(activityContext)).finish();
        }
    }
}
