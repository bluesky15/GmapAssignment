package com.lkb.googlemapdemo

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MapApp : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { MapDataBase.getDataBase(this, applicationScope) }
    val repository by lazy { DataBaseRepository(database.mapDao()) }
}
