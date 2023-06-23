package com.pragma.entomologo.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pragma.entomologo.ui.activities.ViewModelHandler
import com.pragma.entomologo.ui.views.counterView.CounterInsectsView
import com.pragma.entomologo.ui.views.formSpecieView.FormSpecieView
import com.pragma.entomologo.ui.views.loadImageProfile.LoadImageProfileView
import com.pragma.entomologo.ui.views.registerEntomologistView.RegisterEntomologistView
import com.pragma.entomologo.ui.views.reports.ReportsView
import com.pragma.entomologo.ui.views.speciesRecordsView.SpeciesRecordsView
import com.pragma.entomologo.ui.views.splashView.SplashView

@Composable
fun NavigationHandler(
    viewModelHandler: ViewModelHandler
) {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = Routes.SPLASH.route,
    ) {
        composable(route = Routes.COUNTER_INSECTS.route) {
            CounterInsectsView(
                modifier = Modifier.fillMaxSize(),
                navHostController = navHostController
            )
        }

        composable(route = Routes.LIST_COUNTER_RECORDS.route) {
            SpeciesRecordsView(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModelHandler.speciesRecordsViewModel,
                imageProfileViewModel = viewModelHandler.imageProfileViewModel,
                navigateToRegisterNewInsect = {
                    navHostController.popBackStack(route = Routes.LIST_COUNTER_RECORDS.route, inclusive = false)
                    navHostController.navigate(route = Routes.REGISTER_NEW_INSECT.route)
                },
                navigateToImageProfile = {
                    navHostController.popBackStack(route = Routes.LIST_COUNTER_RECORDS.route, inclusive = false)
                    navHostController.navigate(route = Routes.LOAD_IMAGE_PROFILE.route)
                }
            )
        }

        composable(route = Routes.LOAD_IMAGE_PROFILE.route) {
            LoadImageProfileView(
                modifier = Modifier.fillMaxSize(),
                navigateToProfile = {
                    navHostController.popBackStack(route = Routes.REGISTER_NEW_PROFILE.route, inclusive = false)
                    viewModelHandler.registerEntomologyViewModel.setCurrentImageProfile(image = it)
                },
                navigateToListRecord = {
                    navHostController.popBackStack(route = Routes.LIST_COUNTER_RECORDS.route, inclusive = false)
                    viewModelHandler.imageProfileViewModel.setImageSelected(bitmap = it)
                }
            )
        }

        composable(route = Routes.REGISTER_NEW_INSECT.route) {
            FormSpecieView(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModelHandler.formSpecieViewModel,
                navHostController = navHostController
            )
        }

        composable(route = Routes.REGISTER_NEW_PROFILE.route) {
            RegisterEntomologistView(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModelHandler.registerEntomologyViewModel,
                navigateToCatureImage = {
                    navHostController.navigate(Routes.LOAD_IMAGE_PROFILE.route)
                },
                navigateToListRecords = {
                    navHostController.popBackStack(route = Routes.REGISTER_NEW_PROFILE.route, inclusive = true)
                    navHostController.navigate(Routes.LIST_COUNTER_RECORDS.route)
                },
            )
        }

        composable(route = Routes.REPORTS.route) {
            ReportsView(
                modifier = Modifier.fillMaxSize(),
                navHostController = navHostController
            )
        }

        composable(route = Routes.SPLASH.route) {
            SplashView(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModelHandler.splashViewModel,
                navigateToListRecords = {
                    navHostController.popBackStack(route = Routes.SPLASH.route, inclusive = true)
                    navHostController.navigate(Routes.LIST_COUNTER_RECORDS.route)
                },
                navigateToRegisterEntomologist = {
                    navHostController.popBackStack(route = Routes.SPLASH.route, inclusive = true)
                    navHostController.navigate(Routes.REGISTER_NEW_PROFILE.route)
                }
            )
        }
    }
}

