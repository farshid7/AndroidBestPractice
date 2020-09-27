package com.example.androidbestpractice.di.networkManager

data class Resource<out T>(val status: Status, val data: T?, val errorBody: ErrorBody?) {
    companion object {
        fun <T> loading(): Resource<T> =
            Resource(status = Status.LOADING, data = null, errorBody = null)

        fun <T> success(data: T): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, errorBody = null)

        fun <T> error(data: T?, errorBody: ErrorBody): Resource<T> =
            Resource(status = Status.ERROR, data = data, errorBody = errorBody)

        fun <T> failed(): Resource<T> =
            Resource(status = Status.FILED, data = null, errorBody = null)
    }
}
