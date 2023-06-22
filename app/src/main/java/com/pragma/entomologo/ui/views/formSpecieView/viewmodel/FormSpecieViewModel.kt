package com.pragma.entomologo.ui.views.formSpecieView.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pragma.entomologo.logic.models.InsectModel

abstract class FormSpecieViewModel : ViewModel() {
    abstract fun goToSpeciesRegisterRecords() : LiveData<Pair<Boolean, InsectModel>>
    abstract fun listInsects() : LiveData<List<InsectModel>>
    abstract fun loading(): LiveData<Boolean>
    abstract fun loadListInsects()
    abstract fun saveInsect()
    abstract fun setInsectSelected(insectModel: InsectModel)
    abstract fun setTextAutocomplete(text: String)
    abstract fun setTextMoreInformation(text: String)
    abstract fun setTextUrlPhoto(text: String)
    abstract fun textAutocomplete() : LiveData<String>
    abstract fun textMoreInformation() : LiveData<String>
    abstract fun textUrlPhoto() : LiveData<String>
}
