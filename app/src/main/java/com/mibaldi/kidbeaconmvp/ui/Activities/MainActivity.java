package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mibaldi.kidbeaconmvp.Base.BaseActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.di.HasComponent;

import com.mibaldi.kidbeaconmvp.features.Main.DaggerMainComponent;
import com.mibaldi.kidbeaconmvp.features.Main.MainComponent;

import com.mibaldi.kidbeaconmvp.ui.Fragments.MainFragment;

public class MainActivity extends BaseActivity implements HasComponent<MainComponent> {

    private MainComponent mainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeInjector();
        this.initializeActivity();
    }

    private void initializeActivity() {
        addFragment(R.id.content_main,new MainFragment());
    }

    private void initializeInjector() {
     this.mainComponent = DaggerMainComponent.builder()
                .kidBeaconApplicationComponent(getInjector())
                .build();
    }

    public MainComponent getComponent(){
        return mainComponent;
    }
    public static Intent getCallingIntent(Context context){
        return new Intent(context,MainActivity.class);
    }
}
