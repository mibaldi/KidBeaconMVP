package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mibaldi.kidbeaconmvp.Base.BaseMVPActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.di.HasComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSettings.DaggerGroupSettingsComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSettings.GroupSettingsComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSettings.GroupSettingsPresenter;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.DaggerLoginFirebaseComponent;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.LoginFirebaseComponent;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.LoginFirebasePresenter;
import com.mibaldi.kidbeaconmvp.features.NFC.NfcComponent;
import com.mibaldi.kidbeaconmvp.features.NFC.NfcPresenter;
import com.mibaldi.kidbeaconmvp.ui.Views.GroupSettingsView;
import com.mibaldi.kidbeaconmvp.ui.Views.NfcView;

public class GroupSettingsActivity extends BaseMVPActivity<GroupSettingsPresenter,GroupSettingsView> implements GroupSettingsView, HasComponent<GroupSettingsComponent> {
    private GroupSettingsComponent component;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_settings);
        presenter.init(this);
    }

    private void initializeInjector() {
        this.component = DaggerGroupSettingsComponent.builder()
                .kidBeaconApplicationComponent(getInjector())
                .build();
    }
    @NonNull
    @Override
    public GroupSettingsPresenter createPresenter() {
        return component.presenter();
    }
    public GroupSettingsComponent getComponent(){
        return component;
    }

    public static Intent getCallingIntent(Context context){
        return new Intent(context,GroupSettingsActivity.class);
    }
}
