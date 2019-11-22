package io.bloco.biblioteca.api

import io.bloco.biblioteca.model.FoundBook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.text.ParseException

class ApiCaller(private val apiService: ApiInterface?) {

    fun performSearchByQuery(
        query: String,
        onComplete: ((List<FoundBook>) -> Unit)? = null
    ) {
        val call: Call<BookResponse>? =
            apiService?.getBookByQuery(query, API_KEY)
        call?.enqueue(object : Callback<BookResponse> {

            override fun onResponse(
                call: Call<BookResponse>?,
                response: Response<BookResponse>?
            ) {

                val booksList: List<BookResponse.Item>? = response?.body()?.items
                val newFoundBooksList = mutableListOf<FoundBook>()

                booksList?.let {
                    if (booksList.isEmpty()) {
                        Timber.e("Books list empty")
                    } else {
                        for (element in booksList) {
                            val bookInfo = element.volumeInfo
                            bookInfo?.let {
                                val title = bookInfo.title
                                val authors = bookInfo.authors
                                var authorsStr = ""
                                authors?.let {
                                    authorsStr = authors.take(FIRST_3_AUTHORS).joinToString { it }
                                }
                                val pubDate = bookInfo.publishedDate
                                val isbnList = bookInfo.industryIdentifiers
                                var isbn: String? = null
                                isbnList?.let { isbn = getIsbn(isbnList) }
                                val googleId = element.id
                                val thumbnail = bookInfo.imageLinks?.smallThumbnail
                                if (title != null && googleId != null) {
                                    addNewFoundBook(
                                        title,
                                        authorsStr,
                                        pubDate,
                                        isbn,
                                        googleId,
                                        thumbnail,
                                        newFoundBooksList
                                    )
                                    onComplete?.invoke(newFoundBooksList)
                                }
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<BookResponse>?, t: Throwable?) {
                Timber.d("MYCALLBACK FAIL: ${t.toString()}")
            }
        })
    }

    fun performSearchByIsbn(
        query: String,
        onComplete: ((List<FoundBook>) -> Unit)
    ) {
        val call: Call<BookResponse>? =
            apiService?.getBookByIsbn(query, API_KEY)
        call?.enqueue(object : Callback<BookResponse> {

            override fun onResponse(
                call: Call<BookResponse>?,
                response: Response<BookResponse>?
            ) {

                val booksList: List<BookResponse.Item>? = response?.body()?.items
                val newFoundBooksList = mutableListOf<FoundBook>()

                booksList?.let {
                    if (booksList.isEmpty()) {
                        Timber.e("Books list empty")
                    } else {
                        for (element in booksList) {
                            val bookInfo = element.volumeInfo
                            bookInfo?.let {
                                val title = bookInfo.title
                                val authors = bookInfo.authors
                                var authorsStr = ""
                                authors?.let {
                                    authorsStr = authors.take(FIRST_3_AUTHORS).joinToString { it }
                                }
                                val pubDate = bookInfo.publishedDate
                                val isbnList = bookInfo.industryIdentifiers
                                var isbn: String? = null
                                isbnList?.let { isbn = getIsbn(isbnList) }
                                val googleId = element.id
                                val thumbnail = bookInfo.imageLinks?.smallThumbnail
                                if (title != null && googleId != null) {
                                    addNewFoundBook(
                                        title,
                                        authorsStr,
                                        pubDate,
                                        isbn,
                                        googleId,
                                        thumbnail,
                                        newFoundBooksList
                                    )
                                    onComplete.invoke(newFoundBooksList)
                                }
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<BookResponse>?, t: Throwable?) {
                Timber.d("MYCALLBACK FAIL: ${t.toString()}")
            }
        })
    }

    private fun addNewFoundBook(
        title: String,
        author: String?,
        pubDate: String?,
        isbn: String?,
        googleId: String,
        thumbnail: String?,
        currentBooksList: MutableList<FoundBook>
    ) {
        currentBooksList.add(FoundBook(title, author, pubDate, isbn, googleId, thumbnail))
    }

    private fun getIsbn(isbnList: List<BookResponse.IndustryIdentifier>): String? {

        when {
            isbnList.any { IndustryIdentifier ->
                IndustryIdentifier.type == ISBN_TYPE_13
            } -> for (element in isbnList)
                if (element.type == ISBN_TYPE_13)
                    return element.identifier
            isbnList.any { IndustryIdentifier ->
                IndustryIdentifier.type == ISBN_TYPE_10
            } -> for (element in isbnList)
                if (element.type == ISBN_TYPE_10)
                    return element.identifier
            isbnList.any { IndustryIdentifier ->
                IndustryIdentifier.type == ISBN_TYPE_OTHER
            } -> for (element in isbnList)
                if (element.type == ISBN_TYPE_OTHER)
                    return element.identifier
        }
        return null
    }

    fun queryIsAnIsbn(query: String?): Boolean {
        return try {
            query?.toLong()
            true
        } catch (e: ParseException) {
            false
        } catch (e: NumberFormatException) {
            false
        }
    }

    companion object {
        private const val API_KEY = "AIzaSyDV_QELWETLCzg8iHZB7w2_gBwT1VCmnIo"
        private const val ISBN_TYPE_10 = "ISBN_10"
        private const val ISBN_TYPE_13 = "ISBN_13"
        private const val ISBN_TYPE_OTHER = "OTHER"
        private const val FIRST_3_AUTHORS = 3
    }
}