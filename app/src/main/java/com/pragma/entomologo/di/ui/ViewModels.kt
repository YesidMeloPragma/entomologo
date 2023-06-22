package com.pragma.entomologo.di.ui

import com.pragma.entomologo.logic.usesCase.addInsectUseCase.AddInsectUseCase
import com.pragma.entomologo.logic.usesCase.getAllCountersUseCase.GetAllCountersUseCase
import com.pragma.entomologo.logic.usesCase.getAllInsectsUseCase.GetAllInsectsUseCase
import com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase.GetImageProfileEntomologistUseCase
import com.pragma.entomologo.logic.usesCase.isEntomologistRegisteredUseCase.IsEntomologistRegisteredUseCase
import com.pragma.entomologo.logic.usesCase.registerEntomologistUseCase.RegisterEntomologistUseCase
import com.pragma.entomologo.ui.views.formSpecieView.viewmodel.FormSpecieViewModel
import com.pragma.entomologo.ui.views.formSpecieView.viewmodel.FormSpecieViewModelImpl
import com.pragma.entomologo.ui.views.registerEntomologistView.viewModel.RegisterEntomologyViewModel
import com.pragma.entomologo.ui.views.registerEntomologistView.viewModel.RegisterEntomologyViewModelImpl
import com.pragma.entomologo.ui.views.splashView.viewModel.SplashViewModel
import com.pragma.entomologo.ui.views.splashView.viewModel.SplashViewModelImpl
import com.pragma.entomologo.ui.views.speciesRecordsView.viewModel.SpeciesRecordsViewModel
import com.pragma.entomologo.ui.views.speciesRecordsView.viewModel.SpeciesRecordsViewModelImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ViewModels {

    @Provides
    fun provideFormSpecieViewModel(
        addInsectUseCase: AddInsectUseCase,
        getAllInsectsUseCase: GetAllInsectsUseCase,
    ) : FormSpecieViewModel = FormSpecieViewModelImpl(
        addInsectUseCase = addInsectUseCase,
        getAllInsectsUseCase = getAllInsectsUseCase
    )

    @Provides
    fun providesRegisterEntomologyViewModel(
        registerEntomologistUseCase: RegisterEntomologistUseCase
    ): RegisterEntomologyViewModel = RegisterEntomologyViewModelImpl(
        registerEntomologistUseCase = registerEntomologistUseCase
    )

    @Provides
    fun provideSpeciesRecordsViewModel(
        getAllCountersUseCase: GetAllCountersUseCase,
        getImageProfileEntomologistUseCase: GetImageProfileEntomologistUseCase
    ) : SpeciesRecordsViewModel = SpeciesRecordsViewModelImpl(
        getAllCountersUseCase = getAllCountersUseCase,
        getImageProfileEntomologistUseCase = getImageProfileEntomologistUseCase
    )

    @Provides
    fun provideSplashViewModel(
        isEntomologistRegisteredUseCase: IsEntomologistRegisteredUseCase
    ) : SplashViewModel = SplashViewModelImpl(isEntomologistRegisteredUseCase = isEntomologistRegisteredUseCase)
}