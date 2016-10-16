package com.mibaldi.kidbeaconmvp.repositories;

import com.mibaldi.kidbeaconmvp.data.OwnGroup;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by mikelbalducieldiaz on 16/10/16.
 */
@Singleton
public class GroupsRepository {
    public OwnGroup currentOwnGroup;

    @Inject
    public GroupsRepository() {
    }

    public void setCurrentGroup(OwnGroup ownGroup){
        this.currentOwnGroup = ownGroup;
    }
    public OwnGroup getCurrentOwnGroup(){
        return currentOwnGroup;
    }
}
