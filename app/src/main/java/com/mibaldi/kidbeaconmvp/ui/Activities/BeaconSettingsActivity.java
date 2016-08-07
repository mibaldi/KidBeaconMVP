package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mibaldi.kidbeaconmvp.Base.BaseMVPActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.di.HasComponent;
import com.mibaldi.kidbeaconmvp.features.BeaconSettings.BeaconSettingsComponent;
import com.mibaldi.kidbeaconmvp.features.BeaconSettings.BeaconSettingsPresenter;
import com.mibaldi.kidbeaconmvp.features.BeaconSettings.DaggerBeaconSettingsComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSettings.GroupSettingsComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSettings.GroupSettingsPresenter;
import com.mibaldi.kidbeaconmvp.ui.Views.BeaconSettingsView;

public class BeaconSettingsActivity extends BaseMVPActivity<BeaconSettingsPresenter,BeaconSettingsView> implements BeaconSettingsView, HasComponent<BeaconSettingsComponent> {

    private BeaconSettingsComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_settings);
        presenter.init(this);
    }

    private void initializeInjector() {
        this.component = DaggerBeaconSettingsComponent.builder()
                .kidBeaconApplicationComponent(getInjector())
                .build();
    }
    @NonNull
    @Override
    public BeaconSettingsPresenter createPresenter() {
        return component.presenter();
    }
    public BeaconSettingsComponent getComponent(){
        return component;
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, BeaconSettingsActivity.class);
    }
}
