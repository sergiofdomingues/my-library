package io.bloco.biblioteca.helpers

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Helpers {
    private const val myFormat = "dd/MM/yyyy"
    @SuppressLint("ConstantLocale")
    private val dateFormat = SimpleDateFormat(myFormat, Locale.getDefault())

    fun stringToDate(dateString: String = "22/10/11"): Date? {
        return try{
            dateFormat.parse(dateString)
        } catch (e: ParseException){
            null
        }
    }

    fun dateToString(date: Date?): String {
        return date?.let { dateFormat.format(it) } ?: ""
    }
}