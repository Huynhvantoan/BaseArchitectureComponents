package com.toanhamster.baseandroid.di.module

import com.toan_itc.core.architecture.AppExecutors
import dagger.Module
import dagger.Provides
import com.toanhamster.baseandroid.data.local.database.DatabaseManager
import javax.inject.Singleton

/**
 * Created by ToanDev on 24/2/19.
 * Email:Huynhvantoan.itc@gmail.com
 */

@Module
class DataModule {

    @Singleton
    @Provides
    fun realmManager(): DatabaseManager = DatabaseManager()

    @Singleton
    @Provides
    fun appExecutors(): AppExecutors = AppExecutors()
}
