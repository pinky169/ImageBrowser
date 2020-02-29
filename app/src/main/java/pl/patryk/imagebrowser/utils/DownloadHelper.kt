package pl.patryk.imagebrowser.utils

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import pl.patryk.imagebrowser.model.ItemEntity
import java.io.File

class DownloadHelper(private val context: Context, private val itemEntity: ItemEntity) : ActivityCompat.OnRequestPermissionsResultCallback {

    companion object {
        const val REQUEST_PERMISSION = 1
    }

    fun requestDownload() {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSION)
        } else {
            startDownload()
        }
    }

    private fun startDownload() {

        val fileName = "pixabay_${itemEntity.id}.jpg"
        val file = File(Environment.getExternalStorageDirectory(), fileName)

        val request = DownloadManager.Request(Uri.parse(itemEntity.webformatURL))
            .setTitle(fileName) // Title of the Download Notification
            .setDescription("Downloading...") // Description of the Download Notification
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED) // Visibility of the download notification
            .setDestinationUri(Uri.fromFile(file)) // Uri of the destination file
            .setAllowedOverMetered(false) // Set if download is allowed on Mobile network
            .setAllowedOverRoaming(false) // Set if download is allowed on roaming network
            .setVisibleInDownloadsUi(true)

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startDownload()
            }
        }
    }
}