package com.toanhamster.baseandroid.di.module

import com.toanhamster.baseandroid.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.blendtv.tv.di.ActivityScope

/**
 * Created by ToanDev on 24/2/19.
 * Email:Huynhvantoan.itc@gmail.com
 */
@Suppress("unused")
@Module
abstract class ActivityBuildersModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(FragmentBuildersModule::class)])
    abstract fun contributeMainActivity(): MainActivity

}
