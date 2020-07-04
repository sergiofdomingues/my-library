package com.sergiodomingues.library.api

import com.google.gson.annotations.SerializedName
import com.sergiodomingues.library.model.FoundBook

data class BookResponse(

    @SerializedName("items")
    var items: List<Item>? = null, // Each item is a book
    @SerializedName("totalItems")
    var totalItems: Int
) {

    fun toModel() =
        items?.map { it.toModel() } ?: emptyList()

    data class Item(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("volumeInfo")
        var volumeInfo: VolumeInfo
    ) {

        fun toModel() =
            FoundBook(
                title = volumeInfo.title,
                author = volumeInfo.authors?.take(FIRST_3_AUTHORS)?.joinToString { it },
                publishedDate = volumeInfo.publishedDate,
                isbn = volumeInfo.industryIdentifiers.filterIsbn(),
                googleId = id ?: "",
                imageCover = volumeInfo.imageLinks?.smallThumbnail
                    ?.replace("http://", "https://") ?: ""
            )

        private fun List<IndustryIdentifier>?.filterIsbn(): String? {
            return (this?.firstOrNull { it.type == ISBN_TYPE_13 }
                ?: this?.firstOrNull { it.type == ISBN_TYPE_10 }
                ?: this?.firstOrNull { it.type == ISBN_TYPE_OTHER }
                    )?.identifier
        }
    }

    data class VolumeInfo(
        @SerializedName("title")
        var title: String,
        @SerializedName("authors")
        var authors: List<String>? = null,
        @SerializedName("publishedDate")
        var publishedDate: String? = null,
        @SerializedName("averageRating")
        var averageRating: Double? = null,
        @SerializedName("industryIdentifiers")
        var industryIdentifiers: List<IndustryIdentifier>? = null,
        @SerializedName("imageLinks")
        var imageLinks: ImageLinks? = null
    )

    data class IndustryIdentifier(
        @SerializedName("type")
        var type: String? = null,
        @SerializedName("identifier")
        var identifier: String? = null
    )

    data class ImageLinks(
        @SerializedName("smallThumbnail")
        var smallThumbnail: String? = null,
        @SerializedName("thumbnail")
        var thumbnail: String? = null
    )

    companion object {
        private const val ISBN_TYPE_10 = "ISBN_10"
        private const val ISBN_TYPE_13 = "ISBN_13"
        private const val ISBN_TYPE_OTHER = "OTHER"
        private const val FIRST_3_AUTHORS = 3
    }
}
