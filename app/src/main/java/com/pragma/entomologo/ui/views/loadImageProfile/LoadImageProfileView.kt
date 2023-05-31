@file:Suppress("DEPRECATION")
package com.pragma.entomologo.ui.views.loadImageProfile

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import com.pragma.entomologo.R
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.views.customs.buttons.CustomRoundedButtonsWithElevation
import com.pragma.entomologo.ui.views.customs.dialogs.progressDialog.ProgressDialog
import com.pragma.entomologo.ui.views.customs.images.CustomCircularImage

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun LoadImageProfileViewPreview() {
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)) {
            val constraintsId = createRef()
            LoadImageProfileView(modifier = Modifier.constrainAs(constraintsId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            },
                imageSelected = {},
                navigateToProfile = {},
                isPreview = true
            )
        }
    }
}

@Composable
fun LoadImageProfileView(
    modifier: Modifier,
    imageSelected: (Bitmap?)->Unit,
    navigateToProfile: ()->Unit,
    isPreview: Boolean = false
) {
    //region variables
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current
    var imageUri by  remember { mutableStateOf<Uri?>(null) }
    var loading by rememberSaveable { mutableStateOf(false) }
    var showButtons by rememberSaveable { mutableStateOf(true) }
    val requestPermissions: MutableState<Boolean> = remember { mutableStateOf(true) }
    val showDialogRequestPermissions: MutableState<Boolean> = remember { mutableStateOf(true) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            loading = it != null
            showButtons = it == null
            imageUri = it
        })
    //endregion

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        if(requestPermissions.value) {
            CheckRequestPermission(
                isPreview = isPreview,
                showDialog = showDialogRequestPermissions,
                permissions = getPermissions()
            )
        }

        val (
            imageProfileId,
            buttonLoadImageId
        ) = createRefs()

        //region profile image
        imageUri?.let {
            if(Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore
                    .Images
                    .Media
                    .getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { bitmapResponse ->
                val guidelineImageBottom = createGuidelineFromBottom(0.63f)
                val guidelineImageEnd = createGuidelineFromEnd(0.35f)
                val guidelineImageStart = createGuidelineFromStart(0.35f)
                val guidelineImageTop = createGuidelineFromTop(0.23f)
                CustomCircularImage(
                    modifier = Modifier.constrainAs(imageProfileId){
                        bottom.linkTo(guidelineImageBottom)
                        end.linkTo(guidelineImageEnd)
                        start.linkTo(guidelineImageStart)
                        top.linkTo(guidelineImageTop)
                        width = Dimension.fillToConstraints
                    },
                    bitmap = bitmapResponse,
                    placeHolder = R.mipmap.avatar,
                    contentScale = ContentScale.FillHeight
                )
                imageSelected.invoke(bitmapResponse)
                navigateToProfile.invoke()
            }
        }

        if (bitmap.value == null) {
            val guidelineImageBottom = createGuidelineFromBottom(0.63f)
            val guidelineImageEnd = createGuidelineFromEnd(0.35f)
            val guidelineImageStart = createGuidelineFromStart(0.35f)
            val guidelineImageTop = createGuidelineFromTop(0.23f)
            CustomCircularImage(
                modifier = Modifier.constrainAs(imageProfileId){
                    bottom.linkTo(guidelineImageBottom)
                    end.linkTo(guidelineImageEnd)
                    start.linkTo(guidelineImageStart)
                    top.linkTo(guidelineImageTop)
                    width = Dimension.fillToConstraints
                },
                placeHolder = R.mipmap.avatar,
                contentScale = ContentScale.FillHeight,
            )
        }
        //endregion

        //region button upload image
        if(showButtons) {
            val guidelineBottomButton = createGuidelineFromBottom(0.5f)
            val guidelineEndButton = createGuidelineFromEnd(0.3f)
            val guidelineStartButton = createGuidelineFromStart(0.3f)
            val guidelineTopButton = createGuidelineFromTop(0.45f)

            CustomRoundedButtonsWithElevation(
                modifier = Modifier.constrainAs(buttonLoadImageId){
                    bottom.linkTo(guidelineBottomButton)
                    end.linkTo(guidelineEndButton)
                    start.linkTo(guidelineStartButton)
                    top.linkTo(guidelineTopButton)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
                text = "Subir foto de pefil",
                onClick = {
                    if (!hasPermissions(context = context, permissions = getPermissions())) {
                        showDialogRequestPermissions.value = true
                        requestPermissions.value = true
                        return@CustomRoundedButtonsWithElevation
                    }
                    loading = true
                    showButtons = false
                    launcher.launch("image/*")
                })
        }
        //endregion

        if(loading) {
            ProgressDialog()
        }

    }
}

//region request permission
@Composable
private fun CheckRequestPermission(
    isPreview: Boolean = false,
    showDialog: MutableState<Boolean>,
    permissions: Array<String>
) {
    if(isPreview) return

    if (hasPermissions(context = LocalContext.current, permissions = permissions)) return

    RequestTheNecessaryPermits(showDialog = showDialog)
}

fun hasPermissions(context: Context, permissions: Array<String>): Boolean {
    for (permission in permissions) {
        val permissionStatus = ContextCompat.checkSelfPermission(context, permission)
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            return false
        }
    }
    return true
}

@Composable
fun RequestTheNecessaryPermits(
    showDialog: MutableState<Boolean>,
) {
    if (!showDialog.value) return

    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = { /* No hacer nada */ },
        title = { Text("Permisos Multimedia") },
        text = { Text("Estos permisos son necesarios para poder capturar imagenes desde tu galeria para vincularla al perfil") },
        confirmButton = {
            Button(
                onClick = {
                    showDialog.value = false

                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", context.applicationContext.packageName, null)
                    intent.data = uri
                    context.startActivity(intent)
                }
            ) {
                Text("habilitar")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    showDialog.value = false
                }
            ) {
                Text("cancelar")
            }
        }
    )
}

private fun getPermissions(): Array<String> {
    return arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
}
//endregion
