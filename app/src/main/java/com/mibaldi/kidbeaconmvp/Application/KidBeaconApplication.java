package com.mibaldi.kidbeaconmvp.Application;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.mibaldi.kidbeaconmvp.BuildConfig;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class KidBeaconApplication extends Application {
    private KidBeaconApplicationComponent KidBeaconComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        initDebugTools();
      KidBeaconComponent=DaggerKidBeaconApplicationComponent.builder()
                .kidBeaconApplicationModule(new KidBeaconApplicationModule(this)).build();
    }
    public KidBeaconApplicationComponent getInjector(){
        return KidBeaconComponent;
    }
    private void initDebugTools() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
