package com.pragma.entomologo.ui.views.reports

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pragma.entomologo.ui.activities.ActivityState
import com.pragma.entomologo.ui.navigation.Routes
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.views.customs.buttons.CustomRoundedButtonsWithElevation
import com.pragma.entomologo.ui.views.reports.viewModel.ReportsViewModel
import com.pragma.entomologo.ui.views.reports.viewModel.ReportsViewModelImpl

//region ui
@Composable
fun ReportsView(
    modifier: Modifier,
    stateActivity : MutableState<ActivityState>,
    viewModel: ReportsViewModel = hiltViewModel<ReportsViewModelImpl>()
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (buttonId) = createRefs()
        CustomRoundedButtonsWithElevation(
            modifier = Modifier.constrainAs(buttonId){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            },
            text = "Informes",
            onClick = {
            } )
    }
}

//endregion

//region preview
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
//@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun ReportsViewPreview() {
    EntomologoTheme {
        val stateActivity : MutableState<ActivityState> = remember { mutableStateOf(value = ActivityState.RESUME) }
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)) {
            val constraintsId = createRef()
            ReportsView(modifier = Modifier.constrainAs(constraintsId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            },
                stateActivity = stateActivity,
                viewModel = getViewModel()
            )
        }
    }
}

private fun getViewModel()
    = object : ReportsViewModel() {
    override fun closeException() {
        println("Cerrar excepcion")
    }

    override fun loadResume() {
        println("Cargar resumen")
    }

}
//endregion