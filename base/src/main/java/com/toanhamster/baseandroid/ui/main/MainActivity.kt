package com.toanhamster.baseandroid.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.orhanobut.logger.Logger
import com.toanhamster.baseandroid.R
import com.toanhamster.baseandroid.event.AppEvent
import com.toanhamster.baseandroid.event.appEventFlowable
import com.toanhamster.baseandroid.event.appEventProcessor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {
    private var compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compositeDisposable.add(createAppEventsSubscription())
    }

    override fun onStart() {
        super.onStart()
        appEventProcessor.onNext(AppEvent.Show)
    }

    private fun createAppEventsSubscription(): Disposable =
        appEventFlowable
            .doOnNext { Logger.d("AppEvents$it") }
            .doOnNext {
                when (it) {
                    AppEvent.Show -> {

                    }
                    AppEvent.Dismiss -> {

                    }
                }
            }
            .subscribe()

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
        compositeDisposable = CompositeDisposable()
    }
}
