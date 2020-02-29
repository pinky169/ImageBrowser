package pl.patryk.imagebrowser.api

import pl.patryk.imagebrowser.BuildConfig
import pl.patryk.imagebrowser.model.SearchEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {

    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

    @GET("?key=$API_KEY&per_page=200")
    fun getData(): Call<SearchEntity>

    @GET("?key=$API_KEY&per_page=200")
    fun getSearchResult(
        @Query("q") query: String,
        @Query("image_type") type: String,
        @Query("category") category: String,
        @Query("orientation") orientation: String
    ): Call<SearchEntity>
}