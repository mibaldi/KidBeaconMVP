package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mibaldi.kidbeaconmvp.base.BaseActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.di.HasComponent;

import com.mibaldi.kidbeaconmvp.features.ListGroups.DaggerListGroupsComponent;
import com.mibaldi.kidbeaconmvp.features.ListGroups.ListGroupsComponent;

import com.mibaldi.kidbeaconmvp.ui.Fragments.ListGroupsFragment;

public class ListGroupsActivity extends BaseActivity implements HasComponent<ListGroupsComponent> {

    private ListGroupsComponent listGroupsComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_groups);
        this.initializeInjector();
        this.initializeActivity();
    }

    private void initializeActivity() {
        addFragment(R.id.fl_content,new ListGroupsFragment());
    }

    private void initializeInjector() {
     this.listGroupsComponent = DaggerListGroupsComponent.builder()
                .kidBeaconApplicationComponent(getInjector())
                .build();
    }

    public ListGroupsComponent getComponent(){
        return listGroupsComponent;
    }
    public static Intent getCallingIntent(Context context){
        return new Intent(context,ListGroupsActivity.class);
    }
}
