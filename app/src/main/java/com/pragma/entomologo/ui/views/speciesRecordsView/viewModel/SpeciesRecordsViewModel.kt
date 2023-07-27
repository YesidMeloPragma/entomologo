package com.pragma.entomologo.ui.views.speciesRecordsView.viewModel

import androidx.lifecycle.ViewModel
import com.pragma.entomologo.logic.excepciones.LogicException
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import kotlinx.coroutines.flow.StateFlow

abstract class SpeciesRecordsViewModel : ViewModel() {
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
    abstract fun getStateUI() : StateFlow<StatusUISpeciesRecord>
    abstract fun loadListCounters()
    abstract fun requestPermissionsStorage()
}