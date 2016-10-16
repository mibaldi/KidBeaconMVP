package com.mibaldi.kidbeaconmvp.ui.Activities;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mibaldi.kidbeaconmvp.base.BaseMVPActivity;
import com.mibaldi.kidbeaconmvp.R;
import com.mibaldi.kidbeaconmvp.services.firebase.FirebaseDataSource;
import com.mibaldi.kidbeaconmvp.data.OwnBeacon;
import com.mibaldi.kidbeaconmvp.data.OwnGroup;
import com.mibaldi.kidbeaconmvp.di.HasComponent;
import com.mibaldi.kidbeaconmvp.features.NFC.DaggerNfcComponent;
import com.mibaldi.kidbeaconmvp.features.NFC.NfcComponent;
import com.mibaldi.kidbeaconmvp.features.NFC.NfcPresenter;
import com.mibaldi.kidbeaconmvp.ui.Views.NfcView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NfcActivity extends BaseMVPActivity<NfcPresenter, NfcView> implements NfcView, HasComponent<NfcComponent> {
    private NfcComponent component;

    @BindView(R.id.beaconName)
    TextView beaconName;

    @BindView(R.id.saveBeacon)
    Button saveBeaconBtn;
    private OwnBeacon ownBeacon;
    private OwnGroup ownGroup;
    private NfcAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        presenter.init(this);
        ownGroup = getIntent().getParcelableExtra("ownGroup");
        ButterKnife.bind(this);
        checkNFC();
        saveBeaconBtn.setEnabled(false);
        adapter = NfcAdapter.getDefaultAdapter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //checkNFC();
        enableForegroundDispatchSystem();
    }

    @Override
    protected void onPause() {
        super.onPause();

        disableForegroundDispatchSystem();
    }

    private void enableForegroundDispatchSystem() {

        if (ownGroup != null && adapter != null) {
            Intent intent = new Intent(this, NfcActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            IntentFilter[] intentFilters = new IntentFilter[]{};

            adapter.enableForegroundDispatch(this, pendingIntent, intentFilters, null);
        }else {
            Snackbar.make(findViewById(android.R.id.content),"No se puede agregar por no tener nfc",Snackbar.LENGTH_INDEFINITE).show();
        }


    }

    private void disableForegroundDispatchSystem() {
        if (adapter != null){
            adapter.disableForegroundDispatch(this);
        }
    }

    @NonNull
    @Override
    public NfcPresenter createPresenter() {
        return component.presenter();
    }

    private void initializeInjector() {
        this.component = DaggerNfcComponent.builder()
                .kidBeaconApplicationComponent(getInjector())
                .build();
    }

    public NfcComponent getComponent() {
        return component;
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, NfcActivity.class);
    }

    public String[] parseString(String nfcResponse) {
        String[] nfcResponses = nfcResponse.split(";");
        String uuid = "";
        String major = "";
        String minor = "";
        String name = "";
        if (nfcResponses.length == 4) {
            uuid = nfcResponses[0].split(":")[1];

            major = nfcResponses[1].split(":")[1];
            minor = nfcResponses[2].split(":")[1];
            name = nfcResponses[3].split(":")[1];
        }
        String[] beaconFields = {uuid, major, minor, name};


        return beaconFields;
    }

    public OwnBeacon generateBeacon(String[] beaconFields) {
        OwnBeacon ownBeacon = new OwnBeacon();
        ownBeacon.uuid = beaconFields[0];
        ownBeacon.major = beaconFields[1];
        ownBeacon.minor = beaconFields[2];
        ownBeacon.name = beaconFields[3];
        return ownBeacon;
    }

    @OnClick(R.id.saveBeacon)
    public void saveBeacon() {
        if (ownBeacon != null) {
            FirebaseDataSource.generateBeacon(ownBeacon, ownGroup.id);
            Toast.makeText(this, "Beacon guardado" + ownBeacon.name, Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void checkNFC() {
        if (adapter != null) {
            if (!adapter.isEnabled()) {
                if (android.os.Build.VERSION.SDK_INT >= 16) {
                    startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
                } else {
                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                }
            }
        }

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
            ownBeacon = readTag(intent);
            if (ownBeacon != null) {
                saveBeaconBtn.setEnabled(true);
            }
        }
    }

    private OwnBeacon readTag(Intent intent) {
        String content = "";

        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

        if (parcelableArrayExtra != null) {
            NdefMessage[] ndefMessages = new NdefMessage[parcelableArrayExtra.length];
            for (int i = 0; i < parcelableArrayExtra.length; i++) {
                ndefMessages[i] = (NdefMessage) parcelableArrayExtra[i];
            }

            if (ndefMessages[0] != null) {
                byte[] payload = ndefMessages[0].getRecords()[0].getPayload();
                for (byte aPayload : payload) {
                    content += (char) aPayload;
                }
                String[] beaconFields = parseString(content);
                OwnBeacon ownBeacon = generateBeacon(beaconFields);

                return ownBeacon;
            }

        }

        return null;
    }

}
