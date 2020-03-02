package pl.patryk.imagebrowser.repository

import androidx.lifecycle.LiveData
import pl.patryk.imagebrowser.api.ApiClient
import pl.patryk.imagebrowser.dao.ItemDao
import pl.patryk.imagebrowser.database.ItemDatabase
import pl.patryk.imagebrowser.model.ItemEntity
import pl.patryk.imagebrowser.model.SearchEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemRepository(private val itemDao: ItemDao) {

    val allItems: LiveData<SearchEntity> = itemDao.getAllData()

    fun insert(itemEntity: ItemEntity) = itemDao.insert(itemEntity)

    fun insert(searchEntity: SearchEntity) = itemDao.insert(searchEntity)

    fun insertAll(itemList: List<ItemEntity>) = itemDao.insertAll(itemList)

    fun deleteAll() = itemDao.deleteAll()

    fun getSearchData(query: String, type: String, category: String, orientation: String) {
        val call = ApiClient().getClient()?.getSearchResult(query, type, category, orientation)
        call?.enqueue(callback)
    }

    fun getAllData() {
        val call = ApiClient().getClient()?.getAllData()
        call?.enqueue(callback)
    }

    private val callback = object : Callback<SearchEntity> {

        override fun onFailure(call: Call<SearchEntity>, t: Throwable) {
            //Log.e("API", "error: ${t.message}")
        }

        override fun onResponse(call: Call<SearchEntity>, response: Response<SearchEntity>) {
            when (response.code()) {
                200 -> {
                    Thread(Runnable {

                        // Clear db
                        ItemDatabase.INSTANCE!!.itemDao().deleteAll()

                        // Insert search results into db
                        val searchEntity = response.body()!!
                        ItemDatabase.INSTANCE!!.itemDao().insert(searchEntity)

                        val items = response.body()?.items
                        ItemDatabase.INSTANCE!!.itemDao().insertAll(items!!)

                    }).start()
                }
            }
        }
    }
}