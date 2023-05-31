package com.pragma.entomologo.ui.activities

import com.pragma.entomologo.ui.views.formSpecieView.viewmodel.FormSpecieViewModel
import com.pragma.entomologo.ui.views.registerEntomologistView.viewModel.RegisterEntomologyViewModel
import com.pragma.entomologo.ui.views.speciesRecordsView.viewModel.SpeciesRecordsViewModel
import com.pragma.entomologo.ui.views.splashView.viewModel.SplashViewModel
import javax.inject.Inject

class ViewModelHandler @Inject constructor(
    val formSpecieViewModel: FormSpecieViewModel,
    val registerEntomologyViewModel: RegisterEntomologyViewModel,
    val speciesRecordsViewModel: SpeciesRecordsViewModel,
    val splashViewModel: SplashViewModel
)