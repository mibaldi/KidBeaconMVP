package com.mibaldi.kidbeaconmvp.features.LoginFirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mibaldi.kidbeaconmvp.Base.BasePresenter;
import com.mibaldi.kidbeaconmvp.Navigation.Navigator;
import com.mibaldi.kidbeaconmvp.Services.Firebase.FirebaseDataSource;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.interactors.LoginWithGoogleInteractor;
import com.mibaldi.kidbeaconmvp.ui.Views.LoginFirebaseView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import timber.log.Timber;


@PerActivity
public class LoginFirebasePresenter extends BasePresenter<LoginFirebaseView> implements GoogleApiClient.OnConnectionFailedListener{

    Navigator navigator;

    @Inject
    LoginWithGoogleInteractor loginWithGoogleInteractor;

    @Inject
    ApiClientRepository apiClientRepository;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase database;

    private FragmentActivity fragmentActivity;
    private FirebaseAuth mAuth;
    private boolean hasEnter = false;

    @Inject
    public LoginFirebasePresenter(Navigator navigator) {
        this.navigator = navigator;
    }

    public void init(FragmentActivity fragmentActivity) {

        this.fragmentActivity = fragmentActivity;
        initGoogle(fragmentActivity);
    }


    private void initGoogle(FragmentActivity fragment) {

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

       apiClientRepository.init(fragment,this);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null && !hasEnter) {
                    hasEnter = true;
                    navigator.openMain();
                    // User is signed in
                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    hasEnter = false;
                    // User is signed out
                    //Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        loginWithGoogleInteractor.execute(mAuth,mAuthListener);

    }

    public void signIn(){
        apiClientRepository.signIn();
    }
    public void signOut(){
        apiClientRepository.signOut(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {

            }
        });
    }

    public void disconnect(){
        apiClientRepository.revokeAccess(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {

            }
        });
    }

    public void onActivityResult(int requestCode,Intent data){
        loginWithGoogleInteractor.onActivityResult(requestCode, data, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {

                    Toast.makeText(fragmentActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Timber.d("Hello");
                    final FirebaseUser currentUser = mAuth.getCurrentUser();
                    FirebaseDataSource.generateUser(currentUser);
                }
            }
        });
    }

    public void onStart(){
        mAuth.addAuthStateListener(mAuthListener);
        loginWithGoogleInteractor.onStart(apiClientRepository.getGoogleApiClient());
    }

    public void onStop(){
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(apiClientRepository.getGoogleApiClient()).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        FirebaseAuth.getInstance().signOut();
                        //updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        /*try {
            connectionResult.startResolutionForResult((Activity) context, Constants.PLUS_CONNECT_RECOVER_ERROR_CODE);
        } catch (Exception e) {
            listener.onError(e.getMessage());
        }*/
    }
}
