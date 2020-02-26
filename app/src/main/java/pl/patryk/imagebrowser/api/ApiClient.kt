package pl.patryk.imagebrowser.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    val API_URL = "https://pixabay.com/api/"

    private var retrofit: Retrofit? = null

    fun getClient(): ImageApi? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(ImageApi::class.java)
    }
}