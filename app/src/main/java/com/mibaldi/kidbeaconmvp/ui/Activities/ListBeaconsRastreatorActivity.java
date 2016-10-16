package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.mibaldi.kidbeaconmvp.base.BaseMVPActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.HasComponent;
import com.mibaldi.kidbeaconmvp.features.ListBeaconsRastreator.DaggerListBeaconsRastreatorComponent;
import com.mibaldi.kidbeaconmvp.features.ListBeaconsRastreator.ListBeaconsRastreatorComponent;
import com.mibaldi.kidbeaconmvp.features.ListBeaconsRastreator.ListBeaconsRastreatorPresenter;
import com.mibaldi.kidbeaconmvp.ui.Adapters.ListBeaconsRastreatorAdapter;
import com.mibaldi.kidbeaconmvp.ui.Views.ListBeaconsRastreatorView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import timber.log.Timber;

public class ListBeaconsRastreatorActivity extends BaseMVPActivity<ListBeaconsRastreatorPresenter,ListBeaconsRastreatorView> implements ListBeaconsRastreatorView, HasComponent<ListBeaconsRastreatorComponent>, BeaconConsumer {
    private ListBeaconsRastreatorComponent component;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    private OwnGroup ownGroup;
    private BeaconManager beaconManager;
    public List<OwnBeacon> items = new ArrayList<>();
    private SpotsDialog dialog;
    @Inject
    public ListBeaconsRastreatorAdapter adapter;
    @BindView(R.id.beaconsList)
    RecyclerView recyclerView;
    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_beacons_rastreator);
        ButterKnife.bind(this);
        //ownGroup = getIntent().getParcelableExtra("ownGroup");

        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        createLoadingDialog();
        presenter.init();

        permission_bluetooth();

        //presenter.loadBeaconsService();


    }
    private void createLoadingDialog() {
        dialog = new SpotsDialog(this,"Cargando beacons");
    }
    private void verifyBluetooth() {

        try {
            if (!BeaconManager.getInstanceForApplication(this).checkAvailability()) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Bluetooth not enabled");
                builder.setMessage("Please enable bluetooth in settings and restart this application.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                        System.exit(0);
                    }
                });
                builder.show();
            }
        } catch (RuntimeException e) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Bluetooth LE not available");
            builder.setMessage("Sorry, this device does not support Bluetooth LE.");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                    System.exit(0);
                }

            });
            builder.show();

        }

    }

    private void permission_bluetooth() {
        verifyBluetooth();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M Permission check

            if (this.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("This app needs location access");
                builder.setMessage("Please grant location access so this app can detect beacons in the background.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                    @TargetApi(23)
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                                PERMISSION_REQUEST_COARSE_LOCATION);
                    }

                });
                builder.show();
            }
        }
    }

    @NonNull
    @Override
    public ListBeaconsRastreatorPresenter createPresenter() {
        return component.presenter();
    }

    private void initializeInjector() {
        this.component = DaggerListBeaconsRastreatorComponent.builder()
                .kidBeaconApplicationComponent(getInjector())
                .build();
        component.inject(this);
    }

    public ListBeaconsRastreatorComponent getComponent(){
        return component;
    }

    public static Intent getCallingIntent(Context context){
        return new Intent(context,ListBeaconsRastreatorActivity.class);
    }


    @Override
    public void showOwnBeaconList(List<OwnBeacon> ownBeaconList) {
        this.items = ownBeaconList;
        adapter.setDataAndListener(items);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void showDialog(Boolean b) {
        if (b){
            dialog.show();
        }else{
            dialog.hide();
        }
    }

    @Override
    public void showTitle(String ownGroupName) {
        title.setText(ownGroupName);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
                if (collection.size() > 0) {
                    for (Beacon b : collection) {
                        OwnBeacon o = new OwnBeacon(b.getIdentifier(0).toString(), b.getIdentifier(1).toString(), b.getIdentifier(2).toString());
                        if (items.contains(o)) {
                            int index = items.indexOf(o);
                            OwnBeacon ownBeacon = items.get(index);
                            ownBeacon.distance = b.getDistance();
                            items.set(index, ownBeacon);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyDataSetChanged();
                                }
                            });

                            Log.i("ListBeaconsRastreator", "The first beacon I see is about " + b.getIdentifier(0));
                        } else {
                            Timber.i("Sin beacons en este grupo");
                        }
                    }

                }
            }
        });
        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
        }
    }
}
