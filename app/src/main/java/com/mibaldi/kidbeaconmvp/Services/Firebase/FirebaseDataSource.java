package com.mibaldi.kidbeaconmvp.services.firebase;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.data.User;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

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
    public static StorageReference mStorage = FirebaseStorage.getInstance().getReference();


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

    public static void editGroup(OwnGroup ownGroup) {
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

    public static void addPhotoGroup(String url, String key) {
        FirebaseDataSource.refGroups.child(key).child("Photo").setValue(url);
    }

    public static void uploadImage(Bitmap bitmap, String name, OwnGroup ownGroup, ResponseListener listener) {


        /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();*/
        /*File outputDir = context.getApplicationContext().getCacheDir();
        context.getApplicationContext().getFilesDir();  // context being the Activity pointer
        try {
            File outputFile = File.createTempFile("prefix", ".jpg", outputDir);
            Uri file = Uri.fromFile(outputFile);
            StorageReference riversRef = mStorage.child("images/rivers.jpg");

            riversRef.putFile(file)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            Log.d("ERROR", "error");
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        StorageReference riversRef = mStorage.child("images/"+ name);
        UploadTask uploadTask = riversRef.putBytes(data);
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                long total = taskSnapshot.getTotalByteCount();
                long transferred = taskSnapshot.getBytesTransferred();
                listener.onProgress(total,transferred);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                listener.onError(1,"fallo en la subida");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                //addPhotoGroup(downloadUrl.toString(),ownGroup.id);
                listener.onSuccess(downloadUrl.toString());
            }
        });


    }

}
