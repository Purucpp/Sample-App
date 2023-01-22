package com.yesandroid.sampleapp.data.remote

import com.yesandroid.sampleapp.data.Resource
import com.yesandroid.sampleapp.data.dto.recipes.Recipes

/**
 * Created by Purushottam
 */

internal interface RemoteDataSource {
    suspend fun requestRecipes(): Resource<Recipes>
}
