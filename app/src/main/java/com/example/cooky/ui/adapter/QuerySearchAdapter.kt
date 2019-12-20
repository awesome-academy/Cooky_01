package com.example.cooky.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.cooky.R
import com.example.cooky.base.BaseRecyclerViewAdapter
import com.example.cooky.data.local.model.autocomplete.QueryRecipeSearch
import com.example.cooky.databinding.ItemAutocompleteQueryBinding

class QuerySearchAdapter(
    private val onItemClick: (QueryRecipeSearch) -> Unit
) : BaseRecyclerViewAdapter<QueryRecipeSearch, ItemAutocompleteQueryBinding>(DiffUtilCallback()) {

    override val layoutResource = R.layout.item_autocomplete_query

    override fun bindViewHolder(binding: ItemAutocompleteQueryBinding, item: QueryRecipeSearch) {
        binding.item = item
    }

    override fun setEvent(binding: ItemAutocompleteQueryBinding) {
        binding.apply {
            itemQuery.setOnClickListener {
                item?.let(onItemClick)
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<QueryRecipeSearch>() {
        override fun areItemsTheSame(
            oldItem: QueryRecipeSearch,
            newItem: QueryRecipeSearch
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: QueryRecipeSearch,
            newItem: QueryRecipeSearch
        ) = oldItem == newItem
    }
}
