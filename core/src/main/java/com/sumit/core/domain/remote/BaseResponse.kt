package com.sumit.core.domain.remote

import com.sumit.core.extensions.empty
import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("status")
    val status: String = "Status"

    @SerializedName("message")
    var message: String = String.empty
}