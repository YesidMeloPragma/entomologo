package com.pragma.entomologo.ui.utils.formats

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

enum class DateFormats(val format: String) {
    Slash(format = "dd/MM/yyyy"),
    ;

    @SuppressLint("SimpleDateFormat")
    fun dateToFormat(date: Date) : String {
        return SimpleDateFormat(format).format(date)
    }
}