package it.cook.receiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.AlarmManagerCompat
import java.util.*

class AlarmHandler() {
    private var alarmManager: AlarmManager? = null
    fun setAlarm(context: Context, next: Calendar) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val now = Calendar.getInstance()
        next[Calendar.HOUR_OF_DAY] = next[Calendar.HOUR_OF_DAY]
        next[Calendar.MINUTE] = next[Calendar.MINUTE]
        next[Calendar.SECOND] = 0
        if (now.after(next)) next.add(Calendar.DATE, 1)

        if (Build.VERSION.SDK_INT >= 23) {
            AlarmManagerCompat.setExactAndAllowWhileIdle(
                alarmManager!!,
                AlarmManager.RTC_WAKEUP,
                next.timeInMillis,
                getIntent(context)
            )
        } else  AlarmManagerCompat.setExact(alarmManager!!,AlarmManager.RTC_WAKEUP, next.timeInMillis, getIntent(context))

    }

    private fun getIntent(context: Context): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java)

        return PendingIntent.getBroadcast(
            context,
            12,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

    }
}