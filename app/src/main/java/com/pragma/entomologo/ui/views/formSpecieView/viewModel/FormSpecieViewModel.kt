package com.pragma.entomologo.ui.views.formSpecieView.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.excepciones.LogicException
import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class FormSpecieViewModel : ViewModel() {
    protected val _uiState = MutableStateFlow(FormSpecieUIState())
    val uiState: StateFlow<FormSpecieUIState> = _uiState.asStateFlow()

    enum class LoadingState {
        START,
        LOADING,
        LOADED,
        NAVIGATE_TO_COUNTER_RECORD,
        RESTART,
        ;
    }

    data class FormSpecieUIState(
        val imageBase64: String = "",
        val insectSelected: InsectModel? = null,
        val loadingState: LoadingState = LoadingState.START,
        val logicException: LogicException? = null,
        val listInsect: List<InsectModel> = emptyList(),
    )

    abstract fun closeError()
    abstract fun loadView()
    abstract fun saveRecord(nameInsect: String, moreInformation: String)
    abstract fun setSelectInsect(insectSelected: InsectModel)
    abstract fun setImage(imageBase64: String)

    protected fun updateUIState(
        imageBase64: String = _uiState.value.imageBase64,
        insectSelected: InsectModel? = _uiState.value.insectSelected,
        loadingState: LoadingState = _uiState.value.loadingState,
        listInsect: List<InsectModel> = _uiState.value.listInsect,
        logicException: LogicException? = _uiState.value.logicException,
    ) {
        viewModelScope.launch {
            _uiState.emit(
                FormSpecieUIState(
                    loadingState = loadingState,
                    listInsect = listInsect,
                    insectSelected = insectSelected,
                    imageBase64 = imageBase64,
                    logicException = logicException
                )
            )
        }
    }
}