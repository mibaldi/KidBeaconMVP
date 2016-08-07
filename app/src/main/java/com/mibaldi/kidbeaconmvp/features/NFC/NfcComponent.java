package com.mibaldi.kidbeaconmvp.features.NFC;

import com.mibaldi.kidbeaconmvp.Application.KidBeaconApplicationComponent;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.ui.Fragments.ListGroupsFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = KidBeaconApplicationComponent.class,modules = NfcModule.class)
public interface NfcComponent {

    //void inject(ListGroupsFragment listGroupsFragment);

    NfcPresenter presenter();
}
