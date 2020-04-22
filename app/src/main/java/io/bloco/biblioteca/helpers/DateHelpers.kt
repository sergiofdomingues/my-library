package io.bloco.biblioteca.helpers

import android.annotation.SuppressLint
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateHelpers {

    private const val myFormat = "dd/MM/yyyy"
    private const val googleApiFormat = "yyyy-MM-dd"

    @SuppressLint("ConstantLocale")
    val dateFormat = SimpleDateFormat(myFormat, Locale.getDefault())
    @SuppressLint("ConstantLocale")
    private val gApiFormat = SimpleDateFormat(googleApiFormat, Locale.getDefault())

    private fun stringFromGApiToDate(dateString: String = ""): Date? {

        try {
            return gApiFormat.parse(dateString)
        } catch (e: ParseException) {
            if (isValidYear(dateString)) {
                return try {
                    gApiFormat.parse("$dateString-01-01")
                } catch (ex: Exception) {
                    Timber.d("NumberFormatException trying to parse")
                    null
                }
            }
            return null
        }
    }

    fun parseStringToDate(dateString: String = "") : Date?{

        return if(isInMyFormat(dateString)) {
            stringToDate(dateString)
        } else {
            stringFromGApiToDate(dateString)
        }
    }

    private fun isInMyFormat(dateString: String) : Boolean {
        return try {
            dateFormat.parse(dateString)
            true
        } catch (e: ParseException) {
            false
        }
    }

    private fun isValidYear(str: String): Boolean {
        return try {
            Integer.parseInt(str)
            return (str.length == 4)
        } catch (e: NumberFormatException) {
            Timber.d("NumberFormatException trying to parse String to Int")
            false
        }
    }

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