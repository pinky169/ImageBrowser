package pl.patryk.imagebrowser.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.patryk.imagebrowser.model.ItemEntity
import pl.patryk.imagebrowser.model.SearchEntity

@Dao
interface ItemDao {

    @Query("DELETE FROM search_data_table")
    fun deleteAll()

    @Query("SELECT * FROM search_data_table")
    fun getAllData(): LiveData<SearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchEntity: SearchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemEntity: ItemEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(itemList: List<ItemEntity>)
}