package com.sumit.core.data.remote

import com.sumit.core.data.model.ArticleParentResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET("svc/mostpopular/v2/viewed/1.json")
    fun getArticles(@Query("api-key") apiKey: String): Single<ArticleParentResponse>

}