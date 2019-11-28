package io.bloco.biblioteca.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class FoundBook @Inject constructor(val title: String,
                     val author: String? = null,
                     val publishedDate: String? = null,
                     val isbn: String? = null,
                     val googleId: String,
                     var thumbnail: String?) : Parcelable