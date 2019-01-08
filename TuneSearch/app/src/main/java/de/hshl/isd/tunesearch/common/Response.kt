package de.hshl.isd.tunesearch.common

sealed class Response {
    class Success<T>(val value: T) : Response()
    class Failure(val error: Throwable) : Response()
}