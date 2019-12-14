package com.sumit.core.domain.remote

import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("status")
    val status: String = "Status"
}