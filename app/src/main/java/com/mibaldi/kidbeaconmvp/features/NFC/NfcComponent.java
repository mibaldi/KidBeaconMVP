package com.mibaldi.kidbeaconmvp.features.NFC;

import com.mibaldi.kidbeaconmvp.application.KidBeaconApplicationComponent;
import com.mibaldi.kidbeaconmvp.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = KidBeaconApplicationComponent.class,modules = NfcModule.class)
public interface NfcComponent {

    //void inject(ListGroupsFragment listGroupsFragment);

    NfcPresenter presenter();
}
