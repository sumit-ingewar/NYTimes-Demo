package com.sumit.test.ui.fragment.mapper

import com.sumit.core.data.model.ArticleResponse
import com.sumit.core.data.model.Media
import com.sumit.core.empty
import com.sumit.test.ui.fragment.item.ArticleItem
import com.sumit.test.ui.fragment.item.ArticleViewType

object ArticleMapper {

    fun getMapperArticleList(arrayList: ArrayList<ArticleResponse>): ArrayList<Any> {
        val list = arrayListOf<Any>()

        arrayList.forEach {
            val articleItem = ArticleItem(
                url = it.url,
                adx_keywords = it.adx_keywords,
                section = it.section,
                byline = it.byline,
                title = it.title,
                abstract = it.abstract,
                published_date = it.published_date,
                source = it.source,
                id = it.id,
                asset_id = it.asset_id,
                views = it.views,
                imageUrl = getImageUrl(it.media)

            )
            articleItem.viewType = ArticleViewType.VIEW_TYPE_ARTICLE
            list.add(articleItem)
        }

        return list
    }

    private fun getImageUrl(mediaList: ArrayList<Media>): String {
        if (mediaList[0].type == "image") {
            return mediaList[0].mediaMetadata[0].url
        }
        return String.empty
    }
}