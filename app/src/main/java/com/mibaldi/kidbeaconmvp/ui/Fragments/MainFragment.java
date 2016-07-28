package com.mibaldi.kidbeaconmvp.ui.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mibaldi.kidbeaconmvp.Base.BaseMVPFragment;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.features.Main.MainComponent;
import com.mibaldi.kidbeaconmvp.features.Main.MainPresenter;
import com.mibaldi.kidbeaconmvp.ui.Views.MainView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends BaseMVPFragment<MainPresenter,MainView>  implements MainView{
    private MainComponent component;
    private Unbinder unbind;

    @Inject
    public MainFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        component = getComponent(MainComponent.class);
        component.inject(this);
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        unbind = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }

    @Override
    public MainPresenter createPresenter() {
        return component.presenter();
    }
}
