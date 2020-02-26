package pl.patryk.imagebrowser.api

import pl.patryk.imagebrowser.model.SearchEntity
import retrofit2.http.GET
import retrofit2.Call

interface ImageApi {

    @GET("?key=15386729-3ae66212ee8927ba5030bab5a&per_page=200")
    fun getData(): Call<SearchEntity>
}