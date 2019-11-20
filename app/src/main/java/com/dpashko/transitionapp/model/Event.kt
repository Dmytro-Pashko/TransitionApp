package com.dpashko.transitionapp.model

sealed class Event<out T>(val status: Status, val data: T?, val error: Throwable?) {

    class Loading<out T> : Event<T>(Status.LOADING, null, null)

    class Success<out T>(data: T) : Event<T>(Status.SUCCESS, data, null)

    class Error<out T>(error: Throwable) : Event<T>(Status.ERROR, null, error)
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
