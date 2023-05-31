package com.pragma.entomologo.logic.constants

enum class FileNamesEnum constructor(private val fileName: String) {
    PROFILE_IMAGE("profile.jpeg");
    fun getFileName() = fileName
}