package com.sumit.core.data.remote

import com.sumit.core.data.model.ArticleParentResponse
import io.reactivex.Single
import javax.inject.Inject

class ArticleDataRepository @Inject constructor(
    private val articleRemote: ArticleRemote
) : ArticleRepository {

    override fun getArticles(apiKey: String): Single<ArticleParentResponse> {
        return articleRemote.getArticles(apiKey)
    }
}