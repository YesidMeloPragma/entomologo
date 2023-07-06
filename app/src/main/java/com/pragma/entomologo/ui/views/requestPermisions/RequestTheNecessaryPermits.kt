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

@Composable
fun RequestTheNecessaryPermits(
    showDialog: MutableState<Boolean>,
    actionCancel: ()-> Unit
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
                    actionCancel.invoke()
                }
            ) {
                Text("cancelar")
            }
        }
    )
}
