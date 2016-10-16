package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.widget.EditText;

import com.mibaldi.kidbeaconmvp.base.BaseMVPActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.HasComponent;
import com.mibaldi.kidbeaconmvp.features.BeaconSettings.BeaconSettingsComponent;
import com.mibaldi.kidbeaconmvp.features.BeaconSettings.BeaconSettingsPresenter;
import com.mibaldi.kidbeaconmvp.features.BeaconSettings.DaggerBeaconSettingsComponent;
import com.mibaldi.kidbeaconmvp.ui.Views.BeaconSettingsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BeaconSettingsActivity extends BaseMVPActivity<BeaconSettingsPresenter,BeaconSettingsView> implements BeaconSettingsView, HasComponent<BeaconSettingsComponent> {

    @BindView(R.id.beaconName)
    EditText beaconName;
    @BindView(R.id.beaconUUID)
    EditText beaconUUID;
    @BindView(R.id.beaconMajor)
    EditText beaconMajor;
    @BindView(R.id.beaconMinor)
    EditText beaconMinor;

    private BeaconSettingsComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_settings);
        ButterKnife.bind(this);
        //OwnGroup ownGroup = getIntent().getParcelableExtra("ownGroup");
        OwnBeacon ownBeacon = getIntent().getParcelableExtra("ownBeacon");
        presenter.init(this,ownBeacon);
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
    @OnClick(R.id.btn_save)
    public void saveBeacon(){
        String name= beaconName.getText().toString();
        String UUID = beaconUUID.getText().toString();
        String major= beaconMajor.getText().toString();
        String minor = beaconMinor.getText().toString();
        presenter.addBeacon(name,UUID,major,minor);
    }

    @Override
    public void showBeaconName(String name) {
        beaconName.setText(name);
    }

    @Override
    public void showBeaconUUID(String UUID) {
        beaconUUID.setText(UUID);
    }

    @Override
    public void showBeaconMajor(String major) {
        beaconMajor.setText(major);
    }

    @Override
    public void showBeaconMinor(String minor) {
        beaconMinor.setText(minor);
    }
}
