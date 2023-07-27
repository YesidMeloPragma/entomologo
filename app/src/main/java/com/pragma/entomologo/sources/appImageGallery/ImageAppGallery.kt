package com.pragma.entomologo.sources.appImageGallery

/**
 * This class save and load images in custom path.
 * the path has be the next caracteristics:
 * 1. use how base path: val base = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
 * 2. asign name directory example: val dirName = "entomologo"
 * 3. asing nam file example: val fileName "profile.jpg"
 *
 * the final path is next : "$base/$dirName/$fileName"
 *
 * Notes:
 * 1. implement in manifiest the next permissions
 * <uses-permission android:name="android.permission.READ_MEDIA_IMAGES"/>
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
 * <uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE"
 * tools:ignore="ScopedStorage"/>
 *
 * 2. request permissions in by read and write in application
 *
 * @author Elver Yesid Melo
 */
interface ImageAppGallery {
    suspend fun iHaveStoragePermissions(): Boolean
    suspend fun getImageStringBase64(path: String) : String?
    suspend fun saveImage(stringBase64: String, path: String, fileName: String) : String?
    suspend fun existsImage(path: String): Boolean
}