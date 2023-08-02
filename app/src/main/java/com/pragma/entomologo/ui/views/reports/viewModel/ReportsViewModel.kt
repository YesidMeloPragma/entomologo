package com.pragma.entomologo.ui.views.reports.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.excepciones.LogicException
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class ReportsViewModel : ViewModel() {
    private val _stateUI = MutableStateFlow(value = StatusUIReports())

    enum class StatusLoading {
        PRELOAD,
        LOADING,
        LOADED,
        SHOW_ERROR,
        ;
    }

    data class StatusUIReports(
        val loading :  StatusLoading = StatusLoading.PRELOAD,
        val list: List<CounterRecordInsectModel> = emptyList(),
        val logicException: LogicException? = null
    )

    abstract fun closeException()

    abstract fun loadResume()

    fun getStateUI() : StateFlow<ReportsViewModel.StatusUIReports> = _stateUI

    fun updateStateUI(
        loading : StatusLoading = _stateUI.value.loading,
        list: List<CounterRecordInsectModel> = _stateUI.value.list,
        logicException: LogicException? = _stateUI.value.logicException,
    ) {
        viewModelScope.launch {
            _stateUI.emit(
                StatusUIReports(
                    loading = loading,
                    list = list,
                    logicException = logicException
                )
            )
        }
    }
}