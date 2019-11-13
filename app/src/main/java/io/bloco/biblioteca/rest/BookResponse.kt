package io.bloco.biblioteca.rest

import com.google.gson.annotations.SerializedName

class BookResponse {

    @SerializedName("items")
    var items: List<Item>? = null // Each item is a book

    @SerializedName("totalItems")
    var totalItems: Int? = null

}

class Item {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("volumeInfo")
    var volumeInfo: VolumeInfo? = null
}

class VolumeInfo {
     @SerializedName("title")
     var title: String? = null

    @SerializedName("authors")
    var authors: List<String>? = null

    @SerializedName("publishedDate")
    var publishedDate: String? = null

    @SerializedName("averageRating")
    var averageRating: Double? = null

    @SerializedName("industryIdentifiers")
    var industryIdentifiers: List<IndustryIdentifier>? = null

    @SerializedName("imageLinks")
    var imageLinks: ImageLinks? = null
}


class IndustryIdentifier {
    @SerializedName("type")
    var type: String? = null

    @SerializedName("identifier")
    var identifier: String? = null
}

class ImageLinks {
    @SerializedName("smallThumbnail")
    var smallThumbnail: String? = null

    @SerializedName("thumbnail")
    var thumbnail: String? = null
}