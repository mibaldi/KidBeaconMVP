package com.mibaldi.kidbeaconmvp.ui.Views;

import com.mibaldi.kidbeaconmvp.base.BaseView;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;

import java.util.List;

/**
 * Created by mikelbalducieldiaz on 6/8/16.
 */
public interface ListBeaconsRastreatorView extends BaseView {
    void showOwnBeaconList(List<OwnBeacon> ownBeaconList);
    void  showDialog(Boolean b);
    void showTitle(String ownGroupName);
}
