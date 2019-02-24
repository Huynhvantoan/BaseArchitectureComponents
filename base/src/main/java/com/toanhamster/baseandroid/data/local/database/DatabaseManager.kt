package com.toanhamster.baseandroid.data.local.database

import com.orhanobut.logger.Logger
import com.toanhamster.baseandroid.utils.Constants
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by ToanDev on 07/06/18.
 * Email:Huynhvantoan.itc@gmail.com
 */

class DatabaseManager : RepositoryData {

    private val mRealmConfig: RealmConfiguration = RealmConfiguration.Builder()
            .name(Constants.DB_Realm)
            .deleteRealmIfMigrationNeeded()
            .migration(Migration())
            .schemaVersion(Constants.RealmVersion)
            .build()

    init {
        Realm.setDefaultConfiguration(mRealmConfig)
    }

    override fun closeRealm() {
        Realm.getDefaultInstance().apply {
            if (!isClosed) {
                Logger.e("closeRealm")
                close()
            }
        }
    }

}
