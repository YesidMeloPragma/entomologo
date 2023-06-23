package com.pragma.entomologo.ui.views.loadImageProfile.viewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoadImageProfileViewModelImpl @Inject constructor() : LoadImageProfileViewModel() {
    private val dispatcher = Dispatchers.IO
    private val stateUI = MutableStateFlow(value = StateUI())

    override fun getStateUI(): StateFlow<StateUI> = stateUI

    override fun loadView() {
        viewModelScope.launch(dispatcher) {
            updateStatus(loading = StatusUI.LOADING)
            delay(1_000)
            updateStatus(loading = StatusUI.LOADED)
        }
    }

    private fun updateStatus(
        loading: StatusUI = stateUI.value.loading
    ) {
        viewModelScope.launch {
            stateUI.emit(StateUI(
                loading = loading
            ))
        }
    }
}