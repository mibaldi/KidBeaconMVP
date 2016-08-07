package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mibaldi.kidbeaconmvp.Base.BaseActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.di.HasComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSingle.DaggerGroupSingleComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSingle.GroupSingleComponent;
import com.mibaldi.kidbeaconmvp.features.ListBeacons.DaggerListBeaconsComponent;
import com.mibaldi.kidbeaconmvp.features.ListBeacons.ListBeaconsComponent;
import com.mibaldi.kidbeaconmvp.ui.Fragments.GroupSingleFragment;
import com.mibaldi.kidbeaconmvp.ui.Fragments.ListGroupsFragment;

public class GroupSingleActivity extends BaseActivity implements HasComponent<GroupSingleComponent> {
    private GroupSingleComponent component;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_single);
        this.initializeInjector();
        this.initializeActivity();
    }
    private void initializeActivity() {
        addFragment(R.id.fl_content,new GroupSingleFragment());
    }

    private void initializeInjector() {
        this.component = DaggerGroupSingleComponent.builder()
                .kidBeaconApplicationComponent(getInjector())
                .build();
    }

    public GroupSingleComponent getComponent(){
        return component;
    }
    public static Intent getCallingIntent(Context context){
        return new Intent(context,GroupSingleActivity.class);
    }
}
