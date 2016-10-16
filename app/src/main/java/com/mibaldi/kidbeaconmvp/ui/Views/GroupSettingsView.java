package com.mibaldi.kidbeaconmvp.ui.Views;

import com.mibaldi.kidbeaconmvp.base.BaseView;

/**
 * Created by mikelbalducieldiaz on 6/8/16.
 */
public interface GroupSettingsView extends BaseView {
    void showGroupName(String name);

    void enableSaveButton();

    void paintProgress(long total, long progress);

    void showHideProgressBar(boolean b);
}
