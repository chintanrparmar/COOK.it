package it.cook.receiver

import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import it.cook.MainActivity
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

        alarmManager?.setAlarmClock(
            AlarmClockInfo(
                next.timeInMillis,
                PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0)
            ),
            getIntent(context)
        )
        val intent = Intent("android.intent.action.ALARM_CHANGED")
        intent.putExtra("alarmSet", true)
        context.sendBroadcast(intent)
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