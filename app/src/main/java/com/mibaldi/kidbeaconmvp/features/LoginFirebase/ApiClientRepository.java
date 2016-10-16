package com.mibaldi.kidbeaconmvp.features.LoginFirebase;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.mibaldi.kidbeaconmvp.constants.FirebaseConstants;
import com.mibaldi.kidbeaconmvp.R;


import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;


@Singleton
public class ApiClientRepository {
    private GoogleApiClient mGoogleApiClient;
    private FragmentActivity fragmentActivity;

    @Inject
    public ApiClientRepository(){

    }

    public void init(FragmentActivity fragmentActivity, GoogleApiClient.OnConnectionFailedListener listener) {
        this.fragmentActivity = fragmentActivity;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(fragmentActivity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        if (mGoogleApiClient == null){
            mGoogleApiClient = new GoogleApiClient.Builder(fragmentActivity)
                    .enableAutoManage(fragmentActivity , listener)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }

    }

    public GoogleApiClient getGoogleApiClient(){
        return mGoogleApiClient;
    }

    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        fragmentActivity.startActivityForResult(signInIntent, FirebaseConstants.RC_SIGN_IN);
    }

    public void signOut(ResultCallback<Status> callback) {

        if (mGoogleApiClient.isConnected()) {
            Timber.d("conectado se puede desconectar");
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(callback);
        }else {
            Timber.d("sin conectar");
        }
    }

    public void revokeAccess(ResultCallback<Status> callback) {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(callback);
    }
}
