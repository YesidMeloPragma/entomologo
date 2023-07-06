package com.pragma.entomologo.ui.views.app.loadImageInsectFromGallery.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class LoadImageInsectFromGalleryViewModel : ViewModel() {

    protected val _uiState = MutableStateFlow(LoadImageFromGalleryUIState())
    val uiState: StateFlow<LoadImageFromGalleryUIState> = _uiState.asStateFlow()

    enum class LoadingState {
        START,
        LOADING,
        LOADED,
    }

    data class LoadImageFromGalleryUIState(
        val imageBase64: String? = null,
        val insectModel: InsectModel? = null,
        val loadingState: LoadingState = LoadingState.START
    )

    abstract fun loadUI(insectModel: InsectModel?)

    abstract fun updatePhotoInsect(image: String?)

    protected fun updateUIState(
        insectModel: InsectModel? = _uiState.value.insectModel,
        imageBase64: String? = _uiState.value.imageBase64,
        loadingState: LoadingState = _uiState.value.loadingState
    ) {
        viewModelScope.launch {
            _uiState.emit(
                LoadImageFromGalleryUIState(
                    insectModel= insectModel,
                    imageBase64 = imageBase64,
                    loadingState = loadingState
                )
            )
        }
    }
}