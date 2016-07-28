package com.mibaldi.kidbeaconmvp.features.Main;

import com.mibaldi.kidbeaconmvp.Application.KidBeaconApplicationComponent;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.ui.Fragments.MainFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = KidBeaconApplicationComponent.class,modules = MainModule.class)
public interface MainComponent {

    void inject (MainFragment mainFragment);

    MainPresenter presenter();
}
