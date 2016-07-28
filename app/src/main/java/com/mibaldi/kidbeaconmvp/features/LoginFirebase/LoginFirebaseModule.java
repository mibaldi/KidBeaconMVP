package com.mibaldi.kidbeaconmvp.features.LoginFirebase;


import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.interactors.LoginWithGoogleInteractor;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.interactors.LoginWithGoogleInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginFirebaseModule {
    public LoginFirebaseModule() {
    }

    @Provides
    @PerActivity
    LoginWithGoogleInteractor providerLoginWithGoogleInteractor(){
        return new LoginWithGoogleInteractorImpl();
    }

}
