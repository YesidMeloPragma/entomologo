package com.pragma.entomologo.ui.views.formSpecieView.viewModel

import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.logic.usesCase.getAllInsectsUseCase.GetAllInsectsUseCase
import com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase.GetImageProfileEntomologistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormSpecieViewModelImpl @Inject constructor(
    private val getAllInsectsUseCase: GetAllInsectsUseCase,
    private val getImageProfileEntomologistUseCase: GetImageProfileEntomologistUseCase
) : FormSpecieViewModel() {

    override fun loadView() {
        viewModelScope.launch(dispatcher) {
            updateUIState(loadingState = LoadingState.LOADING)
            getImageProfileEntomologistUseCase
                .invoke()
                .collect {
                    imageProfile ->

                    getAllInsectsUseCase
                        .invoke()
                        .collect {
                            updateUIState(
                                loadingState = LoadingState.LOADED,
                                imageProfile = imageProfile
                            )
                        }
                }
        }
    }

    override fun setNameInsect(nameInsect: String) {
        viewModelScope.launch(dispatcher) {
            updateUIState(nameInsect = nameInsect)
        }
    }

    override fun setMoreInformation(moreInformation: String) {
        viewModelScope.launch(dispatcher) {
            updateUIState(moreInformation = moreInformation)
        }
    }

    override fun setSelectInsect(insectSelected: InsectModel) {
        viewModelScope.launch(dispatcher) {
            updateUIState(insectSelected = insectSelected)
        }
    }

    override fun updateUIState(
        loadingState: LoadingState,
        imageProfile: String?,
        nameInsect: String,
        moreInformation: String,
        listInsect: List<InsectModel>,
        insectSelected: InsectModel?
    ) {
        viewModelScope.launch {
            _uiState.emit(
                FormSpecieUIState(
                    loadingState = loadingState,
                    imageProfile = imageProfile,
                    nameInsect = nameInsect,
                    moreInformation = moreInformation,
                    listInsect = listInsect,
                    insectSelected = insectSelected
                )
            )
        }
    }
}