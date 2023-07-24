package com.pragma.entomologo.ui.views.counterInsects.viewModel

import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.excepciones.LogicException
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.logic.models.GeoLocationModel
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.logic.usesCase.getInsectWithImageByIdUseCase.GetInsectWithImageByIdUseCase
import com.pragma.entomologo.logic.usesCase.registerRecordInsectUseCase.RegisterRecordInsectUseCase
import com.pragma.entomologo.ui.dispatchers.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterInsectsViewModelImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getInsectWithImageByIdUseCase: GetInsectWithImageByIdUseCase,
    private val registerRecordInsectUseCase: RegisterRecordInsectUseCase
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

    override fun saveRecord(comment: String) {
        viewModelScope.launch(dispatcherProvider.io()) {
            updateState(statesCounterInsectsUI = StatesCounterInsectsUI.LOADING)
            val counter = CounterRecordInsectModel(
                insect = _uiState.value.insectModel!!,
                geoLocation = GeoLocationModel(lat = 1.0, lng = 1.0, city = ""),
                comment = comment,
                count = _uiState.value.counter
            )
            registerRecordInsectUseCase
                .invoke(counterRecordInsectModel = counter)
                .catch {
                    val exception = if(it is LogicException) it else LogicException()
                    updateState(
                        statesCounterInsectsUI = StatesCounterInsectsUI.SHOW_ERROR,
                        throwable = exception
                    )
                }
                .collect {
                    updateState(
                        statesCounterInsectsUI = StatesCounterInsectsUI.NAVIGATE_TO_LIST
                    )
                }
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
            updateState(
                throwable = null,
                statesCounterInsectsUI = StatesCounterInsectsUI.LOADED
            )
        }
    }
}