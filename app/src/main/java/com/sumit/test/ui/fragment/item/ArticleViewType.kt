package com.sumit.test.ui.fragment.item

import androidx.annotation.IntDef
import com.sumit.test.ui.fragment.item.ArticleViewType.Companion.EMPTY_TYPE_ARTICLE
import com.sumit.test.ui.fragment.item.ArticleViewType.Companion.VIEW_TYPE_ARTICLE

@IntDef(
    VIEW_TYPE_ARTICLE,
    EMPTY_TYPE_ARTICLE
)
annotation class ArticleViewType {
    companion object {
        const val VIEW_TYPE_ARTICLE = 100
        const val EMPTY_TYPE_ARTICLE = 200
    }
}