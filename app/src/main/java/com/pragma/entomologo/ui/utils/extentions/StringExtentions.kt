package com.pragma.entomologo.ui.utils.extentions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

fun String.getBitmap() : Bitmap {
    val decodedString = Base64.decode(this, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
}