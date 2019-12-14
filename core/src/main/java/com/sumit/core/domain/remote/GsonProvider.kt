package com.sumit.core.domain.remote

import android.text.format.DateFormat
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class GsonProvider {

    companion object {
        const val ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    }

    val instance: Gson by lazy {
        GsonBuilder().setDateFormat(ISO_8601).create()
    }
}