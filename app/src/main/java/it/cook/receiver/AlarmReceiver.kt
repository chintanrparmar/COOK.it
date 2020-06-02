package it.cook.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import it.cook.R
import it.cook.utils.sendNotification

class AlarmReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
            context.getText(R.string.time_to_cook).toString(),
            context
        )
    }
}