package com.mibaldi.kidbeaconmvp.application;

import android.content.Context;

import com.mibaldi.kidbeaconmvp.base.BaseActivity;
import com.mibaldi.kidbeaconmvp.navigation.Navigator;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;
import com.mibaldi.kidbeaconmvp.repositories.GroupsRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = KidBeaconApplicationModule.class)
public interface KidBeaconApplicationComponent {
    @Named("ApplicationContext")
    Context context();

    void inject (BaseActivity baseActivity);


    Navigator getNavigator();
    ApiClientRepository apiClientRepository();
    GroupsRepository groupsRepository();
}
