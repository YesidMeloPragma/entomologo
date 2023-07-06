package com.pragma.entomologo.logic.constants

enum class DirNamesEnum constructor(private val dirName: String) {
    IMAGES_APP("Entomologo"),
    INSECT_RECORDS("${IMAGES_APP.dirName}/insects"),
    ;
    fun getDirName() = dirName
}