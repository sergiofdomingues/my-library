package io.bloco.biblioteca.app.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import io.bloco.biblioteca.R
import io.bloco.biblioteca.adapter.SearchBooksAdapter
import io.bloco.biblioteca.app.App
import io.bloco.biblioteca.model.FoundBook
import kotlinx.android.synthetic.main.activity_search_book.*
import timber.log.Timber

class SearchBookActivity : AppCompatActivity(), SearchBooksAdapter.ListItemClick {
    private val foundBooksList = mutableListOf<FoundBook>()
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }
    private val adapter by lazy { SearchBooksAdapter(foundBooksList, this) }
    private val api by lazy { (application as App).getApiCaller() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book)

        recViewBooksFoundList.layoutManager = linearLayoutManager
        recViewBooksFoundList.addItemDecoration(
            io.bloco.biblioteca.helpers.DividerItemDecoration(
                this
            )
        )
        recViewBooksFoundList.itemAnimator = DefaultItemAnimator()
        recViewBooksFoundList.adapter = adapter
    }

    override fun itemClick(book: FoundBook) {
        val addBookIntent = AddBookActivity.getIntent(this, book)
        startActivityForResult(addBookIntent, ADD_NEW_BOOK)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        (menu.findItem(R.id.itemSearchBook).actionView as SearchView).apply {
            this.queryHint = getString(R.string.search_query_hint)
            this.isIconified = false
            this.onActionViewExpanded()
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    onQueryTextChange(query)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    refreshFoundBooksList(api.performBookSearch(newText))
                    return true
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id: Int? = item?.itemId
        if (id == R.id.itemAddBook) {
            val addBookIntent = AddBookActivity.getIntent(this)
            startActivityForResult(addBookIntent, ADD_NEW_BOOK)
        } else if (id == R.id.itemSearchBook) {
            verifyInternetConnection()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            returnToMainActivity()
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Timber.d("ResultCanceled")
        }
    }

    private fun internetEnabled(): Boolean {
        val cManager =
            (applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

        if (Build.VERSION.SDK_INT < 23) run {
            val ni: NetworkInfo = cManager.activeNetworkInfo
                return (ni.isConnected && ni.type == ConnectivityManager.TYPE_WIFI
                        || ni.type == ConnectivityManager.TYPE_MOBILE)
        } else {
            val activeNetwork = cManager.activeNetwork
            if (activeNetwork != null) {
                val nc = cManager.getNetworkCapabilities(activeNetwork)
                return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                ))
            }
        }
        return false
    }

    private fun verifyInternetConnection() {
        if (!internetEnabled()) {

            val builder = AlertDialog.Builder(this)
            builder.setMessage(getString(R.string.option_enable_wifi))
                .setTitle(getString(R.string.no_internet))
                .setCancelable(false)
                .setPositiveButton(
                    getString(R.string.option_yes)
                ) { _, _ ->
                    val wifiManager =
                        (applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager)
                    wifiManager.isWifiEnabled = true
                }
                .setNegativeButton(
                    getString(R.string.option_cancel)
                ) { _, _ -> finish() }

            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

    private fun returnToMainActivity() {
        setResult(
            Activity.RESULT_OK,
            getResultIntent()
        )
        finish()
    }

    private fun refreshFoundBooksList(tmpList: List<FoundBook>) {
        foundBooksList.clear()
        foundBooksList.addAll(tmpList)
        adapter.notifyDataSetChanged()
    }

    companion object {

        fun getResultIntent() =
            Intent().also {
                it.putExtra(
                    RESULT_NEW_BOOK,
                    ADD_NEW_BOOK
                )
            }

        fun getIntent(context: Context): Intent {
            return Intent(context, SearchBookActivity::class.java)
        }

        private const val ADD_NEW_BOOK = 10
        private const val RESULT_NEW_BOOK = "ADDED_BOOK"
    }
}
