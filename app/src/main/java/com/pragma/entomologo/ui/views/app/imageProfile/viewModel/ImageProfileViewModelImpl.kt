package com.pragma.entomologo.ui.views.app.imageProfile.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase.GetImageProfileEntomologistUseCase
import com.pragma.entomologo.ui.utils.extentions.getBitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageProfileViewModelImpl @Inject constructor(
    private val getImageProfileEntomologistUseCase: GetImageProfileEntomologistUseCase
) : ImageProfileViewModel() {
    private val dispatcher = Dispatchers.IO
    private val _uiState = MutableStateFlow(ImageProfileUIState())

    override fun loadImage() {
        viewModelScope.launch(dispatcher) {
            updateUIState(loadingState = StateUI.LOADING)
            getImageProfileEntomologistUseCase
                .invoke()
                .collect{
                    updateUIState(
                        loadingState = StateUI.LOADED,
                        bitmap = it?.getBitmap()
                    )
                }
        }
    }

    override fun setImageSelected(bitmap: Bitmap?) {
        viewModelScope.launch(dispatcher) {
            updateUIState(bitmap = bitmap)
        }
    }

    override fun getStateUI(): StateFlow<ImageProfileUIState> = _uiState

    private fun updateUIState(
        bitmap: Bitmap? = _uiState.value.bitmap,
        enableInteraction: Boolean = _uiState.value.enableInteraction,
        loadingState: StateUI = _uiState.value.loadingState,
    ) {
        viewModelScope.launch {
            _uiState.emit(
                ImageProfileUIState(
                    bitmap = bitmap,
                    enableInteraction = enableInteraction,
                    loadingState = loadingState,
                )
            )
        }
    }
}