package com.pragma.entomologo.ui.views.app.loadImageInsectFromGallery.viewModel

import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.logic.usesCase.getImageInsectUseCase.GetImageInsectUseCase
import com.pragma.entomologo.ui.dispatchers.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadImageInsectFromGalleryViewModelImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getImageInsectUseCase: GetImageInsectUseCase
) : LoadImageInsectFromGalleryViewModel() {

    override fun loadUI(insectModel: InsectModel?) {
        viewModelScope.launch(context = dispatcherProvider.io()) {
            updateUIState(loadingState = LoadingState.LOADING)
            if (insectModel == null) {
                updateUIState(
                    loadingState = LoadingState.LOADED,
                    imageBase64 = null,
                    insectModel = null
                )
                return@launch
            }

            getImageInsectUseCase
                .invoke(insectModel = insectModel)
                .collect{
                    updateUIState(
                        insectModel = insectModel,
                        imageBase64 = it,
                        loadingState = LoadingState.LOADED
                    )
                }
        }
    }

    override fun updatePhotoInsect(image: String?) {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateUIState(imageBase64 = image, insectModel = null)
        }
    }
}