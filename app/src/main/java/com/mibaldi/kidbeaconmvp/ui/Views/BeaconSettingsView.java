package com.mibaldi.kidbeaconmvp.ui.Views;

import com.mibaldi.kidbeaconmvp.base.BaseView;

/**
 * Created by mikelbalducieldiaz on 6/8/16.
 */
public interface BeaconSettingsView extends BaseView {
    void showBeaconName(String name);
    void showBeaconUUID(String UUID);
    void showBeaconMajor(String major);
    void showBeaconMinor(String minor);
}
