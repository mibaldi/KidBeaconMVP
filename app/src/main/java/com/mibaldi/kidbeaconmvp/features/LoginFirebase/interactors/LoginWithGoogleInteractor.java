package com.mibaldi.kidbeaconmvp.features.LoginFirebase.interactors;

import android.content.Intent;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;


public interface LoginWithGoogleInteractor {

    void onStart(GoogleApiClient mGoogleApiClient);

    void execute(FirebaseAuth mAuth, FirebaseAuth.AuthStateListener listener);

    void onActivityResult(int requestCode, Intent data, OnCompleteListener onCompleteListener);
}
