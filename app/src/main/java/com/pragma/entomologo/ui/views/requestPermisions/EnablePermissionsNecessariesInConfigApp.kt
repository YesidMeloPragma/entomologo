package com.pragma.entomologo.ui.views.requestPermisions

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.pragma.entomologo.R

@Composable
fun EnablePermissionsNecessariesInConfigApp(
    showDialog: MutableState<Boolean>,
    actionCancel: ()-> Unit,
    actionDismiss: (()-> Unit) ?= null
) {
    if (!showDialog.value) return

    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = { actionDismiss?.invoke() },
        title = { Text(stringResource(id = R.string.permissions)) },
        text = {
                Text(
                    "${stringResource(id = R.string.permissions_required)}:\n" +
                            "1. ${stringResource(id = R.string.storage)}:\n ${stringResource(id = R.string.enable_permissions_detail)}\n" +
                            "2. ${stringResource(id = R.string.ubication)}:\n ${stringResource(id = R.string.enable_permissions_ubication)}\n" +
                            "${stringResource(id = R.string.without_some_permission)}.",
                )
               },
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
                Text(stringResource(id = R.string.enable))
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    showDialog.value = false
                    actionCancel.invoke()
                }
            ) {
                Text(stringResource(id = R.string.cancel))
            }
        }
    )
}
