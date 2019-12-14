package com.sumit.test.ui.fragment.adapter

import com.bumptech.glide.Glide
import com.sumit.test.base.BaseViewHolder
import com.sumit.test.databinding.ArticleRecyclerItemBinding
import com.sumit.test.ui.fragment.item.ArticleItem

class ArticleViewHolder(
    private var articleRecyclerItemBinding: ArticleRecyclerItemBinding
) : BaseViewHolder<Any>(articleRecyclerItemBinding.root) {

    override fun bindItem(item: Any) {
        articleRecyclerItemBinding.data = item as ArticleItem

        Glide.with(articleRecyclerItemBinding.imageViewArticleImage.context)
            .load(item.imageUrl)
            .into(articleRecyclerItemBinding.imageViewArticleImage)
    }
}