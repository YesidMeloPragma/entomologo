package com.pragma.entomologo.ui.views.customs.images

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pragma.entomologo.R
import com.pragma.entomologo.ui.theme.EntomologoTheme

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
            CustomCircularImage(
                modifier = Modifier.constrainAs(customRoundedId){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            },
                placeHolder = R.mipmap.logo_entomology_1,
                //route = R.mipmap.logo_entomology_1,
                bitmap = BitmapFactory.decodeResource(LocalContext.current.resources, R.mipmap.logo_entomology_1),
                widthBorder = 5.dp,
                colorBorder = MaterialTheme.colorScheme.primaryContainer,
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun CustomCircularImage(
    colorBorder: Color = Color.Transparent,
    contentScale: ContentScale = ContentScale.None,
    modifier: Modifier,
    placeHolder: Int,
    route: Int?= null,
    widthBorder: Dp = 0.dp
) {
    if(route == null) {
        Image(
            painter = painterResource(id = placeHolder),
            contentDescription = "",
            modifier = modifier
                .clip(shape = CircleShape)
                .border(
                    border = BorderStroke(width = widthBorder, color = colorBorder),
                    shape = CircleShape
                )
                .aspectRatio(ratio = 1f),
            contentScale = contentScale
        )
        return
    }
    route.let {
        Image(
            painter = painterResource(id = it),
            contentDescription = "",
            modifier = modifier
                .clip(shape = CircleShape)
                .border(
                    border = BorderStroke(width = widthBorder, color = colorBorder),
                    shape = CircleShape
                )
                .aspectRatio(ratio = 1f),
            contentScale = contentScale
        )
        return
    }
}

@Composable
fun CustomCircularImage(
    bitmap: Bitmap? = null,
    colorBorder: Color = Color.Transparent,
    contentScale: ContentScale = ContentScale.None,
    modifier: Modifier,
    placeHolder: Int,
    widthBorder: Dp = 0.dp
) {
    ConstraintLayout(modifier = modifier) {
        val (placeholderId, bitmapId) = createRefs()
        if (bitmap == null) {
            Image(
                painter = painterResource(id = placeHolder),
                contentDescription = "",
                modifier = Modifier
                    .constrainAs(placeholderId){}
                    .clip(shape = CircleShape)
                    .border(
                        border = BorderStroke(width = widthBorder, color = colorBorder),
                        shape = CircleShape
                    )
                    .aspectRatio(ratio = 1f)
                ,
                contentScale = contentScale
            )
        } else {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "",
                modifier = Modifier
                    .constrainAs(bitmapId){}
                    .clip(shape = CircleShape)
                    .border(
                        border = BorderStroke(width = widthBorder, color = colorBorder),
                        shape = CircleShape
                    )
                    .aspectRatio(ratio = 1f),
                contentScale = contentScale
            )
        }
    }
}