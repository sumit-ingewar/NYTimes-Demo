package com.sumit.core.data.remote

import javax.inject.Inject

class ArticleRemote @Inject constructor(
    private val articleService: ArticleService
) {

    fun getArticles(apiKey: String) = articleService.getArticles(apiKey)
}