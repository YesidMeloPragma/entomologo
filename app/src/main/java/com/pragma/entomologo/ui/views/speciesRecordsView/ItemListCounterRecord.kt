package com.pragma.entomologo.ui.views.speciesRecordsView

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pragma.entomologo.R
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.logic.models.GeoLocationModel
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.utils.extentions.getBitmap

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun ItemListCounterRecordPreview() {
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)) {
            val constraintsId = createRef()
            ItemListCounterRecord(modifier = Modifier.constrainAs(constraintsId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            },
                counter = CounterRecordInsectModel(
                    id= 1,
                    insect = InsectModel( specieName = "Hormiga", urlPhoto = "", moreInformation = "aqui"),
                    geoLocation = GeoLocationModel(lat = 1.11, lng = 2.22),
                    comment = "Un comentario",
                    count = 6,
                )
            )
        }
    }
}

@Composable
fun ItemListCounterRecord(
    modifier: Modifier,
    counter: CounterRecordInsectModel
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(36.dp / 8.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(size = 12.dp)
            )
    ) {
        val (
            counterId,
            detailId,
            imageId
        ) = createRefs()

        //region counter
        val guidelineCounterBottom = createGuidelineFromBottom(0.25f)
        val guidelineCounterEnd = createGuidelineFromEnd(0.84f)
        val guidelineCounterStart = createGuidelineFromStart(0.06f)
        val guidelineCounterTop = createGuidelineFromTop(0.25f)
        ConstraintLayout(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                .aspectRatio(1f)
                .constrainAs(counterId) {
                    bottom.linkTo(guidelineCounterBottom)
                    end.linkTo(guidelineCounterEnd)
                    start.linkTo(guidelineCounterStart)
                    top.linkTo(guidelineCounterTop)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
        ){
            val textId = createRef()
            Text(
                text = if(counter.count < 10) "0${counter.count}" else "${counter.count}",
                modifier = Modifier.constrainAs(textId){
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.surface)
            )
        }
        //endregion

        //region detail
        val guidelineBottomDetail = createGuidelineFromBottom(0.16f)
        val guidelineEndDetail = createGuidelineFromEnd(0.27f)
        val guidelineStartDetail = createGuidelineFromStart(0.2f)
        val guidelineTopDetail = createGuidelineFromTop(0.16f)

        ConstraintLayout(modifier = Modifier.constrainAs(detailId) {
            bottom.linkTo(guidelineBottomDetail)
            end.linkTo(guidelineEndDetail)
            start.linkTo(guidelineStartDetail)
            top.linkTo(guidelineTopDetail)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }) {
            val (titleId, cityId) = createRefs()
            Text(
                text = counter.insect.specieName,
                modifier = Modifier.constrainAs(titleId){
                    bottom.linkTo(cityId.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "Ciudad 0/00/0000",
                modifier = Modifier.constrainAs(cityId){
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(titleId.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface)
            )
        }
        //endregion

        //region image
        val guidelineEndImage = createGuidelineFromEnd(0.03f)
        ImageInsect(
            modifier = Modifier
                .constrainAs(imageId){
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                    end.linkTo(guidelineEndImage)
                    top.linkTo(parent.top, margin = 10.dp)

                    height = Dimension.fillToConstraints
                }
            ,
            counter = counter
        )
        //endregion

    }
}

@Composable
fun ImageInsect(
    modifier: Modifier,
    counter: CounterRecordInsectModel
) {
    ConstraintLayout(
        modifier = modifier
            .aspectRatio(1f)
    ) {
        val (imageId, placeholderId) = createRefs()
        if (counter.imageBase64 == null) {
            Image(
                painter = painterResource(id = R.drawable.scorpion),
                contentDescription = "",
                modifier = Modifier.constrainAs(placeholderId){
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
            )
        } else {
            Image(
                bitmap = counter.imageBase64!!.getBitmap().asImageBitmap(),
                contentDescription = "",
                modifier = Modifier.constrainAs(imageId){
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
            )
        }

    }
}