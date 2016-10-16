package com.mibaldi.kidbeaconmvp.features.GroupSettings;

import android.content.Context;
import android.net.Uri;

import com.mibaldi.kidbeaconmvp.base.BasePresenter;
import com.mibaldi.kidbeaconmvp.navigation.Navigator;
import com.mibaldi.kidbeaconmvp.repositories.GroupsRepository;
import com.mibaldi.kidbeaconmvp.services.firebase.FirebaseDataSource;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;
import com.mibaldi.kidbeaconmvp.ui.Views.GroupSettingsView;

import java.util.Date;

import javax.inject.Inject;

@PerActivity
public class GroupSettingsPresenter extends BasePresenter<GroupSettingsView> {
    Navigator navigator;
    @Inject
    ApiClientRepository apiClientRepository;
    @Inject
    GroupsRepository groupsRepository;
    private Context context;
    public OwnGroup ownGroup;
    boolean edit = false;
    private Uri fileUri;
    public int state = 0;

    @Inject
    public GroupSettingsPresenter(Navigator navigator) {
        this.navigator = navigator;
    }

    public void init(Context context) {

        this.context = context;
        //ownGroup = groupsRepository.getCurrentOwnGroup();

        this.ownGroup = new OwnGroup();

    }

    public void addGroup(String name) {
        if (name != "") {
            ownGroup.name = name;
            ownGroup.creation_date = new Date().toString();
            if (edit) {
                //FirebaseManager.editGroup(ownGroup);
            } else {
                FirebaseDataSource.generateGroup(ownGroup);
                navigator.finishActivity(context);
            }
        }
    }

   /* public void takePhoto(Uri fileUri) {

        navigator.makePhoto((BaseActivity)context,fileUri);
    }

    public void uploadPhoto(Uri fileUri,String name) throws IOException {
        Log.d("STATE",state+"");

        if (fileUri != null) {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), fileUri);
            getView().showHideProgressBar(true);
            FirebaseDataSource.uploadImage(bitmap, name, ownGroup, new ResponseListener() {
                @Override
                public void onSuccess(String s) {
                    ownGroup.photo = s;
                    Toast.makeText(context, "FOTO SACADA", Toast.LENGTH_SHORT).show();
                    getView().showHideProgressBar(false);
                    addGroup(name);

                }

                @Override
                public void onError(int code, String description) {
                    getView().showHideProgressBar(false);
                    Toast.makeText(context, description, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onProgress(long total, long progress) {
                    getView().paintProgress(total,progress);
                }
            });
        } else {
            Toast.makeText(context,"File URI Vacio",Toast.LENGTH_SHORT).show();
        }
    }

    public void showPreview(Uri fileUri) {
        getView().showImagePreview(fileUri);
    }*/
}
