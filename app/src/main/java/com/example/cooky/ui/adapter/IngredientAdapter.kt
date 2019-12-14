package com.example.cooky.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.cooky.R
import com.example.cooky.base.BaseRecyclerViewAdapter
import com.example.cooky.data.local.model.recipe.ExtendedIngredient
import com.example.cooky.databinding.ItemIngredientBinding

class IngredientAdapter :
    BaseRecyclerViewAdapter<ExtendedIngredient, ItemIngredientBinding>(DiffUtilCallback()) {
    override val layoutResource: Int = R.layout.item_ingredient

    override fun bindViewHolder(binding: ItemIngredientBinding, item: ExtendedIngredient) {
        binding.item = item
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<ExtendedIngredient>() {
        override fun areItemsTheSame(oldItem: ExtendedIngredient, newItem: ExtendedIngredient) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ExtendedIngredient, newItem: ExtendedIngredient) =
            oldItem == newItem
    }
}
