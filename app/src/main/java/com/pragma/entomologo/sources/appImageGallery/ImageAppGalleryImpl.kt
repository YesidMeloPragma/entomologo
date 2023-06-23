package com.pragma.entomologo.sources.appImageGallery

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Base64
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ImageAppGalleryImpl constructor(
    private val basePath: String = "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)}"): ImageAppGallery {
    override fun getImageStringBase64(path: String): Flow<String?> = flow {
        val file = File("$basePath/$path")
        if(!file.exists()) {
            emit(null)
            return@flow
        }

        try {
            val bi = FileInputStream(file.toURI().path)
            val bitmap = BitmapFactory.decodeStream(bi)
            bi.close()
            val bnBase64 = bitmap.convertToStringBase64()
            emit(bnBase64)
        }catch (e: Exception) {
            Log.e("Err", "Surgio un error", e)
            emit(null)
        }
    }.flowOn(Dispatchers.IO)

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