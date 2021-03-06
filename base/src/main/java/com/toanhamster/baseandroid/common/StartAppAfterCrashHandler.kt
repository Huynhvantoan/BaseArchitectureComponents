package com.toanhamster.baseandroid.common

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.crashlytics.android.Crashlytics
import com.toanhamster.baseandroid.app.App
import com.toanhamster.baseandroid.ui.main.MainActivity

/**
 * Created by ToanDev on 24/2/19.
 * Email:Huynhvantoan.itc@gmail.com
 */

class StartAppAfterCrashHandler(private val activity: Activity) : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(thread: Thread, ex: Throwable) {
        Crashlytics.logException(ex)
        val intent = Intent(activity, MainActivity::class.java)
        intent.putExtra("crash", true)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                or Intent.FLAG_ACTIVITY_NEW_TASK)
        val pendingIntent = PendingIntent.getActivity(App.instance, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val mgr = App.instance.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent)
        activity.finish()
        System.exit(2)
    }
}
