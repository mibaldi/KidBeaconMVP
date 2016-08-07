package com.mibaldi.kidbeaconmvp.Services.Firebase;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.data.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import timber.log.Timber;

/**
 * Created by mikelbalducieldiaz on 7/8/16.
 */

public final class FirebaseDataSource {
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refRoot = database.getReference();
    public static DatabaseReference refUsers = refRoot.child("Users");
    public static DatabaseReference refGroups = refRoot.child("Groups");
    public static DatabaseReference refBeacons = refRoot.child("Beacons");
    public static DatabaseReference refUserGroups = refUsers.child(mAuth.getCurrentUser().getUid()).child("groups");

    public FirebaseDataSource() {
    }

    public static void generateUser(FirebaseUser currentUser) {
        FirebaseDataSource.refUsers.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    String uid = currentUser.getUid();
                    String email = currentUser.getEmail();
                    String name = currentUser.getDisplayName();
                    User u = new User(uid, email, name);
                    FirebaseDataSource.refUsers.child(uid).setValue(u);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        /*RxFirebaseDatabase.observeValue(FirebaseDataSource.refUsers.child(currentUser.getUid()), User.class)
                .subscribe(user -> {
                    if (user != null) {
                        String uid = currentUser.getUid();
                        String email = currentUser.getEmail();
                        String name = currentUser.getDisplayName();
                        User u = new User(uid, email, name);
                        FirebaseDataSource.refUsers.child(uid).setValue(u);
                    }
                }, throwable -> {
                    Timber.e("signInWithCredential: %s", throwable.toString());
                });
                */
    }

    public static void generateGroup(OwnGroup ownGroup) {
        String uid = mAuth.getCurrentUser().getUid();
        String key = FirebaseDataSource.refGroups.push().getKey();
        ownGroup.id = key;
        Map<String, Object> ownGroupMap = ownGroup.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Groups/" + key, ownGroupMap);
        childUpdates.put("/Users/" + uid + "/groups/" + key, key);
        FirebaseDataSource.refRoot.updateChildren(childUpdates);
    }

    public static void generateBeacon(OwnBeacon ownBeacon, String groupKey) {

        String key = FirebaseDataSource.refBeacons.push().getKey();
        ownBeacon.id = key;
        Map<String, Object> ownBeaconMap = ownBeacon.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Beacons/" + key, ownBeaconMap);
        childUpdates.put("/Groups/" + groupKey + "/beacons/" + key, key);
        FirebaseDataSource.refRoot.updateChildren(childUpdates);
    }

    public  static void editGroup(OwnGroup ownGroup) {
        Map<String, Object> ownGroupMap = ownGroup.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Groups/" + ownGroup.id, ownGroupMap);
        FirebaseDataSource.refRoot.updateChildren(childUpdates);
    }

    public static void editBeacon(OwnBeacon ownBeacon) {
        Map<String, Object> ownBeaconMap = ownBeacon.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Beacons/" + ownBeacon.id, ownBeaconMap);
        FirebaseDataSource.refRoot.updateChildren(childUpdates);
    }

}
