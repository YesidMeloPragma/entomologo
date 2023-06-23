package com.pragma.entomologo.ui.views.requestPermisions

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun hasPermissions(context: Context, permissions: Array<String>): Boolean {
    for (permission in permissions) {
        val permissionStatus = ContextCompat.checkSelfPermission(context, permission)
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            return false
        }
    }
    return true
}