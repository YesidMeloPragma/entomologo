package com.pragma.entomologo.ui.views.counterView

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.pragma.entomologo.ui.navigation.Routes
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.views.customs.buttons.CustomRoundedButtonsWithElevation

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun CounterViewPreview() {
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)) {
            val constraintsId = createRef()
            CounterInsectsView(modifier = Modifier.constrainAs(constraintsId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            })
        }
    }
}

@Composable
fun CounterInsectsView(
    modifier: Modifier,
    navHostController: NavHostController? = null
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
            text = "CounterView",
            onClick = {navHostController?.navigate(Routes.LIST_COUNTER_RECORDS.route)}
        )
    }
}