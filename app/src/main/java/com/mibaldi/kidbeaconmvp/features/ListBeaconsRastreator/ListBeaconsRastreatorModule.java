package com.mibaldi.kidbeaconmvp.features.ListBeaconsRastreator;

import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.ui.Adapters.ListBeaconsAdapter;
import com.mibaldi.kidbeaconmvp.ui.Adapters.ListBeaconsRastreatorAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ListBeaconsRastreatorModule {
    public ListBeaconsRastreatorModule() {
    }
    @Provides
    @PerActivity
    ListBeaconsRastreatorAdapter providerListBeaconsRastreatorAdapter(){
        return new ListBeaconsRastreatorAdapter();
    }

}
