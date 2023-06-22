package com.pragma.entomologo.ui.views.customs.switchs

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.utils.TestTags


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun ButtonsPreview() {
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            val (customRoundedId) = createRefs()
            CustomSwitchRounded(modifier = Modifier.constrainAs(customRoundedId){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end, margin = 24.dp)
                start.linkTo(parent.start, margin = 24.dp)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            }, title = "compartenos tu ubicacion", legend = "Deja que entomology conozca tu ubicacion para gestionar tus registros", onCheckedChanged = {})
        }
    }
}

@Composable
fun CustomSwitchRounded(
    modifier: Modifier,
    title: String,
    legend: String,
    onCheckedChanged: (Boolean) -> Unit,
    checked:  Boolean = false
) {

    ConstraintLayout(modifier = modifier
        .background(MaterialTheme.colorScheme.background)) {
        val (legendId, switchId, titleId) = createRefs()

        Switch(
            modifier = Modifier
                .testTag(TestTags.CUSTOM_SWITCH_SWITCH.id)
                .constrainAs(switchId){
                top.linkTo(parent.top, margin = 12.dp)
                start.linkTo(parent.start, margin = 16.dp)
            },
            checked = checked,
            onCheckedChange = onCheckedChanged,
            colors = SwitchDefaults.colors(
                uncheckedBorderColor = MaterialTheme.colorScheme.outline,
                uncheckedThumbColor = MaterialTheme.colorScheme.outline,
                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )

        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier
                .testTag(TestTags.CUSTOM_SWITCH_TITLE.id)
                .constrainAs(titleId){
            top.linkTo(parent.top, margin = 12.dp)
            start.linkTo(switchId.end, margin = 16.dp)
            end.linkTo(parent.end, margin = 24.dp)
            width = Dimension.fillToConstraints
        })

        Text(
            text = legend,
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
            modifier = Modifier
                .testTag(TestTags.CUSTOM_SWITCH_MESSAGE.id)
                .constrainAs(legendId){
                bottom.linkTo(parent.bottom, margin = 12.dp)
                top.linkTo(titleId.bottom)
                start.linkTo(switchId.end, margin = 16.dp)
                end.linkTo(parent.end, margin = 24.dp)
                width = Dimension.fillToConstraints
        })

    }
}