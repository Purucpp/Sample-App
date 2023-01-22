package com.example.mvvmkotlinexample.retrofit

import com.example.mvvmkotlinexample.model.ServicesSetterGetter
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

   // @GET("services")
    @GET("api/mvvm.json")
    fun getServices() : Call<ServicesSetterGetter>

}