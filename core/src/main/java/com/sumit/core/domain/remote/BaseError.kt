package com.sumit.core.domain.remote

import com.google.gson.annotations.SerializedName

class BaseError {
    @SerializedName("errors")
    var error: String = "Something went wrong"
}