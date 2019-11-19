package io.bloco.biblioteca.helpers

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Helpers {

    private const val myFormat = "dd/MM/yyyy"

    @SuppressLint("ConstantLocale")
    val dateFormat = SimpleDateFormat(myFormat, Locale.getDefault())

    fun stringToDate(dateString: String = ""): Date? {
        return try {
            dateFormat.parse(dateString)
        } catch (e: ParseException) {
            null
        }
    }

    fun dateToString(date: Date?): String = date?.let { dateFormat.format(it) } ?: ""

    fun dateToStringDatePicker(dateFromPicker: Date): String =
        SimpleDateFormat(myFormat, Locale.getDefault()).format(dateFromPicker)
}