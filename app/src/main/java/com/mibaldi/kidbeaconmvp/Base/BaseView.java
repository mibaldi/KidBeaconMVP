package com.mibaldi.kidbeaconmvp.base;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface BaseView extends MvpView {
    void showError(int error);
}
