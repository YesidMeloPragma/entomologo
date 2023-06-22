package com.pragma.entomologo.logic.usesCase.registerEntomologistUseCase

import com.pragma.entomologo.logic.models.EntomologistModel

interface RegisterEntomologistUseCase {

    suspend fun invoke(
        bitmapBase64: String,
        entomologistModel: EntomologistModel
    ) : Boolean

}