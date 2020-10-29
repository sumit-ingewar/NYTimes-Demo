package com.sumit.core.data.model

import com.sumit.core.extensions.empty
import com.google.gson.annotations.SerializedName


data class Media(
    @SerializedName("type") val type: String = String.empty,
    @SerializedName("media-metadata") val mediaMetadata: ArrayList<MediaMetaData> = arrayListOf()
)