package pl.patryk.imagebrowser.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "search_data_table")
data class SearchEntity(
    @SerializedName("hits")
    @PrimaryKey
    val items: List<ItemEntity>,
    val total: Int,
    val totalHits: Int
)