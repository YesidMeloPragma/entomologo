package com.pragma.entomologo.ui.views.requestPermisions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext

@Composable
fun CheckRequestPermission(
    isPreview: Boolean = false,
    showDialog: MutableState<Boolean>,
    permissions: Array<String>
) {
    if(isPreview) return

    if (hasPermissions(context = LocalContext.current, permissions = permissions)) return

    RequestTheNecessaryPermits(showDialog = showDialog)
}