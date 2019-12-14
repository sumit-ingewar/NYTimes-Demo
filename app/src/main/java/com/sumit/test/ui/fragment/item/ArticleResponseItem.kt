package com.sumit.test.ui.fragment.item

import com.sumit.core.empty

data class ArticleItem(
    val url: String = String.empty,
    val adx_keywords: String = String.empty,
    val section: String = String.empty,
    val byline: String = String.empty,
    val title: String = String.empty,
    val abstract: String = String.empty,
    val published_date: String = String.empty,
    val source: String = String.empty,
    val id: Long = 0L,
    val asset_id: Long = 0L,
    val views: Long = 0L,
    val imageUrl: String = String.empty
) : ArticleItemViewType()