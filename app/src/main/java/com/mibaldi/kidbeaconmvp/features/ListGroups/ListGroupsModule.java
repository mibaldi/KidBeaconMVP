package com.mibaldi.kidbeaconmvp.features.ListGroups;

import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.ui.Adapters.GroupsListAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ListGroupsModule {
    public ListGroupsModule() {
    }
    @Provides
    @PerActivity
    GroupsListAdapter providerGroupListAdapter(){
        return new GroupsListAdapter();
    }

}
