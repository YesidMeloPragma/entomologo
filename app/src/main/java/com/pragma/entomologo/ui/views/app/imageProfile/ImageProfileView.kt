package com.pragma.entomologo.ui.views.app.imageProfile

import android.content.res.Configuration
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pragma.entomologo.R
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.views.app.imageProfile.viewModel.ImageProfileViewModel
import com.pragma.entomologo.ui.views.customs.images.CustomCircularImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PHONE
)
@Composable
fun ImageProfileViewPreview() {
    EntomologoTheme {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            val constraintsId = createRef()
            val state = MutableStateFlow(value = ImageProfileViewModel.ImageProfileUIState())
            ImageProfileView(modifier = Modifier.constrainAs(constraintsId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            },
                viewModel = object : ImageProfileViewModel() {
                    override fun loadImage() { Log.i("Info", "Lectura")}
                    override fun setImageSelected(bitmap: Bitmap?) { Log.i("Info", "Lectura") }
                    override fun getStateUI(): StateFlow<ImageProfileUIState> = state
                },
                action = {}
            )
        }
    }
}

@Composable
fun ImageProfileView(
    modifier: Modifier = Modifier,
    viewModel: ImageProfileViewModel,
    action: () -> Unit
) {
    //region stateObserver
    val currentState by viewModel.getStateUI().collectAsState(initial = ImageProfileViewModel.ImageProfileUIState())
    logicUI(
        viewModel = viewModel,
        stateUI = currentState
    )
    //endregion

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        //region variables
        val (imageProfileId) = createRefs()
        //endregion
        CustomCircularImage(
            modifier = Modifier
                .constrainAs(imageProfileId) {
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            }.clickable {
                action.invoke()
            },
            placeHolder = R.mipmap.avatar,
            contentScale = ContentScale.FillHeight,
            bitmap = currentState.bitmap
        )
    }
}

private fun logicUI(
    viewModel: ImageProfileViewModel,
    stateUI: ImageProfileViewModel.ImageProfileUIState
) {
    when(stateUI.loadingState) {
        ImageProfileViewModel.StateUI.START -> viewModel.loadImage()
        ImageProfileViewModel.StateUI.LOADING -> {}
        ImageProfileViewModel.StateUI.LOADED -> {}
    }
}