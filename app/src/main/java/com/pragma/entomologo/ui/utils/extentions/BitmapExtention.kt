package com.pragma.entomologo.ui.utils.extentions

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

/**
 * this method is used by convert a bitmap image to string base64
 * source: Medium -> Reddi Tintaya
 * url: https://medium.com/@reddytintaya/note-1-android-bitmap-to-base64-string-with-kotlin-552890c56b04
 * @return String with image in string format
 */
fun Bitmap.convertToStringBase64() : String? {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val b = baos.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}