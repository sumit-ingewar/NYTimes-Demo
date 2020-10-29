package com.sumit.core.domain.remote

import com.google.gson.annotations.SerializedName
import com.sumit.core.extensions.empty

open class BaseResponse {

    @SerializedName("status")
    val status: String = "Status"

    @SerializedName("message")
    var message: String = String.empty
}