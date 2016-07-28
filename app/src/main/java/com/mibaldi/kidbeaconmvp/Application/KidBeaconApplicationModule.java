package com.mibaldi.kidbeaconmvp.Application;


import android.content.Context;

import com.mibaldi.kidbeaconmvp.Navigation.Navigator;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;

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
}
