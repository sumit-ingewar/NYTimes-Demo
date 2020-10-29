package com.sumit.core.data.model

import com.google.gson.annotations.SerializedName
import com.sumit.core.extensions.empty

data class MediaMetaData(
    @SerializedName("url") val url: String = String.empty
)