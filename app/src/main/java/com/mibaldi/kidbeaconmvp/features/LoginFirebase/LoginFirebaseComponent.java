package com.mibaldi.kidbeaconmvp.features.LoginFirebase;

import com.mibaldi.kidbeaconmvp.Application.KidBeaconApplicationComponent;
import com.mibaldi.kidbeaconmvp.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = KidBeaconApplicationComponent.class,modules = LoginFirebaseModule.class)
public interface LoginFirebaseComponent {

   //void inject(ListGroupsFragment mainFragment);

    LoginFirebasePresenter presenter();
}
