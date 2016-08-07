package com.mibaldi.kidbeaconmvp.features.GroupSettings;

import com.mibaldi.kidbeaconmvp.Application.KidBeaconApplicationComponent;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.ui.Fragments.GroupSingleFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = KidBeaconApplicationComponent.class,modules = GroupSettingsModule.class)
public interface GroupSettingsComponent {

    //void inject(GroupSingleFragment groupSingleFragment);

    GroupSettingsPresenter presenter();
}
