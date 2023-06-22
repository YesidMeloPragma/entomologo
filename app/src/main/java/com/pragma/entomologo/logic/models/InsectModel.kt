package com.pragma.entomologo.logic.models

data class InsectModel (
    var id: Long? = null,
    var specieName: String,
    var urlPhoto: String,
    var moreInformation: String
) {
    override fun toString(): String {
        return specieName
    }
}