package com.tianyu.kitchenassist;

/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.app.NotificationManager;
import android.app.Notification;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.widget.Toast;
// Need the following import to get access to the app resources, since this
// class is in a sub-package.

/**
 * This is an example of implement an {@link BroadcastReceiver} for an alarm that
 * should occur once.
 * <p>
 * When the alarm goes off, we show a <i>Toast</i>, a quick message.
 */
public class OneShotAlarm extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "message received", Toast.LENGTH_SHORT).show();
        Notification.Builder builder = new Notification.Builder(context)
                .setTicker("主人，有食品要过期了")
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentTitle("厨房助手")
                .setContentText("打开瞅瞅")
                .setAutoCancel(true)
                .setContentIntent(TaskStackBuilder.create(context)
                        .addParentStack(ListViewActivity.class)
                        .addNextIntent(new Intent(context, ListViewActivity.class)
                                .putExtra("test", "From Notification"))
                        .getPendingIntent(0, 0));
        builder.setDefaults(builder.build().DEFAULT_ALL);
        NotificationManager nm = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        nm.notify("direct_tag", R.string.notification, builder.build());
        intent = new Intent(this, ListViewActivity.class);
    }
}
