package com.pragma.entomologo.di.sources

import android.content.Context
import com.pragma.entomologo.sources.gpsLocation.GPSLocation
import com.pragma.entomologo.sources.gpsLocation.GPSLocationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GeoLocationModule {

    @Provides
    @Singleton
    fun provideGeoLocation(
        @ApplicationContext context: Context
    ): GPSLocation = GPSLocationImpl(
        context = context
    )

}