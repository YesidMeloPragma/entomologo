package com.pragma.entomologo.ui.views.speciesRecordsView.viewModel

import androidx.lifecycle.ViewModel
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import kotlinx.coroutines.flow.StateFlow

abstract class SpeciesRecordsViewModel : ViewModel() {
    enum class StatusLoading {
        PRELOAD,
        LOADING,
        LOADED,
        ;
    }

    data class StatusUISpeciesRecord(
        val loading :  StatusLoading = StatusLoading.PRELOAD,
        val list: List<CounterRecordInsectModel> = emptyList(),
        val imageBase64: String? = null
    )

    abstract fun getStateUI() : StateFlow<StatusUISpeciesRecord>
    abstract fun loadListCounters()
}