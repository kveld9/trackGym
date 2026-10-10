package com.kveld9.trackgym.service

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.kveld9.trackgym.MainActivity
import com.kveld9.trackgym.R

object RestTimerNotificationManager {

    const val CHANNEL_ID = "rest_timer_channel"
    const val NOTIFICATION_ID = 2001

    const val ACTION_ADD_30 = "com.kveld9.trackgym.ACTION_ADD_30"
    const val ACTION_SKIP = "com.kveld9.trackgym.ACTION_SKIP"
    const val ACTION_COMPLETE_CURRENT_SET = "com.kveld9.trackgym.ACTION_COMPLETE_CURRENT_SET"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.notif_channel_timer_name)
            val descriptionText = context.getString(R.string.notif_channel_timer_desc)
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
                setShowBadge(false)
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showTimerNotification(
        context: Context,
        remainingSeconds: Int,
        totalSeconds: Int
    ) {
        if (!canPostNotifications(context)) return

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
            as? NotificationManager ?: return

        val notification = buildTimerNotification(context, remainingSeconds, totalSeconds)

        runCatching {
            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }

    private fun buildTimerNotification(
        context: Context,
        remainingSeconds: Int,
        totalSeconds: Int
    ): Notification {
        val contentIntent = PendingIntent.getActivity(
            context,
            0,
            Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val add30PendingIntent = createActionPendingIntent(context, 1, ACTION_ADD_30)
        val skipPendingIntent = createActionPendingIntent(context, 2, ACTION_SKIP)
        val completeSetPendingIntent = createActionPendingIntent(
            context,
            3,
            ACTION_COMPLETE_CURRENT_SET
        )

        val title = context.getString(R.string.rest_timer_title)
        val contentText = if (remainingSeconds > 0) {
            context.getString(R.string.notif_timer_running, remainingSeconds)
        } else {
            context.getString(R.string.notif_timer_finished)
        }

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle(title)
            .setContentText(contentText)
            .setContentIntent(contentIntent)
            .setOngoing(remainingSeconds > 0)
            .setOnlyAlertOnce(true)
            .setProgress(totalSeconds.coerceAtLeast(1), remainingSeconds.coerceAtLeast(0), false)
            .addAction(
                android.R.drawable.ic_input_add,
                context.getString(R.string.notif_action_add_30),
                add30PendingIntent
            )
            .addAction(
                android.R.drawable.ic_media_next,
                context.getString(R.string.notif_action_skip),
                skipPendingIntent
            )
            .addAction(
                android.R.drawable.checkbox_on_background,
                context.getString(R.string.notif_action_complete_set),
                completeSetPendingIntent
            )
            .build()
    }

    private fun createActionPendingIntent(
        context: Context,
        requestCode: Int,
        action: String
    ): PendingIntent = PendingIntent.getBroadcast(
        context,
        requestCode,
        Intent(action).setPackage(context.packageName),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    fun dismissNotification(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
            as? NotificationManager ?: return
        runCatching {
            notificationManager.cancel(NOTIFICATION_ID)
        }
    }

    private fun canPostNotifications(context: Context): Boolean {
        val notificationsEnabled = NotificationManagerCompat.from(context).areNotificationsEnabled()
        val permissionGranted = Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        return notificationsEnabled && permissionGranted
    }
}
