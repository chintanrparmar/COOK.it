
package it.cook.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import it.cook.MainActivity
import it.cook.R

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

// TODO: Step 1.1 extension function to send messages (GIVEN)
/**
 * Builds and delivers the notification.
 *
 * @param context, activity context.
 */
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    val CHANNEL_ID = "crp.com.nityagaan"
    val Channel_name: CharSequence = "Nitya Gaan" // The user-visible name of the channel.
    var importance = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        importance = NotificationManager.IMPORTANCE_HIGH
    }
    val intent = Intent(applicationContext, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_ONE_SHOT)
    val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
    builder.setContentTitle("Titl")
    builder.setContentText(messageBody)
    builder.setAutoCancel(true)
    builder.setSmallIcon(R.drawable.app_logo)
    builder.setContentIntent(pendingIntent)
    builder.setChannelId(CHANNEL_ID)
    val notificationManager =applicationContext. getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val mChannel = NotificationChannel(CHANNEL_ID, Channel_name, importance)
        notificationManager.createNotificationChannel(mChannel)
    }
    notificationManager.notify(0, builder.build())
}

// TODO: Step 1.14 Cancel all notifications
/**
 * Cancels all notifications.
 *
 */
fun NotificationManager.cancelNotifications() {
    cancelAll()
}
