package com.pragma.entomologo.ui.views.app

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pragma.entomologo.R
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.views.customs.buttons.ButtonAdd
import com.pragma.entomologo.ui.views.customs.texts.CustomText


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun NewCounterViewPreview() {
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.secondaryContainer)) {
            val constraintsId = createRef()
            NewCounterView(
                modifier = Modifier.constrainAs(constraintsId) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }.aspectRatio(1f/0.2f),
                onClick = {

                }
            )
        }
    }
}

@Composable
fun NewCounterView(modifier: Modifier, onClick : ()->Unit) {
    ConstraintLayout(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surface)) {

        val (dotId, textId, addId) = createRefs()

        //region dotImage
        val guidelineDotImageBottom = createGuidelineFromBottom(fraction = 0.42f)
        val guidelineDotImageEnd = createGuidelineFromEnd(fraction = 0.91f)
        val guidelineDotImageStart = createGuidelineFromStart(fraction = 0.06f)
        val guidelineDotImageTop = createGuidelineFromTop(fraction = 0.42f)
        Image(
            painter = painterResource(id = R.drawable.dot),
            contentDescription ="",
            modifier = Modifier.constrainAs(dotId){
                bottom.linkTo(guidelineDotImageBottom)
                end.linkTo(guidelineDotImageEnd)
                start.linkTo(guidelineDotImageStart)
                top.linkTo(guidelineDotImageTop)

                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSecondaryContainer)
        )
        //endregion

        //region text
        val guidelineTextEnd = createGuidelineFromEnd(fraction = 0.27f)
        val guidelineTextStart = createGuidelineFromStart(fraction = 0.17f)

        CustomText(
            modifier = Modifier.constrainAs(textId){
                bottom.linkTo(parent.bottom)
                end.linkTo(guidelineTextEnd)
                start.linkTo(guidelineTextStart)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            },
            text = "Nuevo conteo"
        )

        //endregion

        //region button add
        val guidelineButtonAddBottom = createGuidelineFromBottom(fraction = 0.15f)
        val guidelineButtonAddEnd = createGuidelineFromEnd(fraction = 0.04f)
        val guidelineButtonAddStart = createGuidelineFromStart(fraction = 0.81f)
        val guidelineButtonAddTop = createGuidelineFromTop(fraction = 0.15f)

        ButtonAdd(modifier = Modifier.constrainAs(addId){
            bottom.linkTo(guidelineButtonAddBottom)
            end.linkTo(guidelineButtonAddEnd)
            start.linkTo(guidelineButtonAddStart)
            top.linkTo(guidelineButtonAddTop)
            height = Dimension.fillToConstraints
            width = Dimension.fillToConstraints
        }, onClick = {onClick.invoke()})
        //endregion
    }
}