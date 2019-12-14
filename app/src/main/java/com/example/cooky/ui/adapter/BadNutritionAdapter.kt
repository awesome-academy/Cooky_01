package com.example.cooky.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.cooky.R
import com.example.cooky.base.BaseRecyclerViewAdapter
import com.example.cooky.data.local.model.nutition.Bad
import com.example.cooky.databinding.ItemBadNutritionBinding

class BadNutritionAdapter :
    BaseRecyclerViewAdapter<Bad, ItemBadNutritionBinding>(DiffUtilCallback()) {

    override val layoutResource: Int = R.layout.item_bad_nutrition

    override fun bindViewHolder(binding: ItemBadNutritionBinding, item: Bad) {
        binding.item = item
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Bad>() {
        override fun areItemsTheSame(oldItem: Bad, newItem: Bad) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Bad, newItem: Bad) =
            oldItem == newItem
    }
}
