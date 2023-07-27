package com.pragma.entomologo.ui.views.speciesRecordsView.viewModel

import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.excepciones.LogicException
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.logic.usesCase.getAllCountersUseCase.GetAllCountersUseCase
import com.pragma.entomologo.ui.dispatchers.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class SpeciesRecordsViewModelImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getAllCountersUseCase: GetAllCountersUseCase
) : SpeciesRecordsViewModel() {

    private val stateUI = MutableStateFlow(value = StatusUISpeciesRecord())
    override fun requestPermissionsStorage() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateStateUI(
                loading = StatusLoading.REQUEST_PERMISSIONS_STORAGE,
                logicException = null
            )
        }
    }

    override fun changePreloadStatus() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateStateUI(
                loading = StatusLoading.PRELOAD,
                logicException = null
            )
        }
    }

    override fun getStateUI(): StateFlow<StatusUISpeciesRecord> = stateUI

    override fun loadListCounters() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateStateUI(loading = StatusLoading.LOADING)
            getAllCountersUseCase
                .invoke()
                .catch {
                    updateStateUI(
                        loading = StatusLoading.SHOW_ERROR,
                        logicException = if(it !is LogicException) LogicException()  else it
                    )
                }
                .collect{
                list->
                updateStateUI(
                    loading = StatusLoading.LOADED,
                    list = list
                )
            }

        }
    }

    private fun updateStateUI(
        loading :  StatusLoading = stateUI.value.loading,
        list: List<CounterRecordInsectModel> = stateUI.value.list,
        logicException: LogicException? = stateUI.value.logicException
    ) {
        viewModelScope.launch(dispatcherProvider.io()) {
            stateUI.emit(StatusUISpeciesRecord(
                loading = loading,
                list = list,
                logicException = logicException
            ))
        }
    }
}