package com.pragma.entomologo.logic.models

data class ResumeRecordCounterInsectModel(
    val insectId: Long,
    val nameInsect: String,
    val imageBase64: String,
    val totalCount: Long
)
