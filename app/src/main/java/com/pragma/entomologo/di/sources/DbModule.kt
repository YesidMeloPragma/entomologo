package com.pragma.entomologo.di.sources

import android.content.Context
import com.pragma.entomologo.sources.database.DatabaseApp
import com.pragma.entomologo.sources.database.dao.CounterRecordInsectDao
import com.pragma.entomologo.sources.database.dao.EntomologistDao
import com.pragma.entomologo.sources.database.dao.GeoLocationDao
import com.pragma.entomologo.sources.database.dao.InsectDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext applicationContext: Context) : DatabaseApp {
        DatabaseApp.startDatabase(aplicationContext = applicationContext)
        return DatabaseApp.getDB()
    }

    @Provides
    fun provideCounterRecordInsectDao(databaseApp: DatabaseApp): CounterRecordInsectDao = databaseApp.getCounterRecordInsectDao()

    @Provides
    fun provideEntomologistDao(databaseApp: DatabaseApp): EntomologistDao = databaseApp.getEntomologistDao()

    @Provides
    fun provideGeoLocationDao(databaseApp: DatabaseApp): GeoLocationDao = databaseApp.getGeoLocationDao()

    @Provides
    fun providesInsectDao(databaseApp: DatabaseApp): InsectDao = databaseApp.getInsectDao()
}