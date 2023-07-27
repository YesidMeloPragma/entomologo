package com.pragma.entomologo.sources.appImageGallery

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

class ImageAppGalleryImpl @Inject constructor(
    private val basePath: String,
    private val context: Context
): ImageAppGallery {
    override suspend fun iHaveStoragePermissions(): Boolean {
        val permissions = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        for (permission in permissions) {
            val permissionStatus = ContextCompat.checkSelfPermission(context, permission)
            if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    override suspend fun getImageStringBase64(path: String): String? {
        val file = File("$basePath/$path")
        if(!file.exists()) {
            return null
        }

        return try {
            val bi = FileInputStream(file.toURI().path)
            val bitmap = BitmapFactory.decodeStream(bi)
            bi.close()
            bitmap.convertToStringBase64()
        } catch (e: Exception) {
            Log.e("Err", "Surgio un error", e)
            null
        }
    }

    override suspend fun saveImage(stringBase64: String, path: String, fileName: String) : String {

        //region create directory
        val direct = File("$basePath/$path")

        if (!direct.exists()) {
            val wallPaperDirectory = File("$basePath/$path")
            wallPaperDirectory.mkdirs()
        }
        //endregion

        //region create file
        val file = File("$basePath/$path/$fileName")

        if (file.exists()) {
            file.delete()
        }

        return try {
            val out = FileOutputStream(file)
            val image = stringBase64.getBitmap()
            image.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
            "$path/$fileName"
        } catch (e: Exception) {
            Log.e("err", "Surgio un error", e)
            "$path/$fileName"
        }
        //endregion
    }

    override suspend fun existsImage(path: String): Boolean {
        val file = File("$basePath/$path")
        return file.exists()
    }

    private fun String.getBitmap() : Bitmap {
        val decodedString = Base64.decode(this, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    private fun Bitmap.convertToStringBase64() : String? {
        val baos = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}