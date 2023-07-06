package com.pragma.entomologo.ui.dialogs.errorDialog.viewModel

import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.ui.dispatchers.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ErrorDialogViewModelImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : ErrorDialogViewModel() {

    override fun loadErrorDialog() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateUIState(loadingState = true)
            delay(3_000)
            updateUIState(loadingState = false)
        }
    }

    override fun sendData() {
        loadErrorDialog()
    }

}