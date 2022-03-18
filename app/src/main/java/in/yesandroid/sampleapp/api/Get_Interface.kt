package `in`.yesandroid.sampleapp.api

import `in`.yesandroid.sampleapp.api.Api_Response
import retrofit2.Call
import retrofit2.http.GET

interface Get_Interface {

    @GET("social/a.json")
    fun getsocial(): Call<List<Api_Response>>?

}