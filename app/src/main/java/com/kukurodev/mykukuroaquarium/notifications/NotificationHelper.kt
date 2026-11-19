package com.kukurodev.mykukuroaquarium.notifications

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.data.Constants.DAY_3_WARNING_MS
import com.kukurodev.mykukuroaquarium.data.Constants.DAY_4_WARNING_MS
import com.kukurodev.mykukuroaquarium.receivers.NotificationReceiver

object NotificationHelper {
    const val CHANNEL_ID = "aquarium_alerts"
    const val TYPE_DAY_3 = "DAY_3"
    const val TYPE_DAY_4 = "DAY_4"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.notification_channel_name)
            val descriptionText = context.getString(R.string.notification_channel_desc)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun scheduleNotifications(context: Context, lastFedTime: Long) {
        createNotificationChannel(context)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val day3Trigger = lastFedTime + DAY_3_WARNING_MS
        if (day3Trigger > System.currentTimeMillis()) {
            val intent3 = Intent(context, NotificationReceiver::class.java).apply {
                putExtra("TYPE", TYPE_DAY_3)
            }
            val pending3 = PendingIntent.getBroadcast(
                context,
                1003,
                intent3,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            alarmManager.set(AlarmManager.RTC_WAKEUP, day3Trigger, pending3)
        }

        val day4Trigger = lastFedTime + DAY_4_WARNING_MS
        if (day4Trigger > System.currentTimeMillis()) {
            val intent4 = Intent(context, NotificationReceiver::class.java).apply {
                putExtra("TYPE", TYPE_DAY_4)
            }
            val pending4 = PendingIntent.getBroadcast(
                context,
                1004,
                intent4,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            alarmManager.set(AlarmManager.RTC_WAKEUP, day4Trigger, pending4)
        }
    }
}
