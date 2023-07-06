package com.pragma.entomologo.ui.views.formSpecieView.viewModel

import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.excepciones.LogicException
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.logic.usesCase.addInsectUseCase.AddInsectUseCase
import com.pragma.entomologo.logic.usesCase.getAllInsectsUseCase.GetAllInsectsUseCase
import com.pragma.entomologo.ui.dispatchers.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormSpecieViewModelImpl @Inject constructor(
    private val addInsectUseCase: AddInsectUseCase,
    private val dispatcherProvider: DispatcherProvider,
    private val getAllInsectsUseCase: GetAllInsectsUseCase,
) : FormSpecieViewModel() {
    override fun closeError() {
        viewModelScope.launch(context = dispatcherProvider.io()) {
            updateUIState(logicException = null)
        }
    }

    override fun loadView() {
        viewModelScope.launch(context = dispatcherProvider.io()) {
            updateUIState(loadingState = LoadingState.LOADING)
            getAllInsectsUseCase
                .invoke()
                .collect{
                    updateUIState(
                        loadingState = LoadingState.LOADED,
                        listInsect = it,
                        insectSelected = null,
                        imageBase64 = "",
                        logicException = null,
                    )
                }
        }
    }

    override fun saveRecord(nameInsect: String, moreInformation: String) {
        viewModelScope.launch(context = dispatcherProvider.io()) {
            updateUIState(loadingState = LoadingState.LOADING)
            delay(1_000)
            val insectToSave = getInsectModel()
                .apply {
                    specieName = if (id == null) nameInsect else specieName
                    this.moreInformation = moreInformation
                }
            addInsectUseCase
                .invoke(
                    imageBase64 = _uiState.value.imageBase64,
                    insectModel = insectToSave
                ).catch {
                    updateUIState(
                        loadingState = LoadingState.LOADED,
                        logicException = it as LogicException
                    )
                }.collect {
                    insectToSave.apply { id = it }
                    delay(2_000)
                    updateUIState(
                        loadingState = LoadingState.NAVIGATE_TO_COUNTER_RECORD,
                        insectSelected = insectToSave
                    )
                }
        }
    }


    override fun setSelectInsect(insectSelected: InsectModel) {
        viewModelScope.launch(context = dispatcherProvider.io()) {
            updateUIState(
                insectSelected = insectSelected
            )
        }
    }

    override fun setImage(imageBase64: String) {
        viewModelScope.launch(context = dispatcherProvider.io()) {
            updateUIState(
                imageBase64 = imageBase64,
                insectSelected = null
            )
        }
    }

    private fun getInsectModel() : InsectModel {
        if(
            _uiState.value.insectSelected != null &&
            _uiState.value.insectSelected?.specieName?.lowercase()?.isEmpty() != true
        ) return _uiState.value.insectSelected!!

        return InsectModel(
            specieName = "",
            moreInformation = "",
            urlPhoto = ""
        )
    }
}