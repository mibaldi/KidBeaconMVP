package com.mibaldi.kidbeaconmvp.features.GroupSettings;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.kidbeaconmvp.Base.BasePresenter;
import com.mibaldi.kidbeaconmvp.Navigation.Navigator;
import com.mibaldi.kidbeaconmvp.Services.Firebase.FirebaseDataSource;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.PerActivity;
import com.mibaldi.kidbeaconmvp.features.LoginFirebase.ApiClientRepository;
import com.mibaldi.kidbeaconmvp.ui.Views.GroupSettingsView;
import com.mibaldi.kidbeaconmvp.ui.Views.GroupSingleView;

import java.util.Date;

import javax.inject.Inject;

@PerActivity
public class GroupSettingsPresenter extends BasePresenter<GroupSettingsView> {
    Navigator navigator;
    @Inject
    ApiClientRepository apiClientRepository;
    private Context context;
    public  OwnGroup ownGroup;
    boolean edit = false;
    @Inject
    public GroupSettingsPresenter(Navigator navigator) {
        this.navigator= navigator;
    }
    public void init(Context context , OwnGroup ownGroup) {

        this.context = context;
        if (ownGroup != null){
            this.ownGroup = ownGroup;
            repaint();
        }else{
            this.ownGroup = new OwnGroup();
        }
    }
    public void addGroup(String name,String photo){
        if (name != "" && photo != "") {
            ownGroup.name = name;
            ownGroup.photo = photo;
            ownGroup.creation_date = new Date().toString();
            if (edit) {
                //FirebaseManager.editGroup(ownGroup);
            } else {
                FirebaseDataSource.generateGroup(ownGroup);
                navigator.finishActivity(context);
            }
        }
    }
    public void repaint(){
        getView().showGroupName(ownGroup.name);
        getView().showGroupPhoto(ownGroup.photo);
    }

}
