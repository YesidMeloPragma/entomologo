package com.pragma.entomologo.ui.views.loadImageProfile.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class LoadImageProfileViewModel : ViewModel() {
    private val stateUI = MutableStateFlow(value = StateUI())
    fun getStateUI() : StateFlow<StateUI> = stateUI

    enum class StatusUI {
        START,
        LOADING,
        LOADED_IMAGE_PROFILE_FROM_APP,
        VERIFY_PERMISSION,
        REQUEST_PERMISSION_FIRST_STEP,
        HAS_PERMISSIONS_GALLERY,
        RESTART,
        ;
    }

    data class StateUI(
        val loading: StatusUI = StatusUI.START,
        val havePermissionGallery : Boolean = false,
        val bitmap: Bitmap? = null,
    )

    abstract fun checkPermissions()
    abstract fun loadView()
    abstract fun restartStateUI()
    abstract fun startStateUI()
    abstract fun updateBitmap(bitmap: Bitmap?)

    protected fun updateStatus(
        loading: StatusUI = stateUI.value.loading,
        havePermissionGallery : Boolean= stateUI.value.havePermissionGallery,
        imageBase64: Bitmap?= stateUI.value.bitmap,
    ) {
        viewModelScope.launch {
            stateUI.emit(StateUI(
                bitmap = imageBase64,
                loading = loading,
                havePermissionGallery = havePermissionGallery,
            ))
        }
    }

}