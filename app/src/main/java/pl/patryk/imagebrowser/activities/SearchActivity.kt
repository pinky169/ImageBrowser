package pl.patryk.imagebrowser.activities

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.search_layout.*
import pl.patryk.imagebrowser.R


class SearchActivity : AppCompatActivity() {

    companion object {
        const val TAG_QUERY = "QUERY"
        const val TAG_TYPE = "TYPE"
        const val TAG_CATEGORY = "CATEGORY"
        const val TAG_ORIENTATION = "ORIENTATION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_layout)

        setupViews()
        setupBackgroundAnimation(coordinator_layout)
    }

    private fun setupViews() {
        search_view.setOnQueryTextListener(queryTextListener)
        layout_spinner_type.setOnClickListener(listener)
        layout_spinner_category.setOnClickListener(listener)
        layout_spinner_orientation.setOnClickListener(listener)
        setSupportActionBar(toolbar)
    }

    fun setupBackgroundAnimation(view: View) {
        val animationDrawable = view.background as? AnimationDrawable
        animationDrawable?.setEnterFadeDuration(2000)
        animationDrawable?.setExitFadeDuration(4000)
        animationDrawable?.start()
    }

    private val queryTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                startSearchResultActivity(s)
                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        }

    private val listener: View.OnClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.layout_spinner_type -> spinner_type.performClick()
            R.id.layout_spinner_category -> spinner_category.performClick()
            R.id.layout_spinner_orientation -> spinner_orientation.performClick()
        }
    }

    private fun startSearchResultActivity(input: String) {
        val intent = Intent(this, SearchResultActivity::class.java)
        intent.putExtra(TAG_QUERY, input)
        intent.putExtra(TAG_TYPE, spinner_type.selectedItem.toString())
        intent.putExtra(TAG_CATEGORY, spinner_category.selectedItem.toString())
        intent.putExtra(TAG_ORIENTATION, spinner_orientation.selectedItem.toString())
        startActivity(intent)
    }
}
