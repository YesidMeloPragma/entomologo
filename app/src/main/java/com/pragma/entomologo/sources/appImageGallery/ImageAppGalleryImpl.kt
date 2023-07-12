package com.pragma.entomologo.sources.appImageGallery

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ImageAppGalleryImpl constructor(
    private val basePath: String = "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)}"): ImageAppGallery {
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
            //file.path?:""
        } catch (e: Exception) {
            Log.e("err", "Surgio un error", e)
            ""
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