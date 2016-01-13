package com.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.risen.androidwcc.R;

import java.util.Timer;
import java.util.TimerTask;

public class DialogActivity extends Activity {
    Timer timer;
    RelativeLayout rlDialog;
    broadcast b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Intent intent = getIntent();
        if (intent.hasExtra("msgContent")) {
            ((TextView) findViewById(R.id.tvMsg)).setText(intent.getStringExtra("msgContent"));
        }
        rlDialog = (RelativeLayout) findViewById(R.id.rlDialog);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.hasMessages(0);
            }
        }, 3000, 3000);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("LoadDialoge");
        b = new broadcast();
        registerReceiver(b, intentFilter);
    }

    Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == 0) {
                timer.cancel();
                rlDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }
    };

    public class broadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("Close")) {
                DialogActivity.this.finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        unregisterReceiver(b);
    }
}
