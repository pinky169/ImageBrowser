package pl.patryk.imagebrowser.activities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.search_results_layout.*
import pl.patryk.imagebrowser.R
import pl.patryk.imagebrowser.adapter.RecyclerAdapter
import pl.patryk.imagebrowser.converters.QueryConverter
import pl.patryk.imagebrowser.viewmodel.ItemViewModel
import java.util.*


class SearchResultActivity : AppCompatActivity() {

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var adapter: RecyclerAdapter
    private lateinit var query: String
    private lateinit var type: String
    private lateinit var category: String
    private lateinit var orientation: String

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_results_layout)

        setupRecycler()
        getExtrasFromIntent()
        setupViews()
        SearchActivity().setupBackgroundAnimation(search_result_coordinator_layout)

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        if (isNetworkAvailable(this))
            itemViewModel.getSearchData(query, type, category, orientation)
        else
            Toasty.warning(this, "No internet connection. Showing cached list in the view.", Toast.LENGTH_LONG).show()

        itemViewModel.allItems.observe(this, Observer { items ->
            items?.let {
                if (items.items.isEmpty()) {
                    recycler_view.visibility = View.GONE
                    empty_view.visibility = View.VISIBLE
                } else {
                    empty_view.visibility = View.GONE
                    recycler_view.visibility = View.VISIBLE
                    adapter.setItems(it.items)
                }
            }
        })
    }

    private fun getExtrasFromIntent() {
        query = intent.getStringExtra(SearchActivity.TAG_QUERY)
        type = intent.getStringExtra(SearchActivity.TAG_TYPE)
        type = QueryConverter.getImageTypeQuery(type)
        category = intent.getStringExtra(SearchActivity.TAG_CATEGORY)
        category = QueryConverter.getImageCategoryQuery(category)
        orientation = intent.getStringExtra(SearchActivity.TAG_ORIENTATION)
        orientation = QueryConverter.getImageOrientationQuery(orientation)
    }

    @ExperimentalStdlibApi
    private fun setupViews() {
        setSupportActionBar(search_results_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = query.capitalize(Locale.getDefault())
        empty_view.setOnClickListener { finish() }
    }

    private fun setupRecycler() {
        adapter = RecyclerAdapter(this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val cm: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}