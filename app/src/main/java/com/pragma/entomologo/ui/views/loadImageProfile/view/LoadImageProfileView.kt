@file:Suppress("DEPRECATION")

package com.pragma.entomologo.ui.views.loadImageProfile.view

import android.Manifest
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.views.customs.buttons.CustomRoundedButtonsWithElevation
import com.pragma.entomologo.ui.views.customs.dialogs.progressDialog.ProgressDialog
import com.pragma.entomologo.ui.views.customs.images.CustomCircularImage
import com.pragma.entomologo.ui.views.loadImageProfile.viewModel.LoadImageProfileViewModel
import com.pragma.entomologo.ui.views.requestPermisions.RequestTheNecessaryPermits
import com.pragma.entomologo.ui.views.requestPermisions.hasPermissions

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun LoadImageProfileViewPreview() {
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)) {
            LoadImageProfileView(
                modifier = Modifier,
                navigateToProfile = {},
                navigateToListRecord = {},
                viewModel = object : LoadImageProfileViewModel() {
                    override fun loadView() { Log.i("Informacion", "cargando pantalla")}
                    override fun requestPermissionsFirstStep() {Log.i("Informacion", "requestPermissionsFirstStep")}
                    override fun restartStateUI() {Log.i("Informacion", "restartStateUI")}
                    override fun startStateUI() {Log.i("Informacion", "startStateUI")}
                    override fun checkPermissions() { Log.i("Informacion", "checkPermissions") }
                    override fun userHasPermissions() { Log.i("Information", "userHasPermissions")}
                }
            )
        }
    }
}

//region ui
@Composable
fun LoadImageProfileView(
    modifier: Modifier,
    navigateToProfile: ((Bitmap?)->Unit)? = null,
    navigateToListRecord: ((Bitmap?) -> Unit)? = null,
    viewModel: LoadImageProfileViewModel = hiltViewModel<LoadImageProfileViewModel>()
) {
    //region variables io
    val currentState by viewModel.getStateUI().collectAsState()
    logicUI(
        currentStateUI = currentState,
        viewModel = viewModel,
    )
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
            viewModel = viewModel,
            navigateToListRecord = navigateToListRecord,
            navigateToProfile = navigateToProfile
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
            viewModel = viewModel,
            navigateToListRecord = navigateToListRecord,
            navigateToProfile = navigateToProfile
        )
    }

}

//region header
@Composable
fun Header(
    modifier: Modifier,
    currentStateUI: LoadImageProfileViewModel.StateUI,
    viewModel: LoadImageProfileViewModel,
    navigateToProfile: ((Bitmap?)->Unit)? = null,
    navigateToListRecord: ((Bitmap?) -> Unit)? = null,
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
            viewModel = viewModel,
            navigateToListRecord = navigateToListRecord,
            navigateToProfile = navigateToProfile,
        )
    }
}

@Composable
fun RequestPermissionGallery(
    currentStateUI: LoadImageProfileViewModel.StateUI,
    navigateToListRecord: ((Bitmap?) -> Unit)? = null,
    navigateToProfile: ((Bitmap?)->Unit)? = null,
    viewModel: LoadImageProfileViewModel,
) {
    val showDialog = remember{ mutableStateOf(false) }
    if (hasPermissions(context = LocalContext.current, permissions = getPermissions())) {
        viewModel.userHasPermissions()
        return
    }
    if (currentStateUI.loading == LoadImageProfileViewModel.StatusUI.VERIFY_PERMISSION) {
        viewModel.requestPermissionsFirstStep()
        showDialog.value = true
        return
    }

    if (currentStateUI.loading == LoadImageProfileViewModel.StatusUI.REQUEST_PERMISSION_FIRST_STEP && showDialog.value) {
        RequestTheNecessaryPermits(
            showDialog = showDialog,
            actionCancel = {
                navigateToListRecord?.invoke(null)
                navigateToProfile?.invoke(null)
                viewModel.restartStateUI()
            }
        )
    }

}
//endregion

//region body
@Composable
fun Body(
    modifier: Modifier,
    currentStateUI: LoadImageProfileViewModel.StateUI,
    viewModel: LoadImageProfileViewModel,
    navigateToProfile: ((Bitmap?)->Unit)? = null,
    navigateToListRecord: ((Bitmap?) -> Unit)? = null,
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
            viewModel = viewModel,
            navigateToListRecord = navigateToListRecord,
            navigateToProfile = navigateToProfile
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
    viewModel: LoadImageProfileViewModel,
    navigateToProfile: ((Bitmap?)->Unit)? = null,
    navigateToListRecord: ((Bitmap?) -> Unit)? = null,
) {
    //region variables
    var imageUri by  remember { mutableStateOf<Uri?>(null) }
    val bitmap =  remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { imageUri = it }
    )
    //endregion

    ConstraintLayout(modifier = modifier) {
        val buttonLoadImageId = createRef()
        val currentContext = LocalContext.current
        CustomRoundedButtonsWithElevation(
            modifier = Modifier.constrainAs(buttonLoadImageId) {
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top, margin = 20.dp)
            },
            text = stringResource(id = R.string.upload_image),
            onClick = {
                if(!hasPermissions(context = currentContext, permissions = getPermissions())) {
                    viewModel.requestPermissionsFirstStep()
                    return@CustomRoundedButtonsWithElevation
                }
                launcher.launch("image/*")
            })

        imageUri?.let {
            if(Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore
                    .Images
                    .Media
                    .getBitmap(currentContext.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(currentContext.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }
            navigateToListRecord?.invoke(bitmap.value)
            navigateToProfile?.invoke(bitmap.value)
        }
    }
}
//endregion

//endregion

//endregion

//region logic
private fun logicUI(
    currentStateUI: LoadImageProfileViewModel.StateUI,
    viewModel: LoadImageProfileViewModel,
) {
    Log.i("Estado", "estado actual: ${currentStateUI.loading}")
    if (currentStateUI.loading == LoadImageProfileViewModel.StatusUI.START) {
        viewModel.loadView()
        return
    }

    if(currentStateUI.loading == LoadImageProfileViewModel.StatusUI.LOADED_IMAGE_PROFILE_FROM_APP) {
        viewModel.checkPermissions()
        return
    }
    if (currentStateUI.loading == LoadImageProfileViewModel.StatusUI.RESTART) {
        viewModel.startStateUI()
        return
    }
}
private fun getPermissions(): Array<String> {
    return arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
}
//endregion