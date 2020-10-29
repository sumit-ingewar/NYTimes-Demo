package com.sumit.core.data.model

import com.sumit.core.extensions.empty
import com.google.gson.annotations.SerializedName

data class MediaMetaData(
    @SerializedName("url") val url: String = String.empty
)