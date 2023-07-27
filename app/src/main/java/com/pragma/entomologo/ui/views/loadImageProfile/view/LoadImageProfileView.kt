@file:Suppress("DEPRECATION")

package com.pragma.entomologo.ui.views.loadImageProfile.view

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.pragma.entomologo.R
import com.pragma.entomologo.ui.activities.ActivityState
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.views.customs.buttons.CustomRoundedButtonsWithElevation
import com.pragma.entomologo.ui.views.customs.dialogs.progressDialog.ProgressDialog
import com.pragma.entomologo.ui.views.customs.images.CustomCircularImage
import com.pragma.entomologo.ui.views.loadImageProfile.viewModel.LoadImageProfileViewModel
import com.pragma.entomologo.ui.views.requestPermisions.EnablePermissionsNecessariesInConfigApp
import kotlin.system.exitProcess

//region ui
@Composable
fun LoadImageProfileView(
    modifier: Modifier,
    navigateToProfile: ((Bitmap?)->Unit)? = null,
    navigateToListRecord: ((Bitmap?) -> Unit)? = null,
    viewModel: LoadImageProfileViewModel = hiltViewModel<LoadImageProfileViewModel>(),
    stateActivity : MutableState<ActivityState>
) {
    //region variables io
    val currentState by viewModel.getStateUI().collectAsState()
    logicUI(
        currentStateUI = currentState,
        viewModel = viewModel
    )
    val showDialogPermission = remember { mutableStateOf(false) }
    //endregion

    ConstraintLayout(modifier = modifier) {
        val (headerId, bodyId) = createRefs()

        Header(
            modifier = Modifier.constrainAs(headerId){
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            },
            currentStateUI = currentState,
            showPermissionDialog = showDialogPermission
        )
        Body(
            modifier = Modifier.constrainAs(bodyId){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            },
            currentStateUI = currentState,
            navigateToListRecord = navigateToListRecord,
            navigateToProfile = navigateToProfile,
            viewModel = viewModel
        )
    }

}

//region logic
private fun logicUI(
    currentStateUI: LoadImageProfileViewModel.StateUI,
    viewModel: LoadImageProfileViewModel
) {
    if (currentStateUI.loading == LoadImageProfileViewModel.StatusUI.START) {
        viewModel.checkPermissions()
        return
    }
}
//endregion

//region header
@Composable
fun Header(
    modifier: Modifier,
    currentStateUI: LoadImageProfileViewModel.StateUI,
    showPermissionDialog: MutableState<Boolean>
) {
    ConstraintLayout(modifier = modifier.wrapContentHeight()) {
        if (
            currentStateUI.loading == LoadImageProfileViewModel.StatusUI.LOADING ||
            currentStateUI.loading == LoadImageProfileViewModel.StatusUI.LOADED_IMAGE_PROFILE_FROM_APP
        ) {
            ProgressDialog()
        }
        RequestPermissionGallery(
            currentStateUI = currentStateUI,
            showPermissionDialog = showPermissionDialog
        )
    }
}

@Composable
fun RequestPermissionGallery(
    currentStateUI: LoadImageProfileViewModel.StateUI,
    showPermissionDialog: MutableState<Boolean>
) {

    if (currentStateUI.havePermissionGallery) {
        showPermissionDialog.value = false
        return
    }
    showPermissionDialog.value = true
    if (currentStateUI.loading != LoadImageProfileViewModel.StatusUI.REQUEST_PERMISSION_FIRST_STEP) return
    EnablePermissionsNecessariesInConfigApp(
        showDialog = showPermissionDialog,
        actionCancel = {
            showPermissionDialog.value = false
            exitProcess(0)
        },
        actionDismiss = {
            showPermissionDialog.value = false
        }
    )
}
//endregion

//region body
@Composable
fun Body(
    modifier: Modifier,
    currentStateUI: LoadImageProfileViewModel.StateUI,
    navigateToProfile: ((Bitmap?)->Unit)? = null,
    navigateToListRecord: ((Bitmap?) -> Unit)? = null,
    viewModel: LoadImageProfileViewModel
) {
    ConstraintLayout(modifier = modifier) {
        val (imageProfileId, selectImageId) = createRefs()

        //region imageProfile
        val separationHorizontal = 0.35f
        val guidelineEndImageProfile = createGuidelineFromEnd(separationHorizontal)
        val guidelineStartImageProfile = createGuidelineFromStart(separationHorizontal)
        val guidelineTopImageProfile = createGuidelineFromTop(0.23f)

        ImageProfile(
            modifier = Modifier.constrainAs(imageProfileId){
                end.linkTo(guidelineEndImageProfile)
                start.linkTo(guidelineStartImageProfile)
                top.linkTo(guidelineTopImageProfile)
                width = Dimension.fillToConstraints
            },
            currentStateUI = currentStateUI
        )
        //endregion

        SelectImage(
            modifier = Modifier.constrainAs(selectImageId){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(imageProfileId.bottom)
                width = Dimension.fillToConstraints
                height =  Dimension.fillToConstraints
            },
            currentStateUI = currentStateUI,
            navigateToListRecord = navigateToListRecord,
            navigateToProfile = navigateToProfile,
            viewModel = viewModel
        )

    }
}

//region imageProfile
@Composable
fun ImageProfile(
    modifier: Modifier,
    currentStateUI: LoadImageProfileViewModel.StateUI,
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val imageProfileId = createRef()
        CustomCircularImage(
            modifier = Modifier.constrainAs(imageProfileId){},
            placeHolder = R.mipmap.avatar,
            widthBorder = 0.dp,
            contentScale = ContentScale.FillHeight,
            bitmap = currentStateUI.bitmap
        )
    }
}
//endregion

//region button
@Composable
fun SelectImage(
    modifier: Modifier,
    currentStateUI: LoadImageProfileViewModel.StateUI,
    navigateToProfile: ((Bitmap?)->Unit)? = null,
    navigateToListRecord: ((Bitmap?) -> Unit)? = null,
    viewModel: LoadImageProfileViewModel
) {
    //region variables
    val currentContext = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            it?.let {
                val bitmap = if(Build.VERSION.SDK_INT < 28) {
                    MediaStore
                        .Images
                        .Media
                        .getBitmap(currentContext.contentResolver, it)
                } else {
                    val source = ImageDecoder.createSource(currentContext.contentResolver, it)
                    ImageDecoder.decodeBitmap(source)
                }
                viewModel.updateBitmap(bitmap = bitmap)
                navigateToListRecord?.invoke(bitmap)
                navigateToProfile?.invoke(bitmap)
            }
        }
    )
    //endregion

    ConstraintLayout(modifier = modifier) {
        val buttonLoadImageId = createRef()
        CustomRoundedButtonsWithElevation(
            modifier = Modifier.constrainAs(buttonLoadImageId) {
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top, margin = 20.dp)
            },
            text = stringResource(id = R.string.upload_image),
            onClick = {
                viewModel.checkPermissions()
                if(!currentStateUI.havePermissionGallery) {
                    return@CustomRoundedButtonsWithElevation
                }
                launcher.launch("image/*")
            })


    }
}
//endregion

//endregion

//endregion

//region preview
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun LoadImageProfileViewPreview() {
    val stateActivity : MutableState<ActivityState> = remember { mutableStateOf(value = ActivityState.RESUME) }
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)) {
            LoadImageProfileView(
                modifier = Modifier,
                navigateToProfile = {},
                navigateToListRecord = {},
                viewModel = getViewModel(),
                stateActivity = stateActivity
            )
        }
    }
}

private fun getViewModel()
    = object : LoadImageProfileViewModel() {
    override fun loadView() { Log.i("Informacion", "cargando pantalla")}
    override fun restartStateUI() {Log.i("Informacion", "restartStateUI")}
    override fun startStateUI() {Log.i("Informacion", "startStateUI")}
    override fun updateBitmap(bitmap: Bitmap?) { Log.i("Informacion", "startStateUI") }
    override fun checkPermissions() { Log.i("Informacion", "checkPermissions") }
}
//endregion