package com.sumit.core.data.remote

import com.sumit.core.data.model.ArticleParentResponse
import com.sumit.core.domain.SingleUseCase
import com.sumit.core.domain.executor.PostExecutionThread
import io.reactivex.Single
import javax.inject.Inject

class ArticleUseCase @Inject constructor(
    private val articleRepository: ArticleRepository,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<ArticleParentResponse, ArticleUseCase.Params>(postExecutionThread) {

    override fun buildUseCase(params: Params?): Single<ArticleParentResponse> {
        return articleRepository.getArticles(params!!.apiKey)
    }

    data class Params constructor(
        val apiKey: String
    ) {
        companion object {
            fun create(
                apiKey: String
            ) = Params(
                apiKey = apiKey
            )
        }
    }
}