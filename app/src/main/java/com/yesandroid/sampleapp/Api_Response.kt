package com.yesandroid.sampleapp

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Api_Response {

    @SerializedName("name")
    @Expose
    private var name: String? = null


    @SerializedName("imgurl")
    @Expose
    private var imgurl: String? = null


    fun getImageUrl(): String?{
        return imgurl
    }


    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

}



