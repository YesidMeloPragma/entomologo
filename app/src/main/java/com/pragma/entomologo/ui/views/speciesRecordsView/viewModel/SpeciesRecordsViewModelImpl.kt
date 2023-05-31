package com.pragma.entomologo.ui.views.speciesRecordsView.viewModel

import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.logic.usesCase.getAllCountersUseCase.GetAllCountersUseCase
import com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase.GetImageProfileEntomologistUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SpeciesRecordsViewModelImpl @Inject constructor(
    private val getAllCountersUseCase: GetAllCountersUseCase,
    private val getImageProfileEntomologistUseCase: GetImageProfileEntomologistUseCase
) : SpeciesRecordsViewModel() {

    private val dispatcher = Dispatchers.IO
    private val stateUI = MutableStateFlow(value = StatusUISpeciesRecord())

    override fun getStateUI(): StateFlow<StatusUISpeciesRecord> = stateUI

    override fun loadListCounters() {
        viewModelScope.launch(dispatcher) {
            updateStateUI(loading = StatusLoading.LOADING)
            getAllCountersUseCase.invoke().collect{
                list->
                getImageProfileEntomologistUseCase.invoke().collect{
                    imageBase64 ->
                    updateStateUI(
                        loading = StatusLoading.LOADED,
                        imageBase64 = imageBase64,
                        list = list
                    )
                }
            }

        }
    }

    private fun updateStateUI(
        loading :  StatusLoading = stateUI.value.loading,
        list: List<CounterRecordInsectModel> = stateUI.value.list,
        imageBase64: String? = stateUI.value.imageBase64
    ) {
        viewModelScope.launch(dispatcher) {
            stateUI.emit(StatusUISpeciesRecord(
                loading = loading,
                list = list,
                imageBase64 = imageBase64
            ))
        }
    }
}