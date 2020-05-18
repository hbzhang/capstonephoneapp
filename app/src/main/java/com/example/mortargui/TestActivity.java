package com.example.mortargui;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends Activity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv = new TextView(this);
        setContentView(tv);
        update();
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(100);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                update();
                            }
                        });
                    }
                } catch (InterruptedException ignored) {}
            }
        };
        t.start();
        startService(new Intent(this, TestService.class));
    }
    private void update() {
        Notification notification = null;
        // update your interface here
        tv.setText(TestService.threat);
        notification.defaults |= Notification.DEFAULT_SOUND;
    }

}
