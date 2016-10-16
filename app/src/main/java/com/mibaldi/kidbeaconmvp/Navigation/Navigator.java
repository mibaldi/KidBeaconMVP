package com.mibaldi.kidbeaconmvp.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import com.mibaldi.kidbeaconmvp.base.BaseActivity;
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
    private static final int CAMERA_CAPTURE = 200;
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
            //intent.putExtra("ownGroup",ownGroup);
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
    public void goToListBeacons(OwnGroup ownGroup){
        if (context != null) {
            Intent intent = ListBeaconsActivity.getCallingIntent(context);
            intent.putExtra("ownGroup",ownGroup);
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
    public void makePhoto(Activity activity,Uri fileUri){
        if (context != null){

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
            activity.startActivityForResult(intent,CAMERA_CAPTURE);
        }
    }
    public void finishActivity(Context activityContext){
        if (activityContext != null) {
            ((BaseActivity)(activityContext)).finish();
        }
    }
}
