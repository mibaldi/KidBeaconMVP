package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mibaldi.kidbeaconmvp.Base.BaseActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.di.HasComponent;
import com.mibaldi.kidbeaconmvp.features.ListBeacons.DaggerListBeaconsComponent;
import com.mibaldi.kidbeaconmvp.features.ListBeacons.ListBeaconsComponent;
import com.mibaldi.kidbeaconmvp.features.ListGroups.DaggerListGroupsComponent;
import com.mibaldi.kidbeaconmvp.features.ListGroups.ListGroupsComponent;
import com.mibaldi.kidbeaconmvp.ui.Fragments.ListGroupsFragment;

public class ListBeaconsActivity extends BaseActivity implements HasComponent<ListBeaconsComponent> {
    private ListBeaconsComponent component;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_beacons);
        this.initializeInjector();
        this.initializeActivity();
    }

    private void initializeActivity() {
        addFragment(R.id.fl_content,new ListGroupsFragment());
    }

    private void initializeInjector() {
        this.component = DaggerListBeaconsComponent.builder()
                .kidBeaconApplicationComponent(getInjector())
                .build();
    }

    public ListBeaconsComponent getComponent(){
        return component;
    }
    public static Intent getCallingIntent(Context context){
        return new Intent(context,ListBeaconsActivity.class);
    }
}
