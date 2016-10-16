package com.mibaldi.kidbeaconmvp.features.GroupSettings;

import com.mibaldi.kidbeaconmvp.application.KidBeaconApplicationComponent;
import com.mibaldi.kidbeaconmvp.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = KidBeaconApplicationComponent.class,modules = GroupSettingsModule.class)
public interface GroupSettingsComponent {

    //void inject(GroupSingleFragment groupSingleFragment);

    GroupSettingsPresenter presenter();
}
