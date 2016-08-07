package com.mibaldi.kidbeaconmvp.features.ListBeaconsRastreator;

import com.mibaldi.kidbeaconmvp.Application.KidBeaconApplicationComponent;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.ui.Activities.ListBeaconsRastreatorActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = KidBeaconApplicationComponent.class,modules = ListBeaconsRastreatorModule.class)
public interface ListBeaconsRastreatorComponent {

    void inject(ListBeaconsRastreatorActivity listBeaconsRastreatorActivity);

    ListBeaconsRastreatorPresenter presenter();
}
