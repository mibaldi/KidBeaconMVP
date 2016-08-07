package com.mibaldi.kidbeaconmvp.ui.Views;

import com.mibaldi.kidbeaconmvp.Base.BaseView;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;

import java.util.List;

/**
 * Created by mikelbalducieldiaz on 6/8/16.
 */
public interface ListBeaconsRastreatorView extends BaseView {
    void showRecipeList(List<OwnBeacon> recipeList);
    void  swipeRefresh(Boolean b);
    void showProgressDialog(int message);
    void cancelProgressDialog();
}
