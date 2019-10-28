package io.bloco.myapplication

import java.io.Serializable

data class Book(
    var title: String?,
    var author: String?,
    var publishDate: String?,
    var isbn: String?,
    var read: Boolean? = true
) : Serializable