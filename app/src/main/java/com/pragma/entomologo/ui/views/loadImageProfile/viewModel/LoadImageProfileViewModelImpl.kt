package com.pragma.entomologo.ui.views.loadImageProfile.viewModel

import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase.GetImageProfileEntomologistUseCase
import com.pragma.entomologo.ui.dispatchers.DispatcherProvider
import com.pragma.entomologo.ui.utils.extentions.getBitmap
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoadImageProfileViewModelImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getImageProfileEntomologistUseCase: GetImageProfileEntomologistUseCase
) : LoadImageProfileViewModel() {

    override fun checkPermissions() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateStatus(loading = StatusUI.VERIFY_PERMISSION)
        }
    }

    override fun userHasPermissions() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateStatus(loading = StatusUI.HAS_PERMISSIONS_GALLERY)
        }
    }

    override fun loadView() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateStatus(loading = StatusUI.LOADING)
            getImageProfileEntomologistUseCase
                .invoke()
                .collect {
                    delay(3_000)
                    updateStatus(
                        loading = StatusUI.LOADED_IMAGE_PROFILE_FROM_APP,
                        imageBase64 = it?.getBitmap()
                    )
                }
        }
    }

    override fun requestPermissionsFirstStep() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateStatus(loading = StatusUI.REQUEST_PERMISSION_FIRST_STEP)
        }
    }

    override fun restartStateUI() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateStatus(loading = StatusUI.RESTART)
        }
    }

    override fun startStateUI() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateStatus(loading = StatusUI.START)
        }
    }

}