package com.pragma.entomologo.ui.views.formSpecieView.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.logic.usesCase.addInsectUseCase.AddInsectUseCase
import com.pragma.entomologo.logic.usesCase.getAllInsectsUseCase.GetAllInsectsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FormSpecieViewModelImpl @Inject constructor(
    private val addInsectUseCase: AddInsectUseCase,
    private val getAllInsectsUseCase: GetAllInsectsUseCase
) : FormSpecieViewModel() {

    private val textAutocomplete = MutableLiveData<String>()
    private val textMoreInformation = MutableLiveData<String>()
    private val textUrlPhoto = MutableLiveData<String>()
    private val listInsectsLivedata = MutableLiveData<List<InsectModel>>()
    private val loadingLiveData = MutableLiveData<Boolean>()
    private val goToSpeciesRecordLiveData = MutableLiveData<Pair<Boolean, InsectModel>>()

    private var currentInsect = InsectModel(specieName = "", moreInformation = "", urlPhoto = "")

    override fun goToSpeciesRegisterRecords(): LiveData<Pair<Boolean, InsectModel>> = goToSpeciesRecordLiveData

    override fun listInsects(): LiveData<List<InsectModel>> = listInsectsLivedata

    override fun loading(): LiveData<Boolean> = loadingLiveData

    override fun loadListInsects() {
        viewModelScope.launch {
            loadingLiveData.postValue(true)
            getAllInsectsUseCase
                .invoke()
                .collect{
                    listInsectsLivedata.postValue(it)
                    loadingLiveData.postValue(false)
                }
        }
    }

    override fun saveInsect() {
        viewModelScope.launch {
            loadingLiveData.postValue(true)
            if (currentInsect.id != null) {
                loadingLiveData.postValue(false)
                goToSpeciesRecordLiveData.postValue(Pair(first = true, second = currentInsect))
                return@launch
            }
            addInsectUseCase
                .invoke(insectModel = currentInsect).collect {
                    loadingLiveData.postValue(false)
                    goToSpeciesRecordLiveData.postValue(
                        Pair(first = true, second = currentInsect.apply { this.id = it })
                    )
                }
        }
    }

    override fun setInsectSelected(insectModel: InsectModel) {
        viewModelScope.launch {
            currentInsect = insectModel
            textAutocomplete.postValue(insectModel.specieName)
            textUrlPhoto.postValue(insectModel.urlPhoto)
            textMoreInformation.postValue(insectModel.moreInformation)
        }
    }

    override fun setTextAutocomplete(text: String) {
        viewModelScope.launch {
            currentInsect.id = null
            currentInsect.specieName = text
            textAutocomplete.postValue(text)
        }
    }

    override fun setTextMoreInformation(text: String) {
        viewModelScope.launch {
            currentInsect.id = null
            currentInsect.moreInformation = text
            textMoreInformation.postValue(text)
        }
    }

    override fun setTextUrlPhoto(text: String) {
        viewModelScope.launch {
            currentInsect.id = null
            currentInsect.urlPhoto = text
            textUrlPhoto.postValue(text)
        }
    }

    override fun textAutocomplete(): LiveData<String> = textAutocomplete

    override fun textMoreInformation(): LiveData<String> = textMoreInformation

    override fun textUrlPhoto(): LiveData<String> = textUrlPhoto

}