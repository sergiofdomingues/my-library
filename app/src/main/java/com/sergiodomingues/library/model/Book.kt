package com.sergiodomingues.library.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
data class Book(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @SerializedName("title")
    @NonNull val title: String,
    val author: String?,
    //@SerializedName("publishedDate")
    val publishDate: Date?,
    @SerializedName("isbn")
    val isbn: String?,
    val read: Boolean = false,
    val uriCover: String? = null
) : Parcelable