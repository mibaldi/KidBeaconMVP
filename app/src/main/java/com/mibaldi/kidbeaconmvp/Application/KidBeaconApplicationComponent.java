package com.mibaldi.kidbeaconmvp.Application;

import android.content.Context;

import com.mibaldi.kidbeaconmvp.Base.BaseActivity;
import com.mibaldi.kidbeaconmvp.Navigation.Navigator;
import com.mibaldi.kidbeaconmvp.Services.Firebase.FirebaseDataSource;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;
import com.mibaldi.kidbeaconmvp.ui.Fragments.ListGroupsFragment;

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
}
