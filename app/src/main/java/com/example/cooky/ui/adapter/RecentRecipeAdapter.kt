package com.example.cooky.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.cooky.R
import com.example.cooky.base.BaseRecyclerViewAdapter
import com.example.cooky.data.local.model.recipe.Recipe
import com.example.cooky.databinding.ItemRecipeRecentlyBinding

class RecentRecipeAdapter(
    private val onItemClick: (Recipe) -> Unit
) : BaseRecyclerViewAdapter<Recipe, ItemRecipeRecentlyBinding>(DiffUtilCallback()) {

    override val layoutResource: Int = R.layout.item_recipe_recently

    override fun bindViewHolder(binding: ItemRecipeRecentlyBinding, item: Recipe) {
        binding.item = item
    }

    override fun setEvent(binding: ItemRecipeRecentlyBinding) {
        binding.itemRecipe.setOnClickListener {
            binding.item?.let(onItemClick)
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem.recipeId == newItem.recipeId

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem == newItem
    }
}
