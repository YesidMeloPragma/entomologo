package com.pragma.entomologo.di.ui

import com.pragma.entomologo.logic.usesCase.addInsectUseCase.AddInsectUseCase
import com.pragma.entomologo.logic.usesCase.getAllCountersUseCase.GetAllCountersUseCase
import com.pragma.entomologo.logic.usesCase.getAllInsectsUseCase.GetAllInsectsUseCase
import com.pragma.entomologo.logic.usesCase.getImageInsectUseCase.GetImageInsectUseCase
import com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase.GetImageProfileEntomologistUseCase
import com.pragma.entomologo.logic.usesCase.getInsectWithImageByIdUseCase.GetInsectWithImageByIdUseCase
import com.pragma.entomologo.logic.usesCase.iHaveGPSPermissionUseCase.IHaveGPSPermissionUseCase
import com.pragma.entomologo.logic.usesCase.iHaveStoragePermissionUseCase.IHaveStoragePermissionUseCase
import com.pragma.entomologo.logic.usesCase.isEntomologistRegisteredUseCase.IsEntomologistRegisteredUseCase
import com.pragma.entomologo.logic.usesCase.registerEntomologistUseCase.RegisterEntomologistUseCase
import com.pragma.entomologo.logic.usesCase.registerRecordInsectUseCase.RegisterRecordInsectUseCase
import com.pragma.entomologo.ui.dialogs.errorDialog.viewModel.ErrorDialogViewModel
import com.pragma.entomologo.ui.dialogs.errorDialog.viewModel.ErrorDialogViewModelImpl
import com.pragma.entomologo.ui.dispatchers.DispatcherProvider
import com.pragma.entomologo.ui.views.app.imageProfile.viewModel.ImageProfileViewModel
import com.pragma.entomologo.ui.views.app.imageProfile.viewModel.ImageProfileViewModelImpl
import com.pragma.entomologo.ui.views.app.loadImageInsectFromGallery.viewModel.LoadImageInsectFromGalleryViewModel
import com.pragma.entomologo.ui.views.app.loadImageInsectFromGallery.viewModel.LoadImageInsectFromGalleryViewModelImpl
import com.pragma.entomologo.ui.views.counterInsects.viewModel.CounterInsectsViewModel
import com.pragma.entomologo.ui.views.counterInsects.viewModel.CounterInsectsViewModelImpl
import com.pragma.entomologo.ui.views.formSpecieView.viewModel.FormSpecieViewModel
import com.pragma.entomologo.ui.views.formSpecieView.viewModel.FormSpecieViewModelImpl
import com.pragma.entomologo.ui.views.loadImageProfile.viewModel.LoadImageProfileViewModel
import com.pragma.entomologo.ui.views.loadImageProfile.viewModel.LoadImageProfileViewModelImpl
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
    fun provideCounterInsectsViewModel(
        dispatcherProvider: DispatcherProvider,
        getInsectWithImageByIdUseCase: GetInsectWithImageByIdUseCase,
        registerRecordInsectUseCase: RegisterRecordInsectUseCase
    ) : CounterInsectsViewModel = CounterInsectsViewModelImpl(
        dispatcherProvider = dispatcherProvider,
        getInsectWithImageByIdUseCase = getInsectWithImageByIdUseCase,
        registerRecordInsectUseCase = registerRecordInsectUseCase
    )

    @Provides
    fun provideErrorDialogViewModel(
        dispatcherProvider: DispatcherProvider
    ): ErrorDialogViewModel = ErrorDialogViewModelImpl(
        dispatcherProvider = dispatcherProvider
    )

    @Provides
    fun provideFormSpecieViewModel(
        addInsectUseCase: AddInsectUseCase,
        dispatcherProvider: DispatcherProvider,
        getAllInsectsUseCase: GetAllInsectsUseCase,
    ) : FormSpecieViewModel = FormSpecieViewModelImpl(
        addInsectUseCase = addInsectUseCase,
        dispatcherProvider = dispatcherProvider,
        getAllInsectsUseCase = getAllInsectsUseCase,
    )

    @Provides
    fun provideImageProfileViewModel(
        getImageProfileEntomologistUseCase: GetImageProfileEntomologistUseCase
    ): ImageProfileViewModel = ImageProfileViewModelImpl(
        getImageProfileEntomologistUseCase = getImageProfileEntomologistUseCase
    )

    @Provides
    fun provideLoadImageInsectFromGalleryViewModel(
        dispatcherProvider: DispatcherProvider,
        getImageInsectUseCase: GetImageInsectUseCase
    ) : LoadImageInsectFromGalleryViewModel = LoadImageInsectFromGalleryViewModelImpl(
        dispatcherProvider = dispatcherProvider,
        getImageInsectUseCase = getImageInsectUseCase
    )

    @Provides
    fun provideLoadImageProfileViewModel(
        dispatcherProvider: DispatcherProvider,
        getImageProfileEntomologistUseCase: GetImageProfileEntomologistUseCase,
        iHaveStoragePermissionUseCase: IHaveStoragePermissionUseCase,
    ) : LoadImageProfileViewModel = LoadImageProfileViewModelImpl(
        dispatcherProvider = dispatcherProvider,
        getImageProfileEntomologistUseCase = getImageProfileEntomologistUseCase,
        iHaveStoragePermissionUseCase = iHaveStoragePermissionUseCase
    )

    @Provides
    fun provideRegisterEntomologyViewModel(
        dispatcherProvider: DispatcherProvider,
        iHaveGPSPermissionUseCase: IHaveGPSPermissionUseCase,
        registerEntomologistUseCase: RegisterEntomologistUseCase
    ): RegisterEntomologyViewModel = RegisterEntomologyViewModelImpl(
        dispatcherProvider = dispatcherProvider,
        iHaveGPSPermissionUseCase = iHaveGPSPermissionUseCase,
        registerEntomologistUseCase = registerEntomologistUseCase
    )

    @Provides
    fun provideSpeciesRecordsViewModel(
        dispatcherProvider: DispatcherProvider,
        getAllCountersUseCase: GetAllCountersUseCase
    ) : SpeciesRecordsViewModel = SpeciesRecordsViewModelImpl(
        dispatcherProvider = dispatcherProvider,
        getAllCountersUseCase = getAllCountersUseCase
    )

    @Provides
    fun provideSplashViewModel(
        isEntomologistRegisteredUseCase: IsEntomologistRegisteredUseCase
    ) : SplashViewModel = SplashViewModelImpl(isEntomologistRegisteredUseCase = isEntomologistRegisteredUseCase)
}