package com.pragma.entomologo.di.logic

import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource.CounterRecordInsectLocalDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource.EntomologistImageDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.localDatasource.EntomologistLocalDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource.EntomologistSPDatasource
import com.pragma.entomologo.logic.datasources.geoLocationDatasource.localDatasource.GeoLocationLocalDatasource
import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasource
import com.pragma.entomologo.logic.usesCase.addInsectUseCase.AddInsectUseCase
import com.pragma.entomologo.logic.usesCase.addInsectUseCase.AddInsectUseCaseImpl
import com.pragma.entomologo.logic.usesCase.getAllCountersUseCase.GetAllCountersUseCase
import com.pragma.entomologo.logic.usesCase.getAllCountersUseCase.GetAllCountersUseCaseImpl
import com.pragma.entomologo.logic.usesCase.getAllInsectsUseCase.GetAllInsectsUseCase
import com.pragma.entomologo.logic.usesCase.getAllInsectsUseCase.GetAllInsectsUseCaseImpl
import com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase.GetImageProfileEntomologistUseCase
import com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase.GetImageProfileEntomologistUseCaseImpl
import com.pragma.entomologo.logic.usesCase.isEntomologistRegisteredUseCase.IsEntomologistRegisteredUseCase
import com.pragma.entomologo.logic.usesCase.isEntomologistRegisteredUseCase.IsEntomologistRegisteredUseCaseImpl
import com.pragma.entomologo.logic.usesCase.registerEntomologistUseCase.RegisterEntomologistUseCase
import com.pragma.entomologo.logic.usesCase.registerEntomologistUseCase.RegisterEntomologistUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UsesCase {

    @Provides
    fun provideAddInsectUseCase(insectLocalDatasource: InsectLocalDatasource): AddInsectUseCase = AddInsectUseCaseImpl(insectLocalDatasource = insectLocalDatasource)

    @Provides
    fun provideGetAllInsectsUseCase(insectLocalDatasource: InsectLocalDatasource) : GetAllInsectsUseCase = GetAllInsectsUseCaseImpl(insectLocalDatasource = insectLocalDatasource)

    @Provides
    fun provideGetAllCountersInsectsUseCase(
        counterRecordInsectLocalDatasource: CounterRecordInsectLocalDatasource,
        insectLocalDatasource: InsectLocalDatasource,
        geoLocationLocalDatasource: GeoLocationLocalDatasource
    ): GetAllCountersUseCase = GetAllCountersUseCaseImpl(
        counterRecordInsectLocalDatasource = counterRecordInsectLocalDatasource,
        insectLocalDatasource = insectLocalDatasource,
        geoLocationLocalDatasource = geoLocationLocalDatasource
    )

    @Provides
    fun provideGetImageProfileEntomologistUseCase(
        entomologistSPDatasource: EntomologistSPDatasource,
        entomologistImageDatasource: EntomologistImageDatasource
    ) : GetImageProfileEntomologistUseCase = GetImageProfileEntomologistUseCaseImpl(
        entomologistSPDatasource = entomologistSPDatasource,
        entomologistImageDatasource = entomologistImageDatasource
    )

    @Provides
    fun provideIsEntomologistRegisteredUseCase(
        entomologistSPDatasource: EntomologistSPDatasource
    ) : IsEntomologistRegisteredUseCase = IsEntomologistRegisteredUseCaseImpl(
        entomologistSPDatasource = entomologistSPDatasource
    )

    @Provides
    fun provideRegisterEntomologistUseCase(
        entomologistImageDatasource: EntomologistImageDatasource,
        entomologistLocalDatasource: EntomologistLocalDatasource,
        entomologistSPDatasource: EntomologistSPDatasource
    ) : RegisterEntomologistUseCase = RegisterEntomologistUseCaseImpl(
        entomologistImageDatasource = entomologistImageDatasource,
        entomologistLocalDatasource = entomologistLocalDatasource,
        entomologistSPDatasource = entomologistSPDatasource
    )
}