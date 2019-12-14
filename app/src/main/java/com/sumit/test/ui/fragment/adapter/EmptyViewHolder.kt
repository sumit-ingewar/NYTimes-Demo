package com.sumit.test.ui.fragment.adapter

import com.sumit.test.base.BaseViewHolder
import com.sumit.test.databinding.EmptyRecyclerItemBinding

class EmptyViewHolder(emptyRecyclerItemBinding: EmptyRecyclerItemBinding) :
    BaseViewHolder<Any>(emptyRecyclerItemBinding.root) {
    override fun bindItem(item: Any) {

    }
}