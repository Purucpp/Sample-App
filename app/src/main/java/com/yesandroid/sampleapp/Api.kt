package com.yesandroid.sampleapp

import io.reactivex.Observable
import retrofit2.http.GET

interface Api {


    @GET("placedemo.json")
    fun getPlaces() : Observable<Response>
}