package pl.patryk.imagebrowser

import android.content.AbstractThreadedSyncAdapter
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*
import pl.patryk.imagebrowser.adapter.RecyclerAdapter
import pl.patryk.imagebrowser.viewmodel.ItemViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecycler()

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        if(isNetworkAvailable(this))
            itemViewModel.getData()
        else
            Toasty.warning(this, "No internet connection. Showing cached list in the view.", Toast.LENGTH_LONG).show()

        itemViewModel.allItems.observe(this, Observer { items -> items?.let{ adapter.setItems(it.items) } })
    }

    private fun setUpRecycler() {
        adapter = RecyclerAdapter(this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}
