package com.pragma.entomologo.ui.views.reports.viewModel

import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.ui.dispatchers.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportsViewModelImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
): ReportsViewModel() {

    override fun closeException() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateStateUI(
                loading = StatusLoading.LOADED,
                logicException = null
            )
        }
    }

    override fun loadResume() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateStateUI(loading = StatusLoading.LOADING)
            delay(5_000)
            updateStateUI(loading = StatusLoading.LOADED)
        }
    }
}