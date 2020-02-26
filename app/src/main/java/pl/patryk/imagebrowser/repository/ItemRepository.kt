package pl.patryk.imagebrowser.repository

import android.util.Log
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

    fun deleteAll() = itemDao.deleteAll()

    fun insert(itemEntity: ItemEntity) = itemDao.insert(itemEntity)

    fun insert(searchEntity: SearchEntity) = itemDao.insert(searchEntity)

    fun getData() {

        val call = ApiClient().getClient()?.getData()

        call?.enqueue(object : Callback<SearchEntity> {
            override fun onFailure(call: Call<SearchEntity>, t: Throwable) {
                Log.e("API", "error: ${t.message}")
            }

            override fun onResponse(call: Call<SearchEntity>, response: Response<SearchEntity>) {
                Log.e("API", "response code-> ${response!!.code()}")
                when (response.code()) {
                    200 -> {
                        Thread(Runnable {

                            ItemDatabase.INSTANCE!!.itemDao().deleteAll()

                            val searchEntity = response.body()!!
                            val items = response.body()?.items

                            for (item in items!!) {
                                Log.d(
                                    "API", "URL: ${item.webformatURL} \n" +
                                            "TITLE: ${item.tags} \n" +
                                            "LIKES: ${item.likes} \n" +
                                            "VIEWS: ${item.views}"
                                )

                                ItemDatabase.INSTANCE!!.itemDao().insert(item)
                                ItemDatabase.INSTANCE!!.itemDao().insert(searchEntity)
                            }

                        }).start()
                    }
                }
            }
        })
    }
}