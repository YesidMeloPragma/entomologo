package com.pragma.entomologo.ui.views.registerEntomologistView.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class RegisterEntomologyViewModel : ViewModel() {

    data class StateUI(
        val bitmap: Bitmap? = null,
        val loading: Boolean = false,
        val name: String = "",
        val navigateToListRecords : Boolean = false,
        val statusSwitch: Boolean = false,
        val enableInteraction : Boolean = true
    )

    abstract fun stateUI() : StateFlow<StateUI>
    abstract fun checkPermissionsGPS()
    abstract fun saveEntomologist()
    abstract fun setCurrentImageProfile(image: Bitmap?)
    abstract fun setCurrentNameEntomologist(name: String)
    abstract fun setCurrentStatusSwitch(statusSwitch: Boolean)

}