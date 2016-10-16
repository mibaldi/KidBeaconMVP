package com.mibaldi.kidbeaconmvp.features.ListGroups;

import com.mibaldi.kidbeaconmvp.application.KidBeaconApplicationComponent;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.ui.Activities.ListGroupsActivity;
import com.mibaldi.kidbeaconmvp.ui.Fragments.ListGroupsFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = KidBeaconApplicationComponent.class,modules = ListGroupsModule.class)
public interface ListGroupsComponent {

    void inject (ListGroupsFragment listGroupsFragment);
    void inject (ListGroupsActivity listGroupsActivity);
    ListGroupsPresenter presenter();
}
