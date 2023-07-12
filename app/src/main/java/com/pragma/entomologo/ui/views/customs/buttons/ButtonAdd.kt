package com.pragma.entomologo.ui.views.customs.buttons

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pragma.entomologo.R
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.utils.TestTags

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun ButtonAddPreview() {
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
        ) {
            val (customRoundedId) = createRefs()
            ButtonAdd(modifier = Modifier.constrainAs(customRoundedId){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }, onClick = {})
        }
    }
}

@Composable
fun ButtonAdd(
    modifier: Modifier,
    onClick: () -> Unit
) {

    ConstraintLayout(
        modifier = modifier
            .aspectRatio(ratio = 1f)
            .clickable { onClick.invoke() }
    ) {
        val imageId = createRef()
        Image(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "",
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .padding(15.dp)
                .testTag(TestTags.IMAGE_BUTTON_ADD.id)
                .constrainAs(imageId){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSecondaryContainer),
            contentScale = ContentScale.FillBounds,
        )
    }

}