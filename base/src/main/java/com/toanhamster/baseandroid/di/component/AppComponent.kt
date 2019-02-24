package com.toanhamster.baseandroid.di.component

import com.toanhamster.baseandroid.app.App
import com.toanhamster.baseandroid.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by ToanDev on 24/2/19.
 * Email:Huynhvantoan.itc@gmail.com
 */

@Suppress("unused")
@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (NetworkModule::class),
    (DataModule::class),
    (ServiceModule::class),
    (ActivityBuildersModule::class),
    (ViewModelModule::class)])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
