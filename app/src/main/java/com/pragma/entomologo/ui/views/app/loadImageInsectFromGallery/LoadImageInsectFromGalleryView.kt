@file:Suppress("DEPRECATION")

package com.pragma.entomologo.ui.views.app.loadImageInsectFromGallery

import android.Manifest
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pragma.entomologo.R
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.utils.extentions.convertToStringBase64
import com.pragma.entomologo.ui.utils.extentions.getBitmap
import com.pragma.entomologo.ui.views.app.loadImageInsectFromGallery.viewModel.LoadImageInsectFromGalleryViewModel
import com.pragma.entomologo.ui.views.customs.images.CustomCircularImage
import com.pragma.entomologo.ui.views.requestPermisions.hasPermissions

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PHONE
)
@Composable
fun LoadImageInsectFromGalleryViewPreview() {
    EntomologoTheme {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            val constraintsId = createRef()
            LoadImageInsectFromGalleryView(modifier = Modifier.constrainAs(constraintsId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            },
                viewModel = object : LoadImageInsectFromGalleryViewModel() {
                    override fun loadUI(insectModel: InsectModel?) {
                        Log.i("Informacion", "Informacion")
                    }

                    override fun updatePhotoInsect(image: String?) {
                        Log.i("Informacion", "Informacion")
                    }

                },
                imageSelected = {}
            )
        }
    }
}

//region ui
@Composable
fun LoadImageInsectFromGalleryView(
    modifier: Modifier = Modifier,
    insectModel: InsectModel? = null,
    viewModel: LoadImageInsectFromGalleryViewModel,
    imageSelected: (String?)->Unit
) {
    //region stateObserver

    val context = LocalContext.current
    val currentState by viewModel.uiState.collectAsState(initial = LoadImageInsectFromGalleryViewModel.LoadImageFromGalleryUIState())
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            if(it == null) return@rememberLauncherForActivityResult

            val bitmap: Bitmap? = if(Build.VERSION.SDK_INT < 28) {
                MediaStore
                    .Images
                    .Media
                    .getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            }

            val imageBase64 = bitmap?.convertToStringBase64()
            viewModel.updatePhotoInsect(image = imageBase64)
            imageSelected.invoke(imageBase64)
        })
    val requestPermissions: MutableState<Boolean> = remember { mutableStateOf(true) }
    val showDialogRequestPermissions: MutableState<Boolean> = remember { mutableStateOf(true) }
    logicApplyInView(
        currentState = currentState,
        insectModel = insectModel,
        viewModel = viewModel
    )

    //endregion

    ConstraintLayout(modifier = modifier) {
        //region variables
        val (imageProfileId) = createRefs()
        //endregion

        ImageInteraction(
            currentState = currentState,
            modifier = Modifier
                .constrainAs(imageProfileId) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
                .clickable {
                    launcherGallery(
                        context = context,
                        launcher = launcher,
                        requestPermissions = requestPermissions,
                        showDialogRequestPermissions = showDialogRequestPermissions,
                    )
                },
        )

    }
}


//region image
@Composable
fun ImageInteraction(
    currentState: LoadImageInsectFromGalleryViewModel.LoadImageFromGalleryUIState,
    modifier: Modifier,
) {
    CustomCircularImage(
        colorBorder = MaterialTheme.colorScheme.primaryContainer,
        placeHolder = R.drawable.scorpion,
        contentScale = ContentScale.FillHeight,
        widthBorder = 5.dp,
        modifier = modifier,
        bitmap = currentState.imageBase64?.getBitmap()
    )
}


//endregion

private fun launcherGallery(
    context: Context,
    launcher: ManagedActivityResultLauncher<String, Uri?>,
    requestPermissions: MutableState<Boolean>,
    showDialogRequestPermissions: MutableState<Boolean>,
) {
    if(!hasPermissions(context = context, permissions = getPermissions())) {
        showDialogRequestPermissions.value = true
        requestPermissions.value = true
        return
    }
    launcher.launch("image/*")
}

//endregion

//region logic
private fun logicApplyInView(
    currentState: LoadImageInsectFromGalleryViewModel.LoadImageFromGalleryUIState,
    viewModel: LoadImageInsectFromGalleryViewModel,
    insectModel: InsectModel?
) {

    when(currentState.loadingState) {
        LoadImageInsectFromGalleryViewModel.LoadingState.START -> viewModel.loadUI(insectModel = insectModel)
        LoadImageInsectFromGalleryViewModel.LoadingState.LOADING -> Log.i("statusLoading imageInsect", currentState.toString())
        LoadImageInsectFromGalleryViewModel.LoadingState.LOADED -> {
            if (currentState.insectModel != insectModel) {
                viewModel.loadUI(insectModel = insectModel)
            }
        }
    }
}

private fun getPermissions(): Array<String> {
    return arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
}
//endregion