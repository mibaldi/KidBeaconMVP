package com.mibaldi.kidbeaconmvp.application;


import android.content.Context;

import com.mibaldi.kidbeaconmvp.navigation.Navigator;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;
import com.mibaldi.kidbeaconmvp.repositories.GroupsRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class KidBeaconApplicationModule {
    private final Context context;
    public KidBeaconApplicationModule(Context context){
        this.context = context;
    }
    @Named("ApplicationContext")
    @Provides
    @Singleton
    Context providedApplicationContext(){
        return this.context;
    }
    @Provides
    @Singleton
    Navigator providedNavigator(){
        return new Navigator(this.context);
    }
    @Provides
    @Singleton
    ApiClientRepository providedApiClientRepository(){return new ApiClientRepository();}
    @Provides
    @Singleton
    GroupsRepository providedGroupsRepository(){return new GroupsRepository();}

}
