package com.toanhamster.baseandroid.app

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.StrictMode
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDex
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.Utils
import com.crashlytics.android.Crashlytics
import com.github.moduth.blockcanary.BlockCanary
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.toanhamster.baseandroid.R
import com.toanhamster.baseandroid.common.AppBlockCanaryContext
import com.toanhamster.baseandroid.common.ForegroundBackgroundListener
import com.toanhamster.baseandroid.utils.isDebug
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import io.fabric.sdk.android.Fabric
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins
import io.realm.Realm
import net.blendtv.tv.di.AppInjector
import java.io.IOException
import java.net.SocketException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by ToanDev on 24/2/19.
 * Email:Huynhvantoan.itc@gmail.com
 */

class App : Application(), HasActivityInjector, HasServiceInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    override fun activityInjector() = dispatchingAndroidInjector

    override fun serviceInjector()= dispatchingServiceInjector

    private lateinit var appObserver: ForegroundBackgroundListener
    private var mRefWatcher: RefWatcher by Delegates.notNull()

    companion object {
        lateinit var instance: App
            private set
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    fun getRefWatcher(context: Context?): RefWatcher? {
        val app = context?.applicationContext as? App
        return app?.mRefWatcher
    }

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        if (isDebug) {
            strictMode()
            setupLogger()
            //setupTest()
        }
        rxJava()
        setupData()
    }

    private fun strictMode() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .build())
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .build())
    }

    private fun setupLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag(getString(R.string.app_name))
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return isDebug
            }
        })
        ProcessLifecycleOwner.get()
                .lifecycle
                .addObserver(ForegroundBackgroundListener()
                .also { appObserver = it })
    }

    private fun setupData() {
        Utils.init(this)
        //PreferenceManager.initialize(this, Constants.APP_NAME)
        Realm.init(this)
        val fabric = Fabric.Builder(this)
                .kits(Crashlytics())
                .debuggable(false)
                .build()
        Fabric.with(fabric)
    }

    private fun setupTest() {
        (getSystemService(ACTIVITY_SERVICE) as? ActivityManager)?.apply {
            Logger.e("memorySize=$memoryClass")
        }
        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)
        BlockCanary.install(this, AppBlockCanaryContext()).start()
    }

    private fun rxJava(){
        RxJavaPlugins.setErrorHandler { e ->
            Logger.e("Undeliverable exception received, not sure what to do"+ e.message)
            if (e is OnErrorNotImplementedException || e is IOException || e is SocketException || e is InterruptedException
                    || e is NullPointerException || e is IllegalArgumentException || e is IllegalStateException) {
                e.printStackTrace()
                return@setErrorHandler
            }
        }
    }

}
