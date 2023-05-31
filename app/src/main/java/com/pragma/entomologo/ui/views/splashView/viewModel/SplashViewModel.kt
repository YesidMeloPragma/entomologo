package com.pragma.entomologo.ui.views.splashView.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class SplashViewModel : ViewModel() {
    enum class StatusLoading {
        PRELOAD,
        LOADING,
        NAVIGATE_TO_LIST_RECORDS,
        NAVIGATE_TO_REGISTER_ENTOMOLOGIST,
        FINISH_LOAD
    }

    data class StateUISplash(
        val statusLoading: StatusLoading = StatusLoading.PRELOAD
    )

    abstract fun finishLoadData()
    abstract fun load()
    abstract fun loadingStatus(): StateFlow<StateUISplash>
}