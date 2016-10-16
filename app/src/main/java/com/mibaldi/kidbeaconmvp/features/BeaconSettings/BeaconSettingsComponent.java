package com.mibaldi.kidbeaconmvp.features.BeaconSettings;

import com.mibaldi.kidbeaconmvp.application.KidBeaconApplicationComponent;
import com.mibaldi.kidbeaconmvp.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = KidBeaconApplicationComponent.class,modules = BeaconSettingsModule.class)
public interface BeaconSettingsComponent {

    //void inject(GroupSingleFragment groupSingleFragment);

    BeaconSettingsPresenter presenter();
}
