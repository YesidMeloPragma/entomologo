package com.pragma.entomologo.ui.views.customs.buttons

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pragma.entomologo.ui.theme.EntomologoTheme


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun CustomRoundedButtonsWithElevationPreview() {
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            val (customRoundedId) = createRefs()
            CustomRoundedButtonsWithElevation(modifier = Modifier.constrainAs(customRoundedId){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }, text = "Ingreso por primera vez", onClick = {})
        }
    }
}

@Composable
fun CustomRoundedButtonsWithElevation(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    colorText: Color = MaterialTheme.colorScheme.primary,
    colorBackground: Color = MaterialTheme.colorScheme.background,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 3.dp,
            pressedElevation = 1.dp,
            disabledElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorBackground,
            contentColor = colorText
        )
    ) {
        Text(text= text, style = MaterialTheme.typography.labelLarge.copy(
            fontWeight = FontWeight.W500
        ))
    }
}