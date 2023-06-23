package com.pragma.entomologo.ui.dialogs.errorDialog.extentions

import androidx.annotation.StringRes
import com.pragma.entomologo.R
import com.pragma.entomologo.logic.excepciones.TypeExceptions

@StringRes fun TypeExceptions.getTitle(): Int {
    return when(this) {
        TypeExceptions.GENERIC_EXCEPTION -> R.string.unspected_error
        TypeExceptions.WITHOUT_IMAGE_SPECIE -> R.string.without_image_specie
        TypeExceptions.WITHOUT_NAMESPECIE -> R.string.without_namespecie
        TypeExceptions.WITHOUT_URL_DESCRIPTION -> R.string.without_url
    }
}