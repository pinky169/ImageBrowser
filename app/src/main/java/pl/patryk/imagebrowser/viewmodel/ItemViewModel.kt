package pl.patryk.imagebrowser.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pl.patryk.imagebrowser.database.ItemDatabase
import pl.patryk.imagebrowser.model.ItemEntity
import pl.patryk.imagebrowser.model.SearchEntity
import pl.patryk.imagebrowser.repository.ItemRepository

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: ItemRepository

    val allItems: LiveData<SearchEntity>

    init {
        // Gets reference to ItemDao from ItemDatabase to construct
        // the correct repository.
        val itemDao = ItemDatabase.getDatabase(application).itemDao()
        repository = ItemRepository(itemDao)
        allItems = repository.allItems
    }

    fun insert(searchEntity: SearchEntity) = repository.insert(searchEntity)

    fun insert(itemEntity: ItemEntity) = repository.insert(itemEntity)

    fun insertAll(itemList: List<ItemEntity>) = repository.insertAll(itemList)

    fun deleteAll() = repository.deleteAll()

    fun getAllData() = repository.getAllData()

    fun getSearchData(query: String,
                      type: String,
                      category: String,
                      orientation: String)
            = repository.getSearchData(query, type, category, orientation)
}