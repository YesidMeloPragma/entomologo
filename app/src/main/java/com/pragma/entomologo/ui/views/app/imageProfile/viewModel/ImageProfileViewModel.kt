package com.pragma.entomologo.ui.views.app.imageProfile.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class ImageProfileViewModel : ViewModel() {

    enum class StateUI {
        START,
        LOADING,
        LOADED,
    }

    data class ImageProfileUIState(
        val bitmap: Bitmap? = null,
        val enableInteraction: Boolean = true,
        val loadingState: StateUI = StateUI.START,
    )

    abstract fun loadImage()

    abstract fun setImageSelected(bitmap: Bitmap?)

    abstract fun getStateUI() : StateFlow<ImageProfileUIState>
}