package com.pragma.entomologo.ui.views.loadImageProfile.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class LoadImageProfileViewModel : ViewModel() {

    abstract fun getStateUI() : StateFlow<StateUI>

    enum class StatusUI {
        START,
        LOADING,
        LOADED,
        ;
    }

    data class StateUI(
        val loading: StatusUI = StatusUI.START
    )

    abstract fun loadView()
}