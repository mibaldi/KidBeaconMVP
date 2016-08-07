package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.mibaldi.kidbeaconmvp.Base.BaseMVPActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.HasComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSettings.DaggerGroupSettingsComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSettings.GroupSettingsComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSettings.GroupSettingsPresenter;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.DaggerLoginFirebaseComponent;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.LoginFirebaseComponent;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.LoginFirebasePresenter;
import com.mibaldi.kidbeaconmvp.features.NFC.NfcComponent;
import com.mibaldi.kidbeaconmvp.features.NFC.NfcPresenter;
import com.mibaldi.kidbeaconmvp.ui.Views.GroupSettingsView;
import com.mibaldi.kidbeaconmvp.ui.Views.NfcView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GroupSettingsActivity extends BaseMVPActivity<GroupSettingsPresenter, GroupSettingsView> implements GroupSettingsView, HasComponent<GroupSettingsComponent> {
    private GroupSettingsComponent component;
    @BindView(R.id.groupName)
    EditText groupName;
    @BindView(R.id.groupPhoto)
    EditText groupPhoto;
    private Unbinder unbind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_settings);
        unbind = ButterKnife.bind(this);

        OwnGroup ownGroup = getIntent().getParcelableExtra("ownGroup");

        presenter.init(this, ownGroup);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind.unbind();
    }



    private void initializeInjector() {
        this.component = DaggerGroupSettingsComponent.builder()
                .kidBeaconApplicationComponent(getInjector())
                .build();
    }

    @NonNull
    @Override
    public GroupSettingsPresenter createPresenter() {
        return component.presenter();
    }

    public GroupSettingsComponent getComponent() {
        return component;
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, GroupSettingsActivity.class);
    }

    @Override
    public void showGroupName(String name) {
        groupName.setText(name);
    }

    @Override
    public void showGroupPhoto(String photo) {
        groupPhoto.setText(photo);
    }
    @OnClick(R.id.btn_save)
    public void saveGroup(){
        String name= groupName.getText().toString();
        String photo = groupPhoto.getText().toString();
        presenter.addGroup(name,photo);
    }

}
