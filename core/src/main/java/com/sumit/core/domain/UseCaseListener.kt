package com.sumit.core.domain

interface UseCaseListener {

    fun onPreExecute()

    fun onPostExecute()

}