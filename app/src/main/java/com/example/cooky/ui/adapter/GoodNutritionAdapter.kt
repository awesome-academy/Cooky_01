package com.example.cooky.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.cooky.R
import com.example.cooky.base.BaseRecyclerViewAdapter
import com.example.cooky.data.local.model.nutition.Bad
import com.example.cooky.data.local.model.nutition.Good
import com.example.cooky.databinding.ItemBadNutritionBinding
import com.example.cooky.databinding.ItemGoodNutritionBinding

class GoodNutritionAdapter :
    BaseRecyclerViewAdapter<Good, ItemGoodNutritionBinding>(DiffUtilCallback()) {

    override val layoutResource: Int = R.layout.item_good_nutrition

    override fun bindViewHolder(binding: ItemGoodNutritionBinding, item: Good) {
        binding.item = item
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Good>() {
        override fun areItemsTheSame(oldItem: Good, newItem: Good) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Good, newItem: Good) =
            oldItem == newItem
    }
}
