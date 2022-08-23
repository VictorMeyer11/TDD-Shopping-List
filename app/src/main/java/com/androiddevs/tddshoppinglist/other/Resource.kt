package com.androiddevs.tddshoppinglist.other

sealed class Resource<T>(val data: T?, val message: String?, val status: Status) {
    class Success<T>(data: T): Resource<T>(data, null, Status.SUCCESS)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message, Status.ERROR)
    class Loading<T>(data: T? = null) : Resource<T>(data, null, Status.LOADING)
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}