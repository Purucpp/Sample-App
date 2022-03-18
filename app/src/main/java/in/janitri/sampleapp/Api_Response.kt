package com.yesandroid.kfirst

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Api_Response {

    @SerializedName("name")
    @Expose
    private var name: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

}



