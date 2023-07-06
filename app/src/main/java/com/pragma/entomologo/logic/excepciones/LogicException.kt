package com.pragma.entomologo.logic.excepciones

data class LogicException(
    val typeException: TypeExceptions = TypeExceptions.GENERIC_EXCEPTION
) : Exception(typeException.toString())
