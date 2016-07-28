package com.mibaldi.kidbeaconmvp.features.LoginFirebase;

import com.mibaldi.kidbeaconmvp.Application.KidBeaconApplicationComponent;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.ui.Fragments.MainFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = KidBeaconApplicationComponent.class,modules = LoginFirebaseModule.class)
public interface LoginFirebaseComponent {

   //void inject(MainFragment mainFragment);

    LoginFirebasePresenter presenter();
}
