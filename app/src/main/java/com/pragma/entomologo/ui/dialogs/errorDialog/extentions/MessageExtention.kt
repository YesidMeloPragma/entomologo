package com.pragma.entomologo.ui.dialogs.errorDialog.extentions

import androidx.annotation.StringRes
import com.pragma.entomologo.R
import com.pragma.entomologo.logic.excepciones.TypeExceptions

@StringRes fun TypeExceptions.getMessage() : Int {
    return when(this) {
        TypeExceptions.GENERIC_EXCEPTION -> R.string.unspected_error_detail
        TypeExceptions.WITHOUT_URL_DESCRIPTION-> R.string.without_url_description_insect
        TypeExceptions.WITHOUT_NAMESPECIE -> R.string.without_namespecie_detail
        TypeExceptions.WITHOUT_IMAGE_SPECIE -> R.string.without_image_specie_detail
    }
}