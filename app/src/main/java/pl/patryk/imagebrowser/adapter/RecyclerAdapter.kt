package pl.patryk.imagebrowser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*
import pl.patryk.imagebrowser.R
import pl.patryk.imagebrowser.model.ItemEntity

class RecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var itemList = emptyList<ItemEntity>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image = itemView.thumbnail_view
        private val authorImage = itemView.author_img
        private val author = itemView.txt_view_author
        private val likes = itemView.txt_view_like
        private val favourites = itemView.txt_view_favorite
        private val downloads = itemView.txt_view_downloads
        private val imageSize = itemView.txt_view_size

        fun bind(itemEntity: ItemEntity) {
            Glide
                .with(context)
                .load(itemEntity.webformatURL)
                .centerCrop()
                .into(image)
            Glide
                .with(context)
                .load(itemEntity.userImageURL)
                .centerCrop()
                .into(authorImage)

            author.text = context.resources.getString(R.string.author, itemEntity.user)
            likes.text = itemEntity.likes.toString()
            favourites.text = itemEntity.favorites.toString()
            downloads.text = context.resources.getString(R.string.total_downloads, itemEntity.downloads)
            imageSize.text = context.resources.getString(R.string.size, itemEntity.webformatWidth, itemEntity.webformatHeight)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(itemList[position])

    override fun getItemCount() = itemList.size

    fun setItems(items: List<ItemEntity>) {
        this.itemList = items
        notifyDataSetChanged()
    }
}