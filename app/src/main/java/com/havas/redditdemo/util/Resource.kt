package com.havas.redditdemo.util

/**
 * A generic class that holds a value with its loading status.
 * @param <T> The type of the data.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    /**
     * A state that represents a successful outcome.
     * @param data The data.
     */
    class Success<T>(data: T) : Resource<T>(data)

    /**
     * A state that represents an error.
     * @param message The error message.
     * @param data The data, if any.
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
