package com.kukurodev.mykukuroaquarium.receivers

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.kukurodev.mykukuroaquarium.MainActivity
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.notifications.NotificationHelper

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        NotificationHelper.createNotificationChannel(context)

        val type = intent.getStringExtra("TYPE") ?: NotificationHelper.TYPE_DAY_3
        val titleRes = if (type == NotificationHelper.TYPE_DAY_4) R.string.notification_day4_title else R.string.notification_day3_title
        val bodyRes = if (type == NotificationHelper.TYPE_DAY_4) R.string.notification_day4_body else R.string.notification_day3_body

        val mainIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            mainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, NotificationHelper.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_welcome_gift)
            .setContentTitle(context.getString(titleRes))
            .setContentText(context.getString(bodyRes))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        try {
            with(NotificationManagerCompat.from(context)) {
                notify(if (type == NotificationHelper.TYPE_DAY_4) 2004 else 2003, builder.build())
            }
        } catch (e: SecurityException) {
            // Permission catch
        }
    }
}
