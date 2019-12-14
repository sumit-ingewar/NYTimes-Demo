package com.sumit.core.data.remote

import com.sumit.core.data.model.ArticleParentResponse
import io.reactivex.Single

interface ArticleRepository {

    fun getArticles(apiKey: String): Single<ArticleParentResponse>
}