package com.pragma.entomologo.ui.dialogs.errorDialog.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class ErrorDialogViewModel: ViewModel() {
    protected val _uiState = MutableStateFlow(ErrorDialogUIState())
    val uiState: StateFlow<ErrorDialogUIState> = _uiState.asStateFlow()

    data class ErrorDialogUIState(val loadingState: Boolean = false)

    abstract fun loadErrorDialog()

    abstract fun sendData()

    protected fun updateUIState(
        loadingState: Boolean = _uiState.value.loadingState
    ) {
        viewModelScope.launch {
            _uiState.emit(
                ErrorDialogUIState(
                    loadingState = loadingState
                )
            )
        }
    }
}