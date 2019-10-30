package io.bloco.biblioteca

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val title: String,
    val author: String?,
    val publishDate: String?,
    val isbn: String?,
    val read: Boolean = false
) : Parcelable