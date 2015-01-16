package com.bojie.gcm;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button btnRegId;
    EditText etRegId;
    GoogleCloudMessaging gcm;
    String regId;
    final String PROJECT_NUMBER = "735494812190";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegId = (Button) findViewById(R.id.btn_get_id);
        etRegId = (EditText) findViewById(R.id.et_id);

        btnRegId.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        getRegId();
    }

    public void getRegId() {

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";

                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }

                    regId = gcm.register(PROJECT_NUMBER);
                    msg = "Device registered, registration ID = " + regId;
                    Log.i("GCM", "!!!!! " + regId);


                } catch (IOException e) {
                    msg = "Error: " + e.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                etRegId.setText(msg + "\n");
            }
        }.execute(null, null, null);
    }
}