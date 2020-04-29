package com.sergiodomingues.library.api

import com.google.gson.annotations.SerializedName

class BookResponse {

    @SerializedName("items")
    var items: List<BookResponse.Item>? = null // Each item is a book

    @SerializedName("totalItems")
    var totalItems: Int? = null

    inner class Item {
        @SerializedName("id")
        var id: String? = null

        @SerializedName("volumeInfo")
        var volumeInfo: BookResponse.VolumeInfo? = null
    }

    inner class VolumeInfo {
        @SerializedName("title")
        var title: String? = null

        @SerializedName("authors")
        var authors: List<String>? = null

        @SerializedName("publishedDate")
        var publishedDate: String? = null

        @SerializedName("averageRating")
        var averageRating: Double? = null

        @SerializedName("industryIdentifiers")
        var industryIdentifiers: List<BookResponse.IndustryIdentifier>? = null

        @SerializedName("imageLinks")
        var imageLinks: BookResponse.ImageLinks? = null
    }


    inner class IndustryIdentifier {
        @SerializedName("type")
        var type: String? = null

        @SerializedName("identifier")
        var identifier: String? = null
    }

    inner class ImageLinks {
        @SerializedName("smallThumbnail")
        var smallThumbnail: String? = null

        @SerializedName("thumbnail")
        var thumbnail: String? = null
    }

}