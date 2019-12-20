package com.example.cooky.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.cooky.R
import com.example.cooky.base.BaseRecyclerViewAdapter
import com.example.cooky.data.local.model.recipe.Recipe
import com.example.cooky.databinding.ItemRecipeVerticalBinding

class RecipeAdapterVertical(
    private val onItemClick: (Recipe) -> Unit
) : BaseRecyclerViewAdapter<Recipe, ItemRecipeVerticalBinding>(DiffUtilCallback()) {

    override val layoutResource: Int = R.layout.item_recipe_vertical

    override fun setEvent(binding: ItemRecipeVerticalBinding) {
        binding.itemRecipe.setOnClickListener {
            binding.item?.let(onItemClick)
        }
    }

    override fun bindViewHolder(binding: ItemRecipeVerticalBinding, item: Recipe) {
        binding.item = item
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem.recipeId == newItem.recipeId

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem == newItem
    }
}
