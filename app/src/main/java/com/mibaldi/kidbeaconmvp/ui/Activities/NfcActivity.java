package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mibaldi.kidbeaconmvp.Base.BaseMVPActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.di.HasComponent;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.DaggerLoginFirebaseComponent;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.LoginFirebaseComponent;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.LoginFirebasePresenter;
import com.mibaldi.kidbeaconmvp.features.NFC.DaggerNfcComponent;
import com.mibaldi.kidbeaconmvp.features.NFC.NfcComponent;
import com.mibaldi.kidbeaconmvp.features.NFC.NfcPresenter;
import com.mibaldi.kidbeaconmvp.ui.Views.LoginFirebaseView;
import com.mibaldi.kidbeaconmvp.ui.Views.NfcView;

public class NfcActivity extends BaseMVPActivity<NfcPresenter,NfcView> implements NfcView, HasComponent<NfcComponent> {
    private NfcComponent component;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        presenter.init(this);

    }

    @NonNull
    @Override
    public NfcPresenter createPresenter() {
        return component.presenter();
    }

    private void initializeInjector() {
        this.component = DaggerNfcComponent.builder()
                .kidBeaconApplicationComponent(getInjector())
                .build();
    }

    public NfcComponent getComponent(){
        return component;
    }

    public static Intent getCallingIntent(Context context){
        return new Intent(context,NfcActivity.class);
    }

}
