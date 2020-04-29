package com.sergiodomingues.library.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoundBook(val title: String,
                     val author: String? = null,
                     val publishedDate: String? = null,
                     val isbn: String? = null,
                     val googleId: String,
                     var thumbnail: String?) : Parcelable