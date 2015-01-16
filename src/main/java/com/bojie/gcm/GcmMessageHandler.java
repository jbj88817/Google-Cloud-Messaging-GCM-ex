package com.bojie.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;


/**
 * Created by bojiejiang on 12/16/14.
 */
public class GcmMessageHandler extends IntentService {

    String mes;
    private Handler mHandler;

    public GcmMessageHandler() {
        super("GcmMessageHandler");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);
        mes = extras.getString("title");
        showToast();
        Log.i("GCM", "Recevied: (" + messageType + ") " + extras.getString("title"));

        GcmReceiver.completeWakefulIntent(intent);
    }

    public void showToast() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), mes, Toast.LENGTH_LONG).show();
            }
        });
    }
}
