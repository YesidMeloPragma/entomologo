package com.pragma.entomologo.ui.views.registerEntomologistView.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.models.EntomologistModel
import com.pragma.entomologo.logic.usesCase.iHaveGPSPermissionUseCase.IHaveGPSPermissionUseCase
import com.pragma.entomologo.logic.usesCase.registerEntomologistUseCase.RegisterEntomologistUseCase
import com.pragma.entomologo.ui.dispatchers.DispatcherProvider
import com.pragma.entomologo.ui.utils.extentions.convertToStringBase64
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterEntomologyViewModelImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val iHaveGPSPermissionUseCase: IHaveGPSPermissionUseCase,
    private val registerEntomologistUseCase: RegisterEntomologistUseCase,
) : RegisterEntomologyViewModel() {

    private val _uiState = MutableStateFlow(value = StateUI())

    override fun stateUI(): StateFlow<StateUI> = _uiState
    override fun checkPermissionsGPS() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateState(loading = true)
            iHaveGPSPermissionUseCase
                .invoke()
                .collect{
                    updateState(loading = false, statusSwitch = it)
                }
        }
    }

    override fun saveEntomologist() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateState(
                loading = true,
                enableInteraction = false
            )
            val imageString = _uiState.value.bitmap?.convertToStringBase64()?:""
            val successSave = registerEntomologistUseCase.invoke(
                bitmapBase64 = imageString,
                entomologistModel = EntomologistModel(
                    name = _uiState.value.name,
                    urlPhoto = ""
                )
            )
            updateState(
                navigateToListRecords = successSave,
                loading = false,
                enableInteraction = !successSave
            )
        }
    }

    override fun setCurrentImageProfile(image: Bitmap?) {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateState(bitmap = image)
        }
    }

    override fun setCurrentNameEntomologist(name: String) {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateState(name = name)
        }
    }

    override fun setCurrentStatusSwitch(statusSwitch: Boolean) {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateState(statusSwitch = statusSwitch)
        }
    }

    private fun updateState(
        bitmap: Bitmap? = _uiState.value.bitmap,
        enableInteraction: Boolean = _uiState.value.enableInteraction,
        loading: Boolean = _uiState.value.loading,
        name: String = _uiState.value.name,
        navigateToListRecords: Boolean = _uiState.value.navigateToListRecords,
        statusSwitch: Boolean = _uiState.value.statusSwitch,
    ) {
        viewModelScope.launch {
            _uiState.emit(StateUI(
                bitmap = bitmap,
                enableInteraction = enableInteraction,
                loading = loading,
                name = name,
                navigateToListRecords = navigateToListRecords,
                statusSwitch = statusSwitch,
            ))
        }
    }
}