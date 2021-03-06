package com.sumit.test.ui.fragment.item

import com.sumit.core.extensions.empty

data class ArticleItem(
    val url: String = String.empty,
    val byline: String = String.empty,
    val title: String = String.empty,
    val published_date: String = String.empty,
    val imageUrl: String = String.empty
) : ArticleItemViewType()