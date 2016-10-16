package com.mibaldi.kidbeaconmvp.features.ListBeacons;

import com.mibaldi.kidbeaconmvp.application.KidBeaconApplicationComponent;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.ui.Fragments.ListBeaconsFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = KidBeaconApplicationComponent.class,modules = ListBeaconsModule.class)
public interface ListBeaconsComponent {

    void inject(ListBeaconsFragment listBeaconsFragment);

    ListBeaconsPresenter presenter();
}
