package com.sumit.test.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sumit.test.base.BaseRecyclerAdapter
import com.sumit.test.base.BaseViewHolder
import com.sumit.test.base.ItemClickListener
import com.sumit.test.databinding.ArticleRecyclerItemBinding
import com.sumit.test.databinding.EmptyRecyclerItemBinding
import com.sumit.test.ui.fragment.item.ArticleItemViewType
import com.sumit.test.ui.fragment.item.ArticleViewType

class ArticleAdapter(private var itemClickListener: ItemClickListener) :
    BaseRecyclerAdapter<Any>(itemClickListener) {

    override fun createBaseViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Any> = when (viewType) {

        ArticleViewType.VIEW_TYPE_ARTICLE -> {

            ArticleViewHolder(
                ArticleRecyclerItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        ArticleViewType.EMPTY_TYPE_ARTICLE -> {
            EmptyViewHolder(
                EmptyRecyclerItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        else -> {
            throw UnsupportedOperationException("Invalide view type: $viewType")
        }


    }

    override fun getItemViewType(position: Int): Int =
        ((getItems()[position]) as ArticleItemViewType).viewType
}