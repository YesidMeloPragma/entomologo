package com.pragma.entomologo.logic.constants

enum class DirNamesEnum constructor(private val dirName: String) {
    IMAGES_APP("Entomologo");
    fun getDirName() = dirName
}