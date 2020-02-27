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

    fun deleteAll() = itemDao.deleteAll()

    fun getSearchData(query: String, type: String, category: String, orientation: String) {

        val call = ApiClient().getClient()?.getSearchResult(query, type, category, orientation)

        call?.enqueue(object : Callback<SearchEntity> {
            override fun onFailure(call: Call<SearchEntity>, t: Throwable) {
                //Log.e("API", "error: ${t.message}")
            }

            override fun onResponse(call: Call<SearchEntity>, response: Response<SearchEntity>) {
                when (response.code()) {
                    200 -> {
                        Thread(Runnable {

                            ItemDatabase.INSTANCE!!.itemDao().deleteAll()

                            val searchEntity = response.body()!!
                            ItemDatabase.INSTANCE!!.itemDao().insert(searchEntity)

                            val items = response.body()?.items

                            for (item in items!!) {
                                ItemDatabase.INSTANCE!!.itemDao().insert(item)
                            }

                        }).start()
                    }
                }
            }
        })
    }

    fun getAllData() {

        val call = ApiClient().getClient()?.getData()

        call?.enqueue(object : Callback<SearchEntity> {
            override fun onFailure(call: Call<SearchEntity>, t: Throwable) {
                //Log.e("API", "error: ${t.message}")
            }

            override fun onResponse(call: Call<SearchEntity>, response: Response<SearchEntity>) {
                when (response.code()) {
                    200 -> {
                        Thread(Runnable {

                            ItemDatabase.INSTANCE!!.itemDao().deleteAll()

                            val searchEntity = response.body()!!
                            ItemDatabase.INSTANCE!!.itemDao().insert(searchEntity)

                            val items = response.body()?.items

                            for (item in items!!) {
                                ItemDatabase.INSTANCE!!.itemDao().insert(item)
                            }

                        }).start()
                    }
                }
            }
        })
    }
}