package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mibaldi.kidbeaconmvp.base.BaseActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.HasComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSingle.DaggerGroupSingleComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSingle.GroupSingleComponent;
import com.mibaldi.kidbeaconmvp.ui.Fragments.GroupSingleFragment;

public class GroupSingleActivity extends BaseActivity implements HasComponent<GroupSingleComponent> {
    private GroupSingleComponent component;
    private OwnGroup ownGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_single);
        ownGroup = getIntent().getParcelableExtra("ownGroup");
        this.initializeInjector();
        this.initializeActivity();
    }
    private void initializeActivity() {
        addFragment(R.id.fl_content,GroupSingleFragment.newInstance(ownGroup));
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
