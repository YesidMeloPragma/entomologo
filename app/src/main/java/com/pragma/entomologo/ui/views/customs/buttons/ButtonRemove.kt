package com.pragma.entomologo.ui.views.customs.buttons

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pragma.entomologo.R
import com.pragma.entomologo.ui.theme.EntomologoTheme

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun ButtonRemovePreview() {
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
        ) {
            val (customRoundedId) = createRefs()
            ButtonRemove(modifier = Modifier.constrainAs(customRoundedId){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }, onClick = {})
        }
    }
}

@Composable
fun ButtonRemove(
    modifier: Modifier,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(size = 16.dp)
    Image(
        painter = painterResource(id = R.drawable.minus),
        contentDescription = "",
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        modifier = modifier
            .clickable { onClick.invoke() }
            .shadow(
                elevation = 4.dp,
                ambientColor = Color.Red,
                shape = shape
            )
            .background(
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = shape
            )
    )
}