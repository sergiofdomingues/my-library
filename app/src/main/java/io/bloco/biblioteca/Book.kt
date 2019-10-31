package io.bloco.biblioteca

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @NonNull val title: String,
    val author: String?,
    val publishDate: Date?,
    val isbn: String?,
    val read: Boolean = false
) : Parcelable {
    //@PrimaryKey(autoGenerate = true) val id: Int = 0
}