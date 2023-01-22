package com.yesandroid.sampleapp.ui.base.listeners

import com.yesandroid.sampleapp.data.dto.recipes.RecipesItem

/**
 * Created by Purushottam
 */

interface RecyclerItemListener {
    fun onItemSelected(recipe : RecipesItem)
}
