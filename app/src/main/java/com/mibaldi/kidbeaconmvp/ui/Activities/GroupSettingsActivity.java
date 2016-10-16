package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.mibaldi.kidbeaconmvp.base.BaseMVPActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.HasComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSettings.DaggerGroupSettingsComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSettings.GroupSettingsComponent;
import com.mibaldi.kidbeaconmvp.features.GroupSettings.GroupSettingsPresenter;
import com.mibaldi.kidbeaconmvp.ui.Views.GroupSettingsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GroupSettingsActivity extends BaseMVPActivity<GroupSettingsPresenter, GroupSettingsView> implements GroupSettingsView, HasComponent<GroupSettingsComponent> {
    private GroupSettingsComponent component;
    @BindView(R.id.groupName)
    EditText groupName;
    @BindView(R.id.btn_save)
    Button btn_save;
    private Unbinder unbind;
    private Uri fileUri;
    private ProgressDialog mProgress;
    private int mProgressStatus = 0;
    private boolean stateButton = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_settings);
        unbind = ButterKnife.bind(this);

        //OwnGroup ownGroup = getIntent().getParcelableExtra("ownGroup");
        presenter.init(this);
        mProgress = new ProgressDialog(this);
        mProgress.setCanceledOnTouchOutside(false);

        if (savedInstanceState != null) {
            fileUri = savedInstanceState.getParcelable("URI");
            stateButton = savedInstanceState.getBoolean("StateButton");
            //presenter.showPreview(fileUri);
        }
       // btn_save.setEnabled(stateButton);

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

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 200: {
                if (resultCode == RESULT_OK) {
                    stateButton = true;
                    btn_save.setEnabled(stateButton);

                }
            }
        }

    }*/

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("URI", fileUri);
        outState.putBoolean("StateButton",stateButton);
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
    public void enableSaveButton() {
        btn_save.setEnabled(true);
    }

    @Override
    public void paintProgress(long total, long progress) {

        mProgress.setMax((int)total);
        mProgress.setProgress((int)progress);
    }

    @Override
    public void showHideProgressBar(boolean b) {
        if (b) {
            mProgress.show();
            mProgress.onStart();
        }else {
            mProgress.hide();

        }
    }


    /*@OnClick(R.id.makePhoto)
    public void takePhoto() {
        File file = Utils.createTempFile(this.getCacheDir());
        fileUri = Utils.getUriFromFile(file);
        presenter.takePhoto(fileUri);
    }*/

    @OnClick(R.id.btn_save)
    public void saveGroup(Button button) {
        /*String name = groupName.getText().toString();
        try {
            presenter.uploadPhoto(fileUri,name);
            //presenter.addGroup(name);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        String name = groupName.getText().toString();
        if (!name.equals("")){
            presenter.addGroup(name);
        }else {
            Snackbar.make(button,"Campo de nombre vacio",Snackbar.LENGTH_SHORT).show();
        }


    }



}
