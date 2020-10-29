package com.sumit.core.domain.remote

import java.io.IOException

class HTTPBadRequest constructor(message: String) : Throwable(message){
    constructor() : this("Bad Request")
}

class HTTPNotFoundException constructor(message: String) : Exception(message)

class HTTPBadRequestconstructor(message: String) : Throwable(message)

class ServerNotAvailableException(message: String) : Throwable(message)

class AuthorizationException(message: String) : Throwable(message)

class NetworkException(throwable: Throwable) : IOException(throwable.message, throwable)


