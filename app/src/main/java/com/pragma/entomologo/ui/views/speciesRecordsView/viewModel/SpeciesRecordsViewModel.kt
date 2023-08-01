package com.pragma.entomologo.ui.views.speciesRecordsView.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.excepciones.LogicException
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class SpeciesRecordsViewModel : ViewModel() {

    private val _stateUI = MutableStateFlow(value = StatusUISpeciesRecord())

    enum class StatusLoading {
        PRELOAD,
        LOADING,
        LOADED,
        REQUEST_PERMISSIONS_STORAGE,
        SHOW_ERROR,
        ;
    }

    data class StatusUISpeciesRecord(
        val loading :  StatusLoading = StatusLoading.PRELOAD,
        val list: List<CounterRecordInsectModel> = emptyList(),
        val logicException: LogicException? = null
    )

    abstract fun changePreloadStatus()
    abstract fun loadListCounters()
    abstract fun requestPermissionsStorage()

    fun getStateUI() : StateFlow<StatusUISpeciesRecord> = _stateUI

    fun updateStateUI(
        loading :  StatusLoading = _stateUI.value.loading,
        list: List<CounterRecordInsectModel> = _stateUI.value.list,
        logicException: LogicException? = _stateUI.value.logicException,
    ) {
        viewModelScope.launch {
            _stateUI.emit(
                StatusUISpeciesRecord(
                    loading = loading,
                    list= list,
                    logicException = logicException
                )
            )
        }
    }
}