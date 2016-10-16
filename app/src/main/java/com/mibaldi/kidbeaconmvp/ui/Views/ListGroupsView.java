package com.mibaldi.kidbeaconmvp.ui.Views;

import com.mibaldi.kidbeaconmvp.base.BaseView;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;

import java.util.List;

public interface ListGroupsView extends BaseView {
    void showOwnGroupsList(List<OwnGroup> ownGroupList);
    void  swipeRefresh(Boolean b);
    void showDialog(Boolean b);
}
