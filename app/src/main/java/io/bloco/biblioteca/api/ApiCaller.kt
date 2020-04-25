package io.bloco.biblioteca.api

import io.bloco.biblioteca.model.FoundBook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class ApiCaller @Inject constructor(private val apiService: ApiInterface) {

    fun performSearchByQuery(
        query: String,
        onComplete: ((BookSearchResult<Any>) -> Unit)
    ) {
        val call: Call<BookResponse>? =
            apiService.getBookByQuery(query, API_KEY)
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
                                    newFoundBooksList.add(
                                        FoundBook(
                                            title,
                                            authorsStr,
                                            pubDate,
                                            isbn,
                                            googleId,
                                            thumbnail
                                        )
                                    )
                                    onComplete.invoke(BookSearchResult.Success(newFoundBooksList))
                                }
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<BookResponse>?, t: Throwable?) {
                Timber.d("MYCALLBACK FAIL: ${t.toString()}")
                onComplete.invoke(BookSearchResult.Error(Exception(t)))
            }
        })
    }

    private fun getIsbn(isbnList: List<BookResponse.IndustryIdentifier>): String? {
        return (isbnList.firstOrNull { it.type == ISBN_TYPE_13 }
            ?: isbnList.firstOrNull { it.type == ISBN_TYPE_10 }
            ?: isbnList.firstOrNull { it.type == ISBN_TYPE_OTHER }
                )?.identifier
    }

    companion object {
        private const val API_KEY = "AIzaSyDV_QELWETLCzg8iHZB7w2_gBwT1VCmnIo"
        private const val ISBN_TYPE_10 = "ISBN_10"
        private const val ISBN_TYPE_13 = "ISBN_13"
        private const val ISBN_TYPE_OTHER = "OTHER"
        private const val FIRST_3_AUTHORS = 3
    }
}