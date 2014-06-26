package com.tianyu.kitchenassist;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity {
    Toast mToast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent;
            //@SuppressWarnings("unchecked")
        switch (view.getId()) {
            case R.id.add_item:
                intent = new Intent(this, AddItemActivity.class);
                startActivity(intent);
                break;
            case R.id.button_list_view:
                intent = new Intent(this, ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.button_start_timer:
                startTimer();
                break;

                }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void startTimer() {
        Intent intent = new Intent(MainActivity.this, OneShotAlarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this,
                0, intent, 0);

        // We want the alarm to go off 5 seconds from now.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 5);

        // Schedule the alarm!
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

        // Tell the user about what we did.
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(MainActivity.this, "timerStart",
                Toast.LENGTH_LONG);
        mToast.show();
    }
}
