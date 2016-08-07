package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mibaldi.kidbeaconmvp.Base.BaseMVPActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.di.HasComponent;
import com.mibaldi.kidbeaconmvp.features.ListBeaconsRastreator.DaggerListBeaconsRastreatorComponent;
import com.mibaldi.kidbeaconmvp.features.ListBeaconsRastreator.ListBeaconsRastreatorComponent;
import com.mibaldi.kidbeaconmvp.features.ListBeaconsRastreator.ListBeaconsRastreatorPresenter;
import com.mibaldi.kidbeaconmvp.features.NFC.DaggerNfcComponent;
import com.mibaldi.kidbeaconmvp.features.NFC.NfcComponent;
import com.mibaldi.kidbeaconmvp.features.NFC.NfcPresenter;
import com.mibaldi.kidbeaconmvp.ui.Views.ListBeaconsRastreatorView;
import com.mibaldi.kidbeaconmvp.ui.Views.NfcView;

public class ListBeaconsRastreatorActivity extends BaseMVPActivity<ListBeaconsRastreatorPresenter,ListBeaconsRastreatorView> implements ListBeaconsRastreatorView, HasComponent<ListBeaconsRastreatorComponent> {
    private ListBeaconsRastreatorComponent component;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_beacons_rastreator);
        presenter.init(this);
    }

    @NonNull
    @Override
    public ListBeaconsRastreatorPresenter createPresenter() {
        return component.presenter();
    }

    private void initializeInjector() {
        this.component = DaggerListBeaconsRastreatorComponent.builder()
                .kidBeaconApplicationComponent(getInjector())
                .build();
    }

    public ListBeaconsRastreatorComponent getComponent(){
        return component;
    }

    public static Intent getCallingIntent(Context context){
        return new Intent(context,ListBeaconsRastreatorActivity.class);
    }
}
