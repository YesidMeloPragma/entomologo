package com.pragma.entomologo.di.logic

import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource.CounterRecordInsectLocalDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource.EntomologistImageDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.localDatasource.EntomologistLocalDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource.EntomologistSPDatasource
import com.pragma.entomologo.logic.datasources.geoLocationDatasource.localDatasource.GeoLocationLocalDatasource
import com.pragma.entomologo.logic.datasources.insectDatasource.imageDatasource.InsectImageLocalDatasource
import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasource
import com.pragma.entomologo.logic.usesCase.addInsectUseCase.AddInsectUseCase
import com.pragma.entomologo.logic.usesCase.addInsectUseCase.AddInsectUseCaseImpl
import com.pragma.entomologo.logic.usesCase.getAllCountersUseCase.GetAllCountersUseCase
import com.pragma.entomologo.logic.usesCase.getAllCountersUseCase.GetAllCountersUseCaseImpl
import com.pragma.entomologo.logic.usesCase.getAllInsectsUseCase.GetAllInsectsUseCase
import com.pragma.entomologo.logic.usesCase.getAllInsectsUseCase.GetAllInsectsUseCaseImpl
import com.pragma.entomologo.logic.usesCase.getImageInsectUseCase.GetImageInsectUseCase
import com.pragma.entomologo.logic.usesCase.getImageInsectUseCase.GetImageInsectUseCaseImpl
import com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase.GetImageProfileEntomologistUseCase
import com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase.GetImageProfileEntomologistUseCaseImpl
import com.pragma.entomologo.logic.usesCase.getInsectWithImageByIdUseCase.GetInsectWithImageByIdUseCase
import com.pragma.entomologo.logic.usesCase.getInsectWithImageByIdUseCase.GetInsectWithImageByIdUseCaseImpl
import com.pragma.entomologo.logic.usesCase.isEntomologistRegisteredUseCase.IsEntomologistRegisteredUseCase
import com.pragma.entomologo.logic.usesCase.isEntomologistRegisteredUseCase.IsEntomologistRegisteredUseCaseImpl
import com.pragma.entomologo.logic.usesCase.registerEntomologistUseCase.RegisterEntomologistUseCase
import com.pragma.entomologo.logic.usesCase.registerEntomologistUseCase.RegisterEntomologistUseCaseImpl
import com.pragma.entomologo.logic.usesCase.registerRecordInsectUseCase.RegisterRecordInsectUseCase
import com.pragma.entomologo.logic.usesCase.registerRecordInsectUseCase.RegisterRecordInsectUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UsesCase {

    @Provides
    fun provideAddInsectUseCase(
        insectLocalDatasource: InsectLocalDatasource,
        insectImageLocalDatasource: InsectImageLocalDatasource,
    ): AddInsectUseCase = AddInsectUseCaseImpl(
        insectLocalDatasource = insectLocalDatasource,
        insectImageLocalDatasource = insectImageLocalDatasource
    )

    @Provides
    fun provideGetAllInsectsUseCase(insectLocalDatasource: InsectLocalDatasource) : GetAllInsectsUseCase = GetAllInsectsUseCaseImpl(insectLocalDatasource = insectLocalDatasource)

    @Provides
    fun provideGetAllCountersInsectsUseCase(
        counterRecordInsectLocalDatasource: CounterRecordInsectLocalDatasource,
        insectImageLocalDatasource: InsectImageLocalDatasource
    ): GetAllCountersUseCase = GetAllCountersUseCaseImpl(
        counterRecordInsectLocalDatasource = counterRecordInsectLocalDatasource,
        insectImageLocalDatasource = insectImageLocalDatasource
    )

    @Provides
    fun provideGetImageInsectUseCase(
        insectImageLocalDatasource: InsectImageLocalDatasource
    ) : GetImageInsectUseCase = GetImageInsectUseCaseImpl(
        insectImageLocalDatasource = insectImageLocalDatasource
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
    fun provideGetInsectWithImageByIdUseCase(
        insectImageLocalDatasource: InsectImageLocalDatasource,
        insectLocalDatasource: InsectLocalDatasource
    ) : GetInsectWithImageByIdUseCase = GetInsectWithImageByIdUseCaseImpl(
        insectImageLocalDatasource = insectImageLocalDatasource,
        insectLocalDatasource = insectLocalDatasource
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

    @Provides
    fun provideRegisterRecordInsectUseCase(
        counterRecordInsectLocalDatasource: CounterRecordInsectLocalDatasource,
        geoLocationLocalDatasource: GeoLocationLocalDatasource
    ) : RegisterRecordInsectUseCase = RegisterRecordInsectUseCaseImpl(
        counterRecordInsectLocalDatasource = counterRecordInsectLocalDatasource,
        geoLocationLocalDatasource = geoLocationLocalDatasource
    )
}