package com.mibaldi.kidbeaconmvp.Navigation;

import android.content.Context;
import android.content.Intent;

import com.mibaldi.kidbeaconmvp.ui.Activities.MainActivity;

import javax.inject.Inject;

public class Navigator {
    Context context;
    @Inject
    public Navigator(Context context){
        this.context = context;
    }
    public void openMain(){
        if (context != null) {
            Intent intent = MainActivity.getCallingIntent(context);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
