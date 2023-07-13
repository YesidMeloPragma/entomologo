package com.pragma.entomologo.ui.views.counterInsects.viewModel

import androidx.lifecycle.ViewModel
import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class CounterInsectsViewModel : ViewModel() {

    protected val _uiState = MutableStateFlow(CounterInsectsUIState())
    val uiState: StateFlow<CounterInsectsUIState> = _uiState.asStateFlow()

    enum class StatesCounterInsectsUI {
        START,
        LOADING,
        LOADED,
        NAVIGATE_TO_LIST,
        SHOW_ERROR,
        ;
    }

    data class CounterInsectsUIState(
        val counter: Int = 0,
        val imageBase64: String? = null,
        val insectModel: InsectModel? = null,
        val statesCounterInsectsUI: StatesCounterInsectsUI = StatesCounterInsectsUI.START,
        val throwable: Throwable? = null
    )

    abstract fun addInsectToCounter()

    abstract fun closeException()

    abstract fun loadCounterInsects(counterInsect: Int, insectId: Int)

    abstract fun removeInsectFromCounter()

    abstract fun saveRecord(comment: String)

    abstract fun updateState(
        counter: Int = _uiState.value.counter,
        imageBase64: String? = _uiState.value.imageBase64,
        insectModel: InsectModel? = _uiState.value.insectModel,
        statesCounterInsectsUI: StatesCounterInsectsUI = _uiState.value.statesCounterInsectsUI,
        throwable: Throwable? = _uiState.value.throwable,
    )
}