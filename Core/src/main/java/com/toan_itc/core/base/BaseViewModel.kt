package com.toan_itc.core.base

import androidx.lifecycle.ViewModel
import com.orhanobut.logger.Logger
import com.toan_itc.core.base.event.Event
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor


/**
 * Created by ToanDev on 24/2/19.
 * Email:Huynhvantoan.itc@gmail.com
 */

abstract class BaseViewModel : ViewModel(){
    private var mCompositeDisposable: CompositeDisposable?=null
    private val subject = PublishProcessor.create<Any>()

    override fun onCleared() {
        super.onCleared()
        Logger.d("onCleared")
        mCompositeDisposable?.clear()
    }

    fun getCompositeDisposable(): CompositeDisposable {
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable = CompositeDisposable()
        }
        return mCompositeDisposable!!
    }

    fun <T : Event> post(event: T) = subject.onNext(event)

    fun <T : Event> stream(event: Class<T>): Flowable<T> = subject.cast(event)
}
