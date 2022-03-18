package `in`.yesandroid.sampleapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Get_Retrofit_Client {

    val Base_url = "https://yesandroid.com/"
    private var retrofit: Retrofit? = null
    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}