package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;


import com.mibaldi.kidbeaconmvp.base.BaseMVPActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.di.HasComponent;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.DaggerLoginFirebaseComponent;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.LoginFirebaseComponent;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.LoginFirebasePresenter;
import com.mibaldi.kidbeaconmvp.ui.Views.LoginFirebaseView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends BaseMVPActivity<LoginFirebasePresenter,LoginFirebaseView> implements LoginFirebaseView, HasComponent<LoginFirebaseComponent> {

    private LoginFirebaseComponent component;

    private Unbinder unbind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbind = ButterKnife.bind(this);
        presenter.init(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbind.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode,data);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }



    @NonNull
    @Override
    public LoginFirebasePresenter createPresenter() {
        return component.presenter();
    }

    @OnClick(R.id.sign_in_button)
    public void sign_in(){
        presenter.signIn();
    }

    @OnClick(R.id.sign_out_button)
    public void sign_out(){
        presenter.signOut();
    }

    @OnClick(R.id.disconnect_button)
    public void disconnect(){
        presenter.disconnect();
    }

    private void initializeInjector() {
       this.component = DaggerLoginFirebaseComponent.builder()
                .kidBeaconApplicationComponent(getInjector())
                .build();
    }

    public LoginFirebaseComponent getComponent(){
        return component;
    }

    public static Intent getCallingIntent(Context context){
        return new Intent(context,LoginActivity.class);
    }

}
