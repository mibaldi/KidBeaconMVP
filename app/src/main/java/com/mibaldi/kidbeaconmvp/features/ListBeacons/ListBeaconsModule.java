package com.mibaldi.kidbeaconmvp.features.ListBeacons;

import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.ui.Adapters.GroupsListAdapter;
import com.mibaldi.kidbeaconmvp.ui.Adapters.ListBeaconsAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ListBeaconsModule {
    public ListBeaconsModule() {
    }
    @Provides
    @PerActivity
    ListBeaconsAdapter providerListBeaconsAdapter(){
        return new ListBeaconsAdapter();
    }

}
