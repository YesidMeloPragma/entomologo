package com.pragma.entomologo.ui.views.counterInsects.viewModel

import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.logic.usesCase.getInsectWithImageByIdUseCase.GetInsectWithImageByIdUseCase
import com.pragma.entomologo.ui.dispatchers.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterInsectsViewModelImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getInsectWithImageByIdUseCase: GetInsectWithImageByIdUseCase
) : CounterInsectsViewModel() {

    override fun loadCounterInsects(counterInsect: Int, insectId: Int) {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateState(statesCounterInsectsUI = StatesCounterInsectsUI.LOADING)
            getInsectWithImageByIdUseCase
                .invoke(insectId = insectId.toLong())
                .collect {
                    updateState(
                        statesCounterInsectsUI = StatesCounterInsectsUI.LOADED,
                        insectModel = it.first,
                        imageBase64 = it.second
                    )
                }

        }
    }

    override fun removeInsectFromCounter() {
        viewModelScope.launch(dispatcherProvider.io()) {
            val currentCounter = if(_uiState.value.counter <= 0) 0 else _uiState.value.counter - 1
            updateState(counter = currentCounter)
        }
    }

    override fun saveRecord() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateState(statesCounterInsectsUI = StatesCounterInsectsUI.LOADING)
            delay(3_000)
            updateState(statesCounterInsectsUI = StatesCounterInsectsUI.NAVIGATE_TO_LIST)
        }
    }

    override fun setInsectModel(insectModel: InsectModel) {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateState(insectModel = insectModel)
        }
    }

    override fun updateState(
        counter: Int,
        imageBase64: String?,
        insectModel: InsectModel?,
        statesCounterInsectsUI: StatesCounterInsectsUI,
        throwable: Throwable?
    ) {
        viewModelScope.launch(dispatcherProvider.io()) {
            _uiState.emit(
                CounterInsectsUIState(
                    counter = counter,
                    imageBase64 = imageBase64,
                    insectModel = insectModel,
                    statesCounterInsectsUI = statesCounterInsectsUI,
                    throwable = throwable,
                )
            )
        }
    }

    override fun addInsectToCounter() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateState(counter = _uiState.value.counter + 1)
        }
    }

    override fun closeException() {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateState(throwable = null)
        }
    }
}