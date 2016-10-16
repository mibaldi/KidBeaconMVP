package com.mibaldi.kidbeaconmvp.features.GroupSingle;

import com.mibaldi.kidbeaconmvp.application.KidBeaconApplicationComponent;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.ui.Fragments.GroupSingleFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = KidBeaconApplicationComponent.class,modules = GroupSingleModule.class)
public interface GroupSingleComponent {

    void inject(GroupSingleFragment groupSingleFragment);

    GroupSinglePresenter presenter();
}
