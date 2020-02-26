package pl.patryk.imagebrowser.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_info")
data class ItemEntity(
    val comments: Int,
    val downloads: Int,
    val favorites: Int,
    @PrimaryKey
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    val user_id: Int,
    val views: Int,
    val webformatHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int
) {
    override fun toString(): String {
        return "ItemEntity(" +
                "comments=$comments, " +
                "downloads=$downloads, " +
                "favorites=$favorites, " +
                "id=$id, " +
                "imageHeight=$imageHeight, " +
                "imageSize=$imageSize, " +
                "imageWidth=$imageWidth, " +
                "largeImageURL='$largeImageURL', " +
                "likes=$likes, " +
                "pageURL='$pageURL', " +
                "previewHeight=$previewHeight, " +
                "previewURL='$previewURL', " +
                "previewWidth=$previewWidth, " +
                "tags='$tags', " +
                "type='$type', " +
                "user='$user', " +
                "userImageURL='$userImageURL', " +
                "user_id=$user_id, " +
                "views=$views, " +
                "webformatHeight=$webformatHeight, " +
                "webformatURL='$webformatURL', " +
                "webformatWidth=$webformatWidth)"
    }


}