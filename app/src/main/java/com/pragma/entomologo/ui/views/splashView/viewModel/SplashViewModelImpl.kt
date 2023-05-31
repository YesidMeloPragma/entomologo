package com.pragma.entomologo.ui.views.splashView.viewModel

import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.usesCase.isEntomologistRegisteredUseCase.IsEntomologistRegisteredUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModelImpl @Inject constructor(
    private val isEntomologistRegisteredUseCase: IsEntomologistRegisteredUseCase
) : SplashViewModel() {

    private val dispatcher = Dispatchers.IO
    private val _uiStatus = MutableStateFlow(value = StateUISplash())

    override fun finishLoadData() {
        viewModelScope.launch(dispatcher) {
            updateUIStatus(statusLoading = StatusLoading.FINISH_LOAD)
        }
    }

    override fun load() {
        viewModelScope.launch(dispatcher) {
            updateUIStatus(statusLoading = StatusLoading.LOADING)
            isEntomologistRegisteredUseCase
                .invoke()
                .collect {
                    when(it) {
                        true -> updateUIStatus(statusLoading = StatusLoading.NAVIGATE_TO_LIST_RECORDS)
                        false -> updateUIStatus(statusLoading = StatusLoading.NAVIGATE_TO_REGISTER_ENTOMOLOGIST)
                    }
                }
        }
    }

    override fun loadingStatus(): StateFlow<StateUISplash> = _uiStatus

    private fun updateUIStatus(
        statusLoading: StatusLoading = StatusLoading.PRELOAD
    ) {
        viewModelScope.launch {
            _uiStatus.emit(StateUISplash(statusLoading = statusLoading))
        }
    }
}