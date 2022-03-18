package com.yesandroid.kfirst

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET

interface Get_Interface {

    @GET("social/a.json")
    fun getsocial(): Call<List<Api_Response>>?
}