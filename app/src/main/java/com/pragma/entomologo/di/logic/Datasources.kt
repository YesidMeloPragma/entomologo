package com.pragma.entomologo.di.logic

import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource.CounterRecordInsectLocalDatasource
import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource.CounterRecordInsectLocalDatasourceImpl
import com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource.EntomologistImageDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource.EntomologistImageDatasourceImpl
import com.pragma.entomologo.logic.datasources.entomologistDatasource.localDatasource.EntomologistLocalDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.localDatasource.EntomologistLocalDatasourceImpl
import com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource.EntomologistSPDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource.EntomologistSPDatasourceImpl
import com.pragma.entomologo.logic.datasources.geoLocationDatasource.localDatasource.GeoLocationLocalDatasource
import com.pragma.entomologo.logic.datasources.geoLocationDatasource.localDatasource.GeoLocationLocalDatasourceImpl
import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasource
import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasourceImpl
import com.pragma.entomologo.sources.appImageGallery.ImageAppGallery
import com.pragma.entomologo.sources.database.dao.CounterRecordInsectDao
import com.pragma.entomologo.sources.database.dao.EntomologistDao
import com.pragma.entomologo.sources.database.dao.GeoLocationDao
import com.pragma.entomologo.sources.database.dao.InsectDao
import com.pragma.entomologo.sources.sharedPreferences.CustomSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class Datasources {

    @Provides
    fun providesCounterInsectLocalDatasource(
        counterRecordInsectDao: CounterRecordInsectDao
    ) : CounterRecordInsectLocalDatasource = CounterRecordInsectLocalDatasourceImpl(
        counterRecordInsectDao = counterRecordInsectDao
    )

    //region Entomologist
    @Provides
    fun provideEntomologistLocalDatasource(
        entomologistDao: EntomologistDao
    ) : EntomologistLocalDatasource = EntomologistLocalDatasourceImpl(
        entomologistDao = entomologistDao
    )

    @Provides
    fun providesEntomologistSPDatasource(
        customSharedPreferences: CustomSharedPreferences
    ) : EntomologistSPDatasource = EntomologistSPDatasourceImpl(
        customSharedPreferences = customSharedPreferences
    )

    @Provides
    fun provideEntomologistImageDatasource(
        imageAppGallery: ImageAppGallery
    ): EntomologistImageDatasource = EntomologistImageDatasourceImpl(
        imageAppGallery = imageAppGallery
    )
    //endregion

    @Provides
    fun provideGeolocationLocalDatasource(
        geoLocationDao: GeoLocationDao
    ) : GeoLocationLocalDatasource = GeoLocationLocalDatasourceImpl(
        geoLocationDao = geoLocationDao
    )

    @Provides
    fun provideInsectLocalDatasource(insectDao: InsectDao): InsectLocalDatasource = InsectLocalDatasourceImpl(insectDao = insectDao)
}