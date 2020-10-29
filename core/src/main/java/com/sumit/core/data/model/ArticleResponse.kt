package com.sumit.core.data.model

import com.google.gson.annotations.SerializedName
import com.sumit.core.extensions.empty


data class ArticleResponse(
    @SerializedName("url") val url: String = String.empty,
    @SerializedName("adx_keywords") val adx_keywords: String = String.empty,
    @SerializedName("section") val section: String = String.empty,
    @SerializedName("byline") val byline: String = String.empty,
    @SerializedName("title") val title: String = String.empty,
    @SerializedName("abstract") val abstract: String = String.empty,
    @SerializedName("published_date") val published_date: String = String.empty,
    @SerializedName("source") val source: String = String.empty,
    @SerializedName("id") val id: Long = 0L,
    @SerializedName("asset_id") val asset_id: Long = 0L,
    @SerializedName("views") val views: Long = 0L,
    @SerializedName("media") val media: ArrayList<Media> = arrayListOf()
)