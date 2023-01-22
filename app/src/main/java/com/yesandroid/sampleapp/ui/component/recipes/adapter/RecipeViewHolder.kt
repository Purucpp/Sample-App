package com.yesandroid.sampleapp.ui.component.recipes.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yesandroid.sampleapp.R
import com.yesandroid.sampleapp.data.dto.recipes.RecipesItem
import com.yesandroid.sampleapp.databinding.RecipeItemBinding
import com.yesandroid.sampleapp.ui.base.listeners.RecyclerItemListener

/**
 * Created by Purushottam
 */

class RecipeViewHolder(private val itemBinding: RecipeItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(recipesItem: RecipesItem, recyclerItemListener: RecyclerItemListener) {
        itemBinding.tvCaption.text = recipesItem.description
        itemBinding.tvName.text = recipesItem.name
        Picasso.get().load(recipesItem.thumb).placeholder(R.drawable.ic_healthy_food).error(R.drawable.ic_healthy_food).into(itemBinding.ivRecipeItemImage)
        itemBinding.rlRecipeItem.setOnClickListener { recyclerItemListener.onItemSelected(recipesItem) }
    }
}

