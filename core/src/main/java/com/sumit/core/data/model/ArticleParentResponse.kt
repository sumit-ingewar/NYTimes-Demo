package com.sumit.core.data.model

import com.google.gson.annotations.SerializedName
import com.sumit.core.domain.remote.BaseResponse
import com.sumit.core.extensions.empty

data class ArticleParentResponse(
    @SerializedName("copyright") val copyright: String = String.empty,
    @SerializedName("num_results") val num_results: Int = 0,
    @SerializedName("results") val results: ArrayList<ArticleResponse> = arrayListOf()

) : BaseResponse()