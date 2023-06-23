package com.pragma.entomologo.ui.views.formSpecieView.viewModel

import androidx.lifecycle.ViewModel
import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class FormSpecieViewModel : ViewModel() {
    protected val dispatcher = Dispatchers.IO
    protected val _uiState = MutableStateFlow(FormSpecieUIState())
    val uiState: StateFlow<FormSpecieUIState> = _uiState.asStateFlow()

    enum class LoadingState {
        START,
        LOADING,
        LOADED,
        ;
    }

    data class FormSpecieUIState(
        val imageProfile: String? = null,
        val loadingState: LoadingState = LoadingState.START,
        val moreInformation: String = "",
        val nameInsect: String = "",
        val listInsect: List<InsectModel> = emptyList(),
        val insectSelected: InsectModel? = null
    )

    abstract fun loadView()

    abstract fun setNameInsect(nameInsect: String)

    abstract fun setMoreInformation(moreInformation: String)

    abstract fun setSelectInsect(insectSelected: InsectModel)

    abstract fun updateUIState(
        loadingState: LoadingState = _uiState.value.loadingState,
        imageProfile: String? = _uiState.value.imageProfile,
        nameInsect: String = _uiState.value.nameInsect,
        moreInformation: String = _uiState.value.moreInformation,
        listInsect: List<InsectModel> = _uiState.value.listInsect,
        insectSelected: InsectModel? = _uiState.value.insectSelected
    )
}