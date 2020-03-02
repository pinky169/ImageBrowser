package pl.patryk.imagebrowser.activities

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.details_layout.*
import pl.patryk.imagebrowser.R
import pl.patryk.imagebrowser.adapter.RecyclerAdapter
import pl.patryk.imagebrowser.model.ItemEntity
import pl.patryk.imagebrowser.utils.DownloadHelper

class DetailsActivity : AppCompatActivity() {

    private lateinit var item: ItemEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_layout)

        item = intent.getSerializableExtra(RecyclerAdapter.TAG_ITEM) as ItemEntity

        init(item)
    }

    private fun init(item: ItemEntity) {
        Glide
                .with(this)
                .load(item.largeImageURL)
                .into(photo_view)

        txt_view_downloads.text = resources.getString(R.string.total_downloads, item.downloads)
        txt_view_size.text = resources.getString(R.string.size, item.webformatWidth, item.webformatHeight)
        action_download.setOnClickListener { DownloadHelper(this, item).requestDownload() }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            DownloadHelper.REQUEST_PERMISSION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                DownloadHelper(this, item).startDownload()
            }
        }
    }
}